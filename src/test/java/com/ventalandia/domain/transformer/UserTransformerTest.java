package com.ventalandia.domain.transformer;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.ventalandia.meli.api.user.MeliPublicUser;
import com.ventalandia.service.CountryService;
import com.ventalandia.domain.Country;

public class UserTransformerTest {

    private CountryService countryService;

    @Before
    public void setup() {

        countryService = mock(CountryService.class);
    }

    @Test
    public void testTransform() throws Exception {
        Country country = new Country();
        when(countryService.getByMeliId("AR")).thenReturn(country);
        UserTransformer userTransformer = new UserTransformer(countryService);

        com.ventalandia.domain.User user = userTransformer.transform(createUser());
        assertEquals("GERMANTANO", user.getNickName());
        assertEquals(country, user.getCountry());
    }

    private MeliPublicUser createUser() {
        MeliPublicUser user = new MeliPublicUser();
        user.setNickname("GERMANTANO");
        user.setCountry_id("AR");
        return user;
    }

}
