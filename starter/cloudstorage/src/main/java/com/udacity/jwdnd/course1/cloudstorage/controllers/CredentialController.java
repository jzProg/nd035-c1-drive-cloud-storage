package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.credential.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/credential")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @GetMapping("/deleteCredential")
    public String deleteCredential(@RequestParam(value = "credentialId") Integer credentialId, Model model) {
        int rows = credentialService.deleteCredential(credentialId);
        if (rows < 0) {
            model.addAttribute("error", true);
        }
        return "result";
    }

    @PostMapping("/addCredential")
    public String addCredential(@ModelAttribute Credential credential, Model model, Authentication authentication) {
        int rows = credentialService.addCredential(credential, authentication.getName());
        if (rows < 0) {
            model.addAttribute("error", true);
        }
        return "result";
    }
}
