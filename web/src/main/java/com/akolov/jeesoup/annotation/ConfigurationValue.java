package com.akolov.jeesoup.annotation;

import javax.enterprise.util.Nonbinding;
import javax.inject.Inject;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigurationValue {

    @Nonbinding String key() default "";

    @Nonbinding boolean mandatory() default false;

    @Nonbinding String defaultValue() default "";
}