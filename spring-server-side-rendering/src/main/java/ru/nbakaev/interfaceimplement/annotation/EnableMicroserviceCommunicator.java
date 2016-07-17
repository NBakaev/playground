/*
 * Copyright 2016 the original author or authors.
 */

package ru.nbakaev.interfaceimplement.annotation;

import ru.nbakaev.interfaceimplement.MicroserviceInterfaceImplementorBeanDefinition;
import java.lang.annotation.*;

/**
 * Enable {@link MicroserviceInterfaceImplementorBeanDefinition}
 *
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 7/17/2016
 *         All Rights Reserved
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableMicroserviceCommunicator {

    String[] basePackages() default {""};

}
