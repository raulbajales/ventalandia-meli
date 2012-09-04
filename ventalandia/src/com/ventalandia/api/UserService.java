package com.ventalandia.api;

import com.ventalandia.meli.api.user.MeliUser;
import com.ventalandia.meli.service.MeliUserContext;

/**
 * 
 * @author msulik
 *
 */
public class UserService {

	public User getCurrent() {
		User user = new User();
		
		MeliUser meliUser = MeliUserContext.get();
		
		user.setNickname(meliUser.getNickname());
		user.setMeliId(meliUser.getId());
		
		return user;
	}

}
