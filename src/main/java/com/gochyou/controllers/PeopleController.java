package com.gochyou.controllers;

import com.gochyou.models.Message;
import com.gochyou.models.People;
import com.gochyou.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PeopleController {

    @Autowired
    PeopleService peopleService;


    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public String showHome(){

        List<People> peoples = peopleService.searchPeople();

        return "people/list";

    }
}
