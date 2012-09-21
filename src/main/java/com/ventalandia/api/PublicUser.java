package com.ventalandia.api;

/**
 * 
 * @author matias
 * 
 */
public class PublicUser {

	private long id;
	private String nickname;
	private String permalink;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getPermalink() {
		return permalink;
	}

	@Override
	public String toString() {
		return "PublicUser [id=" + id + ", nickname=" + nickname
				+ ", permalink=" + permalink + "]";
	}

}
