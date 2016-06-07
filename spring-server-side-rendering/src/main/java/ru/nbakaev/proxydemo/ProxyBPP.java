/*
 * Copyright 2016 the original author or authors.
 */

package ru.nbakaev.proxydemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 6/7/2016
 *         All Rights Reserved
 */
@Component
public class ProxyBPP implements BeanPostProcessor {

    private Map<String, Class> map = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();

        for (Method method : aClass.getMethods()) {
            if (method.isAnnotationPresent(MyProxyMethodCall.class)) {
                map.put(beanName, aClass);
                break;
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = map.get(beanName);

        if (beanClass != null) {

            return Enhancer.create(beanClass, new org.springframework.cglib.proxy.MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    if (method.isAnnotationPresent(MyProxyMethodCall.class)) {
                        logger.info("Start method {}", method.getName());
                        Object returnValue = method.invoke(bean, objects);
                        logger.info("End method {}", method.getName());
                        return returnValue;
                    } else {
                        return method.invoke(bean, objects);
                    }
                }
            });

//            return java.lang.reflect.Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
//                @Override
//                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                    Method implementingBeanMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
//                    if (implementingBeanMethod.isAnnotationPresent(MyProxyMethodCall.class)) {
//                        logger.info("Start method {}", implementingBeanMethod.getName());
//                        Object returnValue = method.invoke(bean, args);
//                        logger.info("End method {}", implementingBeanMethod.getName());
//                        return returnValue;
//                    } else {
//                        return method.invoke(bean, args);
//                    }
//                }
//            });
        }
        return bean;
    }
}