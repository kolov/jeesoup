package com.akolov.jeesoup.application;

import com.akolov.jeesoup.annotation.ApplicationConfiguration;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ApplicationConfiguration(applicationKey = "XXX")
@Qualifier()
public @interface MyConfigurationComplex {

    @Nonbinding String key() default "";

    @Nonbinding boolean mandatory() default false;

    @Nonbinding String defaultValue() default "";

}