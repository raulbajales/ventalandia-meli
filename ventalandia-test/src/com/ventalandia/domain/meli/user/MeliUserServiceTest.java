package com.ventalandia.domain.meli.user;

import org.junit.Test;

import com.google.inject.Inject;
import com.ventalandia.domain.meli.AbstractMeliTest;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.service.MeliAuthContext;
import com.ventalandia.meli.service.UserMeliService;

public class MeliUserServiceTest extends AbstractMeliTest {

	@Inject
	private UserMeliService userMeliService;
	
	@Test
	public void test() throws Exception {
		AuthToken authToken = new AuthToken();
		

		authToken.setAccess_token("APP_USR-10601-090320-4e613aefb063e6ebb36032ad583510be-90661434");
		MeliAuthContext.setAuthToken(authToken);
		System.out.println(this.userMeliService.getCurrentUser());
	}
	
}
