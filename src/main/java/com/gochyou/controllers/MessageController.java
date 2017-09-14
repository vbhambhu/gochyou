package com.gochyou.controllers;

import com.gochyou.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
    public String showMessage(Model model){

        model.addAttribute("conversations", messageService.getConversationList(1));

        return "chat";

    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String showMessages(Model model){

        model.addAttribute("conversations", messageService.getConversationList(1));

        return "messages";

    }
}
