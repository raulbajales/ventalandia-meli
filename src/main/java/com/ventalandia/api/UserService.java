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
		user.setName(meliUser.getFirst_name());
		user.setSurname(meliUser.getLast_name());
		user.setSellerReputationLevel(this.translateSellerReputationLevel(meliUser.getSeller_reputation().getLevel_id()));
		
		return user;
	}

	private int translateSellerReputationLevel(String level) {
		if (level == null) {
			return 0;
		} else if (level.startsWith("0")) {
			return 0;
		} else if (level.startsWith("1")) {
			return 1;
		} else if (level.startsWith("2")) {
			return 2;
		} else if (level.startsWith("3")) {
			return 3;
		} else if (level.startsWith("4")) {
			return 4;
		} else if (level.startsWith("5")) {
			return 5;
		}
		return 0;
	}

}
