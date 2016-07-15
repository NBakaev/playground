/*
 * Copyright 2016 the original author or authors.
 */

package ru.nbakaev.interfaceimplement;

import org.springframework.stereotype.Component;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 7/15/2016
 *         All Rights Reserved
 */
@Retention(RetentionPolicy.RUNTIME)
@Component
@Inherited
public @interface MicroserviceRequest {
}