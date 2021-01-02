package com.udacity.jwdnd.course1.cloudstorage.services.note;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<Note> fetchAllNotes(String username) {
        User user = userService.getUser(username);
        return noteMapper.getNotes(user.getUserId());
    }

    @Override
    public int deleteNote(Integer noteId) {
        return noteMapper.deleteNote(noteId);
    }

    @Override
    public int addNote(Note note, String username) {
        User user = userService.getUser(username);
        if (note.getNoteid() != null) {
            return noteMapper.editNote(note);
        }
        note.setUserid(user.getUserId());
        return noteMapper.insertNote(note);
    }
}
