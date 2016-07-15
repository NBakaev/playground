/*
 * Copyright 2016 the original author or authors.
 */

package ru.nbakaev.interfaceimplement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 7/15/2016
 *         All Rights Reserved
 */
public class MicroserviceInterfaceImplementorFactory {

    private static final Logger logger = LoggerFactory.getLogger(MicroserviceInterfaceImplementorFactory.class);

    public static Object create(Class<?> aClass) {

        if (aClass.isInterface()) {
            List<Class<?>> classes = new ArrayList<>();
            classes.add(aClass);

            Object o = Enhancer.create(UserMicroserviceRequestSuperService.class, classes.toArray( new Class[classes.size()]), new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                    if (method.getName().equals("toString") || method.getName().equals("hashCode")) {
                        return methodProxy.invokeSuper(o, objects);
                    }
                    logger.debug("Start method {} in {}", method.getName(), o.toString());

                    List<String> result = new ArrayList<>();
                    result.add("Ivan");
                    result.add("Petr");

                    logger.debug("End method {} in {}", method.getName(), o.toString());
                    return result;
                }
            });
            return o;
        }
        return null;

    }

    public MicroserviceInterfaceImplementorFactory() {
    }
}
