package com.akolov.jeesoup.application;

import com.akolov.jeesoup.annotation.ApplicationConfiguration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ApplicationConfiguration(applicationKey ="application1/sdsd")
public @interface MyAppConfiguration {

}