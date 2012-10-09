package com.ventalandia.service;

import com.google.inject.Module;
import com.ventalandia.domain.DomainTest;
import com.ventalandia.ioc.GaeModule;
import com.ventalandia.ioc.VentalandiaDomainModule;

/**
 * 
 * @author msulik
 *
 */
public abstract class MeliDomainTest extends DomainTest {

    @Override
    protected Module[] getModules() {
        return new Module[] { new VentalandiaDomainModule(), new LocalMeliModuleTest(), new GaeModule() };
    }
    
}
