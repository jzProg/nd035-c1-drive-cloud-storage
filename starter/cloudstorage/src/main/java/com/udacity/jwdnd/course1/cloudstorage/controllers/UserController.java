package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.exceptions.UsernameExistsException;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    private String register(@ModelAttribute User user, Model model) {
        String error = null;
        try {
            int rows = userService.createNewUser(user);
            if (rows > 0) {
                model.addAttribute("success", true);
            } else {
                error = "Something went wrong...Please try again later";
            }
        } catch(UsernameExistsException e) {
          error = e.getMessage();
        }
        model.addAttribute("error", error);
        return "signup";
    }
}
