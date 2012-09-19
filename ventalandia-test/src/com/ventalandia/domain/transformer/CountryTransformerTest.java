package com.ventalandia.domain.transformer;

import org.junit.Test;

import com.ventalandia.meli.api.notification.Country;
import static junit.framework.Assert.*;

public class CountryTransformerTest {

    @Test
    public void testTransform() throws Exception {

        CountryTransformer countryTransformer = new CountryTransformer();
        com.ventalandia.domain.Country country = countryTransformer.transform(createCountry());
        assertEquals("AR", country.getMeliId());
        assertEquals("Argentina", country.getName());

    }

    private Country createCountry() {
        Country country = new Country();
        country.setId("AR");
        country.setName("Argentina");
        return country;
    }

}
