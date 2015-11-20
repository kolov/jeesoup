package com.akolov.jeesoup.application.approach2;


import com.akolov.jeesoup.annotation.ConfigurationValueProducer;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

/**
 * Custom CDI Producer to provide values annotated with {@link  MyConfigurationComplex }
 */
public class MyConfigurationComplexProducer {

    @Inject
    private ConfigurationValueProducer configurationInjector;

    @Produces
    @MyConfigurationComplex
    public String injectConfiguration(InjectionPoint ip) throws IllegalStateException {
        return configurationInjector.inject(ip, MyConfigurationComplex.class);
    }

}
