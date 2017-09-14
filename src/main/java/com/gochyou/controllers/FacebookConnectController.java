package com.gochyou.controllers;


import com.gochyou.models.FacebookUser;
import com.gochyou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/connect")
public class FacebookConnectController extends ConnectController {


    @Autowired
    UserService userService;

    private Facebook facebook;
    private ConnectionRepository connectionRepository;


    public FacebookConnectController(Facebook facebook, ConnectionFactoryLocator connectionFactoryLocator,
                            ConnectionRepository connectionRepository) {
        super(connectionFactoryLocator, connectionRepository);

        this.facebook = facebook;
    }

    @Override
    protected String connectedView(String providerId) {

        String [] fields = { "id", "email",  "first_name", "last_name","gender" };
        FacebookUser facebookUser = facebook.fetchObject("me", FacebookUser.class, fields);
        userService.saveUser(facebookUser);




        return "redirect:/messages";
    }

}
