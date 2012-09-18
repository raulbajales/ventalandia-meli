package com.ventalandia.service;

import java.util.UUID;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.domain.Token;
import com.ventalandia.ioc.TokenCache;
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
     * @return
     */
    public Token getToken(String hash) {
        // TODO change it to use hash -> user_id -> token
        String id = (String) this.memcacheService.get(hash);
        if (id == null) {
            return null;
        }
        else {
            return this.getTokenFromCache(id);
        }
    }

    private Token getTokenFromCache(String key) {
        String tokenAsString = (String) this.memcacheService.get(key);

        if (tokenAsString != null) {
            return this.gson.fromJson(tokenAsString, Token.class);
        }
        else {
            if (key.startsWith(PREFFIX)) {
                Token token = this.tokenRepository.getByMeliUserId(Long.valueOf(key.replace(PREFFIX, "")));

                this.memcacheService.put(key, this.gson.toJson(token));

                return token;
            }
            return null;
        }
    }

    /**
     * Add a token to the cache and return a hash to locate the entry. It also
     * could be located by the meliId (user) provided by the {@link Token}.
     * 
     * @param token
     * @return a <b>hash</b> to identify the cache entry.
     */
    public String addToken(Token token) {
        // generate cache's keys
        String hash = UUID.randomUUID().toString();
        String userId = PREFFIX + token.getMeliId();

        // adding to cache service
        // TODO check expiration
        this.memcacheService.put(hash, userId);
        this.memcacheService.put(userId, this.gson.toJson(token));

        return hash;
    }
}
