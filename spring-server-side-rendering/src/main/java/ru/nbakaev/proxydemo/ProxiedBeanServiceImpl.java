/*
 * Copyright 2016 the original author or authors.
 */

package ru.nbakaev.proxydemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 6/7/2016
 *         All Rights Reserved
 */
@Service
public class ProxiedBeanServiceImpl implements ProxiedBeanService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @MyProxyMethodCall
    @Override
    public void sayHello(){
        logger.info("Hello from sayHello");
    }

}
