package com.akolov.jeesoup.application;

import com.akolov.jeesoup.annotation.ApplicationConfiguration;

import javax.enterprise.util.Nonbinding;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ApplicationConfiguration(applicationKey ="XXX")
public @interface MyConfiguration {

}