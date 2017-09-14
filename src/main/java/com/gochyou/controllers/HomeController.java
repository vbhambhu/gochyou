package com.gochyou.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(Authentication authentication){


        System.out.println(authentication);
      //  User user = (User) authentication.getPrincipal();
      //  System.out.println(user.getUsername());



        return "home";

    }

}
