package com.akolov.jeesoup.application.approach2;

import com.akolov.jeesoup.annotation.ApplicationConfiguration;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Custom annotation for an application.
 * <p/>
 * It provides applictionKey through the ApplicationConfiguration   annotation and
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@ApplicationConfiguration(applicationKey = "XXX")
@Qualifier()
public @interface MyConfigurationComplex {

    @Nonbinding String key() default "";

    @Nonbinding boolean mandatory() default false;

    @Nonbinding String defaultValue() default "";

}