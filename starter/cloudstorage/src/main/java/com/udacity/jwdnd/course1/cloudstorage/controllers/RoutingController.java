package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.security.core.Authentication;
import com.udacity.jwdnd.course1.cloudstorage.services.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutingController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/login")
    private String login() {
        return "login.html";
    }

    @GetMapping("/home")
    private String home(Model model, Authentication authentication) {
        model.addAttribute("notes", noteService.fetchAllNotes(authentication.getName()));
        return "home.html";
    }

    @GetMapping("/signup")
    private String signup() {
        return "signup.html";
    }
}
