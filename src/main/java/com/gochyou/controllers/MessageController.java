package com.gochyou.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MessageController {


    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String showMessages(){

        return "messages";

    }
}
