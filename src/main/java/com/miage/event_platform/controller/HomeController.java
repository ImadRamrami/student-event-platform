package com.miage.event_platform.controller;

import com.miage.event_platform.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "home";
    }
}
