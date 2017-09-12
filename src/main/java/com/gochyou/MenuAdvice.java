package com.gochyou;

import com.gochyou.models.KeyValue;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class MenuAdvice {

    @ModelAttribute
    public void addAttributes(Model model) {

        List<KeyValue> menu = new ArrayList<KeyValue>();
        menu.add(new KeyValue("/", "Home"));
        menu.add(new KeyValue("/how-it-works", "How it works?"));
        menu.add(new KeyValue("/who-we-are", "Who we are?"));
        menu.add(new KeyValue("/contact", "Contact"));



        model.addAttribute("menus", menu);


        List<KeyValue> langs = new ArrayList<KeyValue>();

        langs.add(new KeyValue("?lang=en", "English"));
        langs.add(new KeyValue("?lang=it", "Italian"));
        langs.add(new KeyValue("?lang=es", "Spanish"));

        model.addAttribute("langs", langs);



    }
}
