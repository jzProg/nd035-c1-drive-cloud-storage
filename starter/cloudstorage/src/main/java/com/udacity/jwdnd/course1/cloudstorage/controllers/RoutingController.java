package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutingController {

    @GetMapping("/login")
    private String login() {
        return "login.html";
    }

    @GetMapping("/home")
    private String home() {
        return "home.html";
    }

    @GetMapping("/signup")
    private String signup() {
        return "signup.html";
    }
}
