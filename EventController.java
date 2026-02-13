package com.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {
    @PostMapping("/{id}/register")
    public String register(@PathVariable Long id) {
        return "Utilisateur inscrit à l'événement " + id;
    }
}