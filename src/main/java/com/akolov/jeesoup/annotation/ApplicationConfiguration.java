package com.akolov.jeesoup.annotation;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationConfiguration {
    /**
     * Bundle key
     * @return a valid bundle key or ""
     */
    @Nonbinding String applicationKey();

}