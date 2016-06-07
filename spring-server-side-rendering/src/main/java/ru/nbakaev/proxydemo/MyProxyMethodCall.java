/*
 * Copyright 2016 the original author or authors.
 */

package ru.nbakaev.proxydemo;

/**
 * @author Nikita Bakaev, ya@nbakaev.ru
 * Date: 6/7/2016
 * All Rights Reserved
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {METHOD})
public @interface MyProxyMethodCall {
}
