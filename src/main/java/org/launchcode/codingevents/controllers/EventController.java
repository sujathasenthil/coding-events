package org.launchcode.codingevents.controllers;

//import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.data.TagRepository;
import org.launchcode.codingevents.models.*;
//import org.launchcode.codingevents.models.EventTypes;
import org.launchcode.codingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId, Model model){
        if (categoryId == null) {
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
        } else {
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Category ID: " + categoryId);
            } else {
                EventCategory category = result.get();
                model.addAttribute("title", "Events in category: " + category.getName());
                model.addAttribute("events", category.getEvents());
            }
        }
        return "events/index";
            }

    @GetMapping("create")
    public String displayCreateEventForm(Model model){
        model.addAttribute("title","Create Event");
        model.addAttribute(new Event());
    //    model.addAttribute("types",EventTypes.values());
        model.addAttribute("categories",eventCategoryRepository.findAll());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title","Create Event");
           // model.addAttribute("errorMsg","BadData!");
            return "events/create";
        }
        eventRepository.save(newEvent);

        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEvent(Model model){
        model.addAttribute("title","Delete Event");
        model.addAttribute("events",eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String displayprocessDeleteEventForm(@RequestParam(required = false) int[] eventIds){
        if (eventIds!= null){
        for(int id : eventIds){
            eventRepository.deleteById(id);
        }}
        return("redirect:");
    }

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer eventId, Model model) {

        Optional<Event> result = eventRepository.findById(eventId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Event ID: " + eventId);
        } else {
            Event event = result.get();
            model.addAttribute("title", event.getName() + " Details");
            model.addAttribute("event", event);
            model.addAttribute("tags",event.getTags());
        }

        return "events/detail";
    }

    //responds to /events/add-tag?eventId=13
    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model){
        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add Tag to: " + event.getName());
        model.addAttribute("tags", tagRepository.findAll());
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag", eventTag);
        return "events/add-tag.html";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag,
                                    Errors errors,
                                    Model model){

        if (!errors.hasErrors()) {
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            if (!event.getTags().contains(tag)){
                event.addTag(tag);
                eventRepository.save(event);
            }
            return "redirect:detail?eventId=" + event.getId();
        }

        return "redirect:add-tag";
    }
    @GetMapping("edit/{eventId}")
//    public String displayEditForm(Model model,@PathVariable int eventId) {
//        model.addAttribute(new AbstractEntity());
    public String displayEditForm(Model model,@PathVariable int eventId) {
        Optional<Event> event=eventRepository.findById(eventId);
        model.addAttribute("event",eventRepository.findById(eventId));
        model.addAttribute("title", "EditEvent");
     //   model.addAttribute("types",EventTypes.values());
      model.addAttribute("categories",eventCategoryRepository.findAll());
        return "events/edit";
    }

     @PostMapping("edit")
        public String processEditForm(Event eventVal,@RequestParam int eventId,EventDetails eventDetails)
    //@RequestParam String name,@RequestParam String description,@RequestParam String contactEmail,@RequestParam String location,@RequestParam boolean register,@RequestParam int numberOfAttendees,@RequestParam Date dateOfParticipation)
        {
           Optional<Event> event=eventRepository.findById(eventId);
            if(event.isPresent())
                eventVal=event.get();
           // eventRepository.findById(eventId);
                eventVal.setEventDetails(eventDetails);
//                   eventVal.setEventDetails(eventDetails);
//                eventVal.setEventDetails(eventDetails);
//                eventVal.setDateOfParticipation(dateOfParticipation);
//                eventVal.setLocation(location);
//                eventVal.setNumberOfAttendees(numberOfAttendees);
//                eventVal.setRegister(register);

            eventRepository.save(eventVal);
            return "redirect: ";
    }

    }

