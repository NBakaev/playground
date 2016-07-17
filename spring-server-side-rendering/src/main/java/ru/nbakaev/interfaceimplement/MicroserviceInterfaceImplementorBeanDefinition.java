/*
 * Copyright 2016 the original author or authors.
 */

package ru.nbakaev.interfaceimplement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;
import ru.nbakaev.interfaceimplement.annotation.EnableMicroserviceCommunicator;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 7/15/2016
 *         All Rights Reserved
 */
@Component
public class MicroserviceInterfaceImplementorBeanDefinition implements BeanDefinitionRegistryPostProcessor{

    private static final Logger logger = LoggerFactory.getLogger(MicroserviceInterfaceImplementorBeanDefinition.class);

    protected void scanPackage(String basePackage, BeanDefinitionRegistry beanDefinitionRegistry){

        ClassPathScanningCandidateComponentMicroserviceInterfaceProvider provider = new ClassPathScanningCandidateComponentMicroserviceInterfaceProvider();
        Set<BeanDefinition> components = provider.findCandidateComponents(basePackage);
        for (BeanDefinition component : components) {
            String interfaceClassName = component.getBeanClassName();
            try {
                Class<?> aClass = Class.forName(interfaceClassName);
                Object o = MicroserviceInterfaceImplementorFactory.create(aClass);

                String beanName = o.getClass().getName();
                GenericBeanDefinition definition = new GenericBeanDefinition();
                definition.setScope("singleton");
                definition.setBeanClass(o.getClass());

                beanDefinitionRegistry.registerBeanDefinition(beanName, definition);
                logger.info("Find microservice interface {}", interfaceClassName);
            } catch (Exception e) {
                logger.error("Error init dynamic microservice {}", interfaceClassName, e);
            }
        }

    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        boolean findRequireAnnotation = false;
        boolean resolveBasePackage = false;

        for (String beanDefinitionName : beanDefinitionRegistry.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition(beanDefinitionName);

            // this is a @Configuration bean
            if ("full".equals(beanDefinition.getAttribute("org.springframework.context.annotation.ConfigurationClassPostProcessor.configurationClass"))){
                logger.debug("Find configuration file {}", beanDefinitionName);
                try {
                    Class aClass = Class.forName(beanDefinition.getBeanClassName());
                    Annotation declaredAnnotation = aClass.getDeclaredAnnotation(EnableMicroserviceCommunicator.class);
                    if (declaredAnnotation != null && declaredAnnotation instanceof EnableMicroserviceCommunicator){
                        findRequireAnnotation = true;
                        String[] basePackages = ((EnableMicroserviceCommunicator) declaredAnnotation).basePackages();

                        if (basePackages.length > 0){
                            for (String basePackage : basePackages){
                                resolveBasePackage = true;
                                scanPackage(basePackage, beanDefinitionRegistry);
                            }
                        }else{
                            logger.warn("Found @EnableMicroserviceCommunicator but not found basePackage attribute");
                        }
                    }else{
                        // we have not EnableMicroserviceCommunicator annotation
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!findRequireAnnotation){
            logger.info("Not find @EnableMicroserviceCommunicator annotation on configuration class. Skipping scanning interfaces");
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

}
