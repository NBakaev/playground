/*
 * Copyright 2016 the original author or authors.
 */

package ru.nbakaev.jdbc.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.nbakaev.jdbc.Orders;

import java.util.List;

/**
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 7/20/2016
 *         All Rights Reserved
 */
@Controller
@RequestMapping("/jpa")
public class SpringDataRestController {

    @Autowired
    private OrdersJPARepository ordersJPARepository;

    private static final Logger logger = LoggerFactory.getLogger(SpringDataRestController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Orders> greeting() {
        return ordersJPARepository.findAll();
    }

}
