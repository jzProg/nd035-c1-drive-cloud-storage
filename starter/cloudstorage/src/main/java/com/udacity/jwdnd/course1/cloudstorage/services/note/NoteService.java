package com.udacity.jwdnd.course1.cloudstorage.services.note;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;

import java.util.List;

public interface NoteService {
    List<Note> fetchAllNotes(String username);
    int deleteNote(Integer noteId);
    int addNote(Note note, String username);
}
