package ru.nbakaev.interfaceimplement.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Nikita Bakaev, ya@nbakaev.ru on 4/6/2016.
 * All Rights Reserved
 */
@Controller
public class ImplementInterfaceController {

    @Autowired
    private UserMicroserviceRequest userMicroserviceRequest;

    @RequestMapping(value = "/implement_interface/users", method = RequestMethod.GET)
    public
    @ResponseBody
    List<String> greeting() {
       return userMicroserviceRequest.getAlluserIds();
    }

}