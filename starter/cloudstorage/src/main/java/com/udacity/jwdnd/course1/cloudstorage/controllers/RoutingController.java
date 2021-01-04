package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.credential.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.file.FileService;
import org.springframework.security.core.Authentication;
import com.udacity.jwdnd.course1.cloudstorage.services.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RoutingController {

    @Autowired
    private FileService fileService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private EncryptionService encryptionService;

    @GetMapping("/login")
    private String login() {
        return "login.html";
    }

    @GetMapping("/home")
    private String home(Model model, Authentication authentication) {
        model.addAttribute("files", fileService.getFiles(authentication.getName()));
        model.addAttribute("notes", noteService.fetchAllNotes(authentication.getName()));
        List<Credential> credentials = credentialService.fetchAllCredentials(authentication.getName());
        for (Credential cred: credentials) {
            cred.setDecrypted_password(encryptionService.decryptValue(cred.getPassword(), cred.getKey()));
        }
        model.addAttribute("credentials", credentials);
        return "home.html";
    }

    @GetMapping("/signup")
    private String signup() {
        return "signup.html";
    }

    @GetMapping("/result")
    private String result() {
        return "result.html";
    }
}
