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

    public static Object create(Class<?> interfaceToExtend) {
        if (interfaceToExtend.isInterface()) {
            List<Class<?>> extendInterfaces = new ArrayList<>();
            extendInterfaces.add(interfaceToExtend);

            Object extendedInterface = Enhancer.create(UserMicroserviceRequestSuperService.class, extendInterfaces.toArray( new Class[extendInterfaces.size()]), new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                    // do not proxy toString and hashCode - invoke super class methods
                    if (method.getName().equals("toString") || method.getName().equals("hashCode")) {
                        return methodProxy.invokeSuper(o, objects);
                    }
                    logger.debug("Start microservice method {} in {}", method.getName(), o.toString());

                    List<String> result = new ArrayList<>();
                    result.add("Ivan");
                    result.add("Petr");

                    logger.debug("End microservice method {} in {}", method.getName(), o.toString());
                    return result;
                }
            });
            return extendedInterface;
        }
        return null;
    }

    public MicroserviceInterfaceImplementorFactory() {
    }
}
