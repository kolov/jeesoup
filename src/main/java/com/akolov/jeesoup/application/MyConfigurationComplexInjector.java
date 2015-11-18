package com.akolov.jeesoup.application;


import com.akolov.jeesoup.annotation.ConfigurationInjector;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class MyConfigurationComplexInjector {
    static final String APP_KEY_MISSING = "No applicatiion key definid; use @ApplicationConfiguration ";


    @Inject
    private ConfigurationInjector configurationInjector;

    @Produces
    @MyConfigurationComplex
    public String injectConfiguration(InjectionPoint ip) throws IllegalStateException {

        return configurationInjector.inject(ip, MyConfigurationComplex.class);
    }

}
