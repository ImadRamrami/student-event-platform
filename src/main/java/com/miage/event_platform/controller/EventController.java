package com.miage.event_platform.controller;

import com.miage.event_platform.model.Event;
import com.miage.event_platform.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events/new")
    public String showEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "event_form";
    }

    @PostMapping("/events")
    public String saveEvent(@ModelAttribute Event event) {
        eventService.createEvent(event);
        return "redirect:/";
    }

    @GetMapping("/events/{id}")
    public String getEvent(@org.springframework.web.bind.annotation.PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "event_details";
    }
}
