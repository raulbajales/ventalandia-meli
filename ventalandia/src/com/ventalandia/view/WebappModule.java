package com.ventalandia.view;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class WebappModule extends AbstractModule {

	@Override
	protected void configure() {
		this.bind(WebappView.class).to(WebappViewImpl.class).in(Scopes.SINGLETON);
	}
}
