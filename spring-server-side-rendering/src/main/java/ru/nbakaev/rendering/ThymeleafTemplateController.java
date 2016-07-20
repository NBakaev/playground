package ru.nbakaev.rendering;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a demo of usage server-side rendering with thymeleaf template
 *
 * Created by Nikita Bakaev, ya@nbakaev.ru on 4/6/2016.
 * All Rights Reserved
 */
@Controller
public class ThymeleafTemplateController {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {

        List<String> stringList = new ArrayList<>();
        stringList.add("First");
        stringList.add("Second");
        stringList.add("Third");
        stringList.add("Fourth");

        model.addAttribute("lists", stringList);
        model.addAttribute("name", name);

        return "greeting";
    }

}