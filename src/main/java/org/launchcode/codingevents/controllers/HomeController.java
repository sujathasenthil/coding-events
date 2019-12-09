package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //just by entering localhost/ we can see the index page
@GetMapping
    public String index(){
    return "index";
}
}
