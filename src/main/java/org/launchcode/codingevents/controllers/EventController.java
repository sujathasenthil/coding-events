package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {
    //now we changed List<String> as List<Events>, and then create a new package(model)  by right clicking org.launcode.codingevents and and right click(models) again to create new java class(Event).
    private  static List<Event> events= new ArrayList<>();
// previously we passed string,in list,like this   private  static List<String> events= new ArrayList<>();
    //localhost/events display all the events in the page
    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("events",events);
        return "events/index";
    }
    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(){
        return "events/create";
    }
    //lives at /events/create
    @PostMapping("create")
//    public String createEvent(@RequestParam String eventName, @RequestParam String eventDesc){
//        events.add(new Event(eventName,eventDesc));
    public String createEvent(@RequestParam String eventName){
        events.add(new Event(eventName));
        return "redirect:/";
    }
}
