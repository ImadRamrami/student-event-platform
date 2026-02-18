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

    @GetMapping("/")
    public String index(Model model, java.security.Principal principal) {
        model.addAttribute("events", eventService.getAllEvents());
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("myEvents", eventService.getEventsByOrganizer(username));
            model.addAttribute("attendedEvents", eventService.getEventsRegisteredBy(username));
        }
        return "index";
    }

    @GetMapping("/events/new")
    public String showEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "event_form";
    }

    @PostMapping("/events")
    public String saveEvent(@ModelAttribute Event event, java.security.Principal principal) {
        eventService.createEvent(event, principal.getName());
        return "redirect:/";
    }

    @GetMapping("/events/{id}")
    public String getEvent(@org.springframework.web.bind.annotation.PathVariable Long id, Model model,
            java.security.Principal principal) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        if (principal != null) {
            String username = principal.getName();
            boolean isRegistered = event.getAttendees().stream()
                    .anyMatch(user -> user.getUsername().equals(username));
            model.addAttribute("isRegistered", isRegistered);
            model.addAttribute("currentUser", username);
        }
        return "event_details";
    }

    @PostMapping("/events/{id}/register")
    public String registerForEvent(@org.springframework.web.bind.annotation.PathVariable Long id,
            java.security.Principal principal,
            org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {

        try {
            boolean success = eventService.registerUser(id, principal.getName());
            if (success) {
                redirectAttributes.addFlashAttribute("success", "Inscription validée !");
            } else {
                redirectAttributes.addFlashAttribute("error",
                        "Échec de l'inscription : Vous êtes déjà inscrit ou l'événement est complet.");

            }
        } catch (Exception e) {
            handleException(e, redirectAttributes, "Error registering for event");
        }
        return "redirect:/events/" + id;
    }

    @PostMapping("/events/{id}/unregister")
    public String unregisterFromEvent(@org.springframework.web.bind.annotation.PathVariable Long id,
            java.security.Principal principal,
            org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {
        try {
            eventService.unregisterUser(id, principal.getName());
            redirectAttributes.addFlashAttribute("success", "Désinscription validée !");
        } catch (Exception e) {
            handleException(e, redirectAttributes, "Error unregistering from event");
        }
        return "redirect:/events/" + id;
    }

    private void handleException(Exception e,
            org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes, String logMessage) {
        org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(EventController.class);
        logger.error(logMessage, e);
        redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
    }
}
