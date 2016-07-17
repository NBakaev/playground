/*
 * Copyright 2016 the original author or authors.
 */

package ru.nbakaev.interfaceimplement;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import ru.nbakaev.interfaceimplement.annotation.MicroserviceRequest;

/**
 * Find all interfaces that extends {@link MicroserviceRequest} in requested basePackage
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 7/17/2016
 *         All Rights Reserved
 */
public class ClassPathScanningCandidateComponentMicroserviceInterfaceProvider extends ClassPathScanningCandidateComponentProvider {

    public ClassPathScanningCandidateComponentMicroserviceInterfaceProvider() {
        super(false);
        addIncludeFilter(new AnnotationTypeFilter(MicroserviceRequest.class, false));
    }

    /**
     * Search for interface
     * @param beanDefinition
     * @return
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface();
    }
}
