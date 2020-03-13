package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.Event;
import org.launchcode.mygallery.data.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
                                         Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }

        eventRepository.save(newEvent);
        return "redirect:index";
    }

    @GetMapping("index")
    public String displayEvents(Model model) {

            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
            return "events/index";
    }

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer eventId, Model model) {

        Optional<Event> result = eventRepository.findById(eventId);

            Event event = result.get();
            model.addAttribute("title", event.getTitleSummary() + " Details");
            model.addAttribute("event", event);
            return "events/detail";
    }
}
