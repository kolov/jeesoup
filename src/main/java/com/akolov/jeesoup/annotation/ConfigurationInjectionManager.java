package com.akolov.jeesoup.annotation;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.text.MessageFormat;
import java.util.Properties;

public class ConfigurationInjectionManager {
    static final String MANDATORY_PARAM_MISSING = "No definition found for a mandatory configuration parameter : '{0}'";

    private Properties props = new Properties();

    @Produces
    @InjectedConfiguration
    public String injectConfiguration(InjectionPoint ip) throws IllegalStateException {
        InjectedConfiguration param = ip.getAnnotated().getAnnotation(InjectedConfiguration.class);

        String value = props.getProperty(param.key(), param.defaultValue());
        if (value == null || value.trim().length() == 0) {
            if (param.mandatory()) {
                throw new IllegalStateException(MessageFormat.format(MANDATORY_PARAM_MISSING, new Object[]{param.key()}));
            }
        }
        return value;
    }

}
