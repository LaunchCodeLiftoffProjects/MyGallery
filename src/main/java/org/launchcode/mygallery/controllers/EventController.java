package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.Event;
import org.launchcode.mygallery.GeneralUser;
import org.launchcode.mygallery.data.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    UserRegistrationAuthenticationController authenticationController;

    @GetMapping("create")
    public String displayCreateEventForm(Model model, HttpServletRequest request) {
        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);

        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
                                         Errors errors, Model model, HttpServletRequest request) {
        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);

        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }

        eventRepository.save(newEvent);
        return "redirect:index";
    }

    @GetMapping("index")
    public String displayEvents(Model model, HttpServletRequest request) {
        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);

            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
            return "events/index";
    }

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer eventId, Model model, HttpServletRequest request) {
        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);

        Optional<Event> result = eventRepository.findById(eventId);

            Event event = result.get();
            model.addAttribute("title", event.getTitleSummary() + " Details");
            model.addAttribute("event", event);
            return "events/detail";
    }
}
