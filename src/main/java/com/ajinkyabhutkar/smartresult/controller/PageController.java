package com.ajinkyabhutkar.smartresult.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {


    @RequestMapping
    public String index(HttpServletRequest request){

//        HttpSession session=request.getSession();
//        session.setAttribute("Ajinkya Bhutkar","Baramati");
        System.out.println("Welcome to index page");

        return "index";
    }

    @GetMapping("/login-page")
    public String login(){

        System.out.println(" on login page");
        return "login_page";
    }

}
