package com.gochyou.controllers;

import com.gochyou.models.Message;
import com.gochyou.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

@Controller
public class HomeController {

    @Autowired
    MessageService messageService;

    @Autowired
    private MessageSource messageSource;

    private Facebook facebook;
    private ConnectionRepository connectionRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(Locale locale, HttpSession httpSession){

        // add parametrized message from controller
        String welcome = messageSource.getMessage("welcome.message", new Object[]{"John Doe"}, locale);




        System.out.println(welcome);

        if(httpSession.getAttribute("isLoggedIn") != null){
            return "redirect:/messages";
        }

        return "home";

    }



    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String showHome(){

        List<Message> message = messageService.getMessages();

        System.out.println(message.size());

        return "test";

    }



}
