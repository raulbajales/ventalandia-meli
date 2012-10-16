package com.ventalandia.service;

import java.util.UUID;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheService.SetPolicy;
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

    private static final int TWO_WEEKS_IN_SECONDS = 60 * 60 * 24 * 14;

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

                this.cacheToken(token);

                return token;
            }
            else { // it's a hash and there is no way to get a user id
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
        this.memcacheService.put(hash, userId, Expiration.byDeltaSeconds(TWO_WEEKS_IN_SECONDS));
        this.cacheToken(token);

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
        AuthContext.setAuthToken(token);
        token.setMeliId(this.userMeliService.getCurrentUser().getId());

        // TODO add here some code to persist a token (insert/updated). Also it
        // should be placed on cache.

        this.updateOnDS(token);

        return this.addToken(token);
    }

    private void updateOnDS(Token token) {
        Token persisted = null;
        try {
            persisted = this.tokenRepository.getByMeliUserId(token.getMeliId());
        }
        catch (Exception e) {
            // do nothing
        }

        if (persisted != null) {
            persisted.setAccess_token(token.getAccess_token());
            persisted.setRefresh_token(token.getRefresh_token());
            persisted.setExpires_in(token.getExpires_in());
            this.tokenRepository.update(persisted);
        }
        else {
            this.tokenRepository.add(token);
        }
    }

    private void replaceTokenOnCache(Token token) {
        Token persisted = this.tokenRepository.getByMeliUserId(token.getMeliId());

        persisted.setAccess_token(token.getAccess_token());
        persisted.setRefresh_token(token.getRefresh_token());
        persisted.setExpires_in(token.getExpires_in());

        this.tokenRepository.update(persisted);
        this.cacheToken(persisted);
    }

    private void cacheToken(Token token) {
        this.memcacheService.put(token.getMeliId(), this.gson.toJson(token), Expiration.byDeltaSeconds(token.getExpires_in().intValue()), SetPolicy.SET_ALWAYS);
    }

    private void updateTokenValues(Token token) {
        Token refreshedToken = this.tokenTransformer.transform(this.meliService.refreshAuthToken(token.getRefresh_token()));

        refreshedToken.setMeliId(token.getMeliId());
        AuthContext.setAuthToken(refreshedToken);

        this.replaceTokenOnCache(refreshedToken);
    }

    public void refreshToken() {
        this.updateTokenValues(AuthContext.getToken());
    }

}
