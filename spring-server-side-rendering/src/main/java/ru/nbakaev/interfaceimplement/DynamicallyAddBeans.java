/*
 * Copyright 2016 the original author or authors.
 */

package ru.nbakaev.interfaceimplement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 7/15/2016
 *         All Rights Reserved
 */
@Component
public class DynamicallyAddBeans implements BeanDefinitionRegistryPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(DynamicallyAddBeans.class);

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

        String className = "ru.nbakaev.interfaceimplement.example.UserMicroserviceRequest";

        try {
            Class<?> aClass = Class.forName(className);
            Object o = MicroserviceInterfaceImplementorFactory.create(aClass);

            String beanName = o.getClass().getName();
            GenericBeanDefinition definition = new GenericBeanDefinition();
            definition.setScope("singleton");
            definition.setBeanClass(o.getClass());

            beanDefinitionRegistry.registerBeanDefinition(beanName, definition);

        } catch (Exception e) {
            logger.error("Error init dynamic microservice {}", className, e);
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
