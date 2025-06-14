package com.ajinkyabhutkar.smartresult.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {


    @RequestMapping
    public String index(){
        System.out.println("Welcome to index page");

        return "index";
    }

}
