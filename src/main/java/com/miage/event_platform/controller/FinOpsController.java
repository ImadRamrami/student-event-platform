package com.miage.event_platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FinOpsController {

    @GetMapping("/finops")
    public String finOpsDashboard() {
        return "finops";
    }
}
