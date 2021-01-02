package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/deleteNote")
    public String deleteNote(@RequestParam(value = "noteId") Integer noteId, Model model) {
        int rows = noteService.deleteNote(noteId);
        if (rows < 0) {
            model.addAttribute("error", true);
        }
        return "result";
    }

    @PostMapping("/addNote")
    public String addNote(@ModelAttribute Note note, Model model, Authentication authentication) {
        int rows = noteService.addNote(note, authentication.getName());
        if (rows < 0) {
            model.addAttribute("error", true);
        }
        return "result";
    }
}
