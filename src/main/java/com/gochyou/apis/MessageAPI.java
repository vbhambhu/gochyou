package com.gochyou.apis;


import com.gochyou.models.Message;
import com.gochyou.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageAPI {

    @Autowired
    MessageService messageService;


    @ResponseBody
    @RequestMapping(value="/api/messages", method= RequestMethod.GET)
    public List<Message> getMessages() {

        int userId = 1;

        return messageService.getMessagesByUserId(userId);
    }


}
