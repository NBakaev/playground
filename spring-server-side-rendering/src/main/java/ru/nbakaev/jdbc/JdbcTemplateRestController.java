/*
 * Copyright 2016 the original author or authors.
 */

package ru.nbakaev.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 7/20/2016
 *         All Rights Reserved
 */
@Controller
@RequestMapping("/jdbc")
public class JdbcTemplateRestController {

    @Autowired
    private JdbcRepository jdbcRepository;

    private static final Logger logger = LoggerFactory.getLogger(JdbcTemplateRestController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public
    @ResponseBody
    Integer greeting() {
        return jdbcRepository.countCustomers() * jdbcRepository.countOrders();
    }

    @RequestMapping(value = "orders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Orders> getOrders() {
        List<Orders> orders = jdbcRepository.getOrders();
        logger.info("Get orders");
        return orders;
    }

}
