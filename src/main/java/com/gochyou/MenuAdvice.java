package com.gochyou;

import com.gochyou.models.KeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;

@ControllerAdvice
public class MenuAdvice {

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute
    public void addAttributes(Locale locale, Model model) {

        String home = messageSource.getMessage("menu.home", new Object[]{}, locale);
        String howitwork = messageSource.getMessage("menu.howitwork", new Object[]{}, locale);
        String whoweare = messageSource.getMessage("menu.whoweare", new Object[]{}, locale);
        String contact = messageSource.getMessage("menu.contact", new Object[]{}, locale);




        List<KeyValue> menu = new ArrayList<KeyValue>();
        menu.add(new KeyValue("/", home));
        menu.add(new KeyValue("/how-it-works", howitwork));
        menu.add(new KeyValue("/who-we-are", whoweare));
        menu.add(new KeyValue("/contact", contact));



        model.addAttribute("menus", menu);


        List<KeyValue> langs = new ArrayList<KeyValue>();

        langs.add(new KeyValue("?lang=en", "English"));
        langs.add(new KeyValue("?lang=it", "Italian"));
        langs.add(new KeyValue("?lang=es", "Spanish"));

        model.addAttribute("langs", langs);



    }
}
