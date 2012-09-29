package com.ventalandia.service;

import java.util.UUID;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.domain.Token;
import com.ventalandia.domain.transformer.TokenTransformer;
import com.ventalandia.ioc.TokenCache;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.service.AuthContext;
import com.ventalandia.meli.service.MeliService;
import com.ventalandia.meli.service.UserMeliService;
import com.ventalandia.persistence.TokenRepository;

/**
 * General contract for Tokens on cache:<br>
 * <li>If the provided key is a HASH (String) then it will check for a MELI User
 * ID. <li>It the provided key is a User ID it will return or try to find a
 * persisted {@link Token}. <br>
 * 
 * @author matias
 * 
 */
public class AuthService {

    private static final String PREFFIX = "ID_";

    @Inject
    @TokenCache
    private MemcacheService memcacheService;

    @Inject
    private Gson gson;

    @Inject
    private TokenRepository tokenRepository;

    @Inject
    private MeliService meliService;

    @Inject
    private UserMeliService userMeliService;

    private TokenTransformer tokenTransformer = new TokenTransformer();

    /**
     * Get a token associated with a User ID. This user id is a MELI User ID.
     * 
     * @param userId
     * @return
     */
    public Token getToken(long userId) {
        return this.getTokenFromCache(PREFFIX + userId);
    }

    /**
     * Get a token associated with a hash. This hash is located on web clients
     * (cookies) and must be provided in order to identify the User and Session.
     * 
     * @param hash
     * @return the {@link Token}
     */
    public Token getToken(String hash) {
        // hash -> user_id -> token
        String userId = (String) this.memcacheService.get(hash);
        if (userId == null) {
            return null;
        }
        else {
            return this.getTokenFromCache(userId);
        }
    }

    private Token getTokenFromCache(String key) {
        String tokenAsString = (String) this.memcacheService.get(key);

        if (tokenAsString != null) {
            return this.gson.fromJson(tokenAsString, Token.class);
        }
        else {
            // it has a token but was removed from cache
            if (key.startsWith(PREFFIX)) {
                Token token = this.tokenRepository.getByMeliUserId(Long.valueOf(key.replace(PREFFIX, "")));

                this.memcacheService.put(key, this.gson.toJson(token));

                return token;
            } else { // it's a hash and there is no way to get a user id
                return null;
            }
        }
    }

    /**
     * Add a token to the cache and return a hash to locate the entry. It also
     * could be located by the meliId (user) provided by the {@link Token}.
     * 
     * @param token
     * @return a <b>hash</b> to identify the cache entry.
     */
    // TODO check expiration
    public String addToken(Token token) {
        // generate cache's keys
        String hash = UUID.randomUUID().toString();
        String userId = PREFFIX + token.getMeliId();

        // adding to cache service
        this.memcacheService.put(hash, userId);
        this.memcacheService.put(userId, this.gson.toJson(token));

        return hash;
    }

    /**
     * 
     * @param meliCode provided by MELI
     * @return a hash to identify the {@link Token}
     */
    // TODO rename.
    public String generateToken(String meliCode) {
        AuthToken authToken = this.meliService.getAuthToken(meliCode);
        Token token = this.tokenTransformer.transform(authToken);
        token.setMeliId(this.userMeliService.getCurrentUser().getId());

        AuthContext.setAuthToken(token);

        // TODO add here some code to persist a token (insert/updated). Also it
        // should be placed on cache.

        this.tokenRepository.update(token);

        return this.addToken(token);
    }

    private void replaceTokenOnCache(Token token) {
        this.tokenRepository.update(token);
        this.memcacheService.put(token.getMeliId(), this.gson.toJson(token));
    }

    // TODO it could be wrong. It should return the token and when the
    // Ventalandia hits MELI API it should be refreshed.
    public Token generateOfflineToken(long meliId) {
        Token token = this.getToken(meliId);

        // not sure about this line
        // this.updateTokenValues(token);

        return token;
    }

    private void updateTokenValues(Token token) {
        Token refreshedToken = this.tokenTransformer.transform(this.meliService.refreshAuthToken(token.getRefresh_token()));

        token.setAccess_token(refreshedToken.getAccess_token());
        token.setRefresh_token(refreshedToken.getRefresh_token());
        token.setExpires_in(refreshedToken.getExpires_in());

        this.replaceTokenOnCache(token);
    }

    public void refreshToken() {
        this.updateTokenValues(AuthContext.getToken());
    }

    public void refreshToken(long meliId) {
        this.updateTokenValues(this.getToken(meliId));
    }

}
