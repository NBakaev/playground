package ru.nbakaev.proxydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Nikita Bakaev, ya@nbakaev.ru on 4/6/2016.
 * All Rights Reserved
 */
@Controller
public class BeanController {

    @Autowired
    private ProxiedBeanService proxiedBeanService;

    @MyProxyMethodCall
    @RequestMapping(value = "/proxy/demo", method = RequestMethod.GET)
    public
    @ResponseBody void greeting() {
       proxiedBeanService.sayHello();
    }

}