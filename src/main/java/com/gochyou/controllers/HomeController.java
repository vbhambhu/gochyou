package com.gochyou.controllers;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private Facebook facebook;
    private ConnectionRepository connectionRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(){

        return "home";

    }

    @RequestMapping(value = "/connect/faceboosk", method = RequestMethod.GET)
    public String showFB(){

        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/ss";
        }

        return "redirect:/messages";

    }
}
