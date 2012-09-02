package com.ventalandia.meli.api.auth;

/**
 * 
 * @author matias
 *
 */
/**
 * @author msulik
 *
 */
public class AuthToken {

	private String access_token;
	private String token_type;
	private Long expires_in;
	private String refresh_token;
	private String scope;
	
	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	
	public String getRefresh_token() {
		return refresh_token;
	}
	
	public boolean hasRefreshToken() {
		return this.refresh_token != null && !this.refresh_token.isEmpty();
	}

	@Override
	public String toString() {
		return "AuthToken [access_token=" + access_token + ", token_type="
				+ token_type + ", expires_in=" + expires_in
				+ ", refresh_token=" + refresh_token + ", scope=" + scope + "]";
	}

}
