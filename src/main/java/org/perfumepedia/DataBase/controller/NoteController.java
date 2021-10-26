package org.perfumepedia.DataBase.controller;

import org.perfumepedia.DataBase.repository.NoteRepository;
import org.perfumepedia.DataBase.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NoteController {

    public static final String NOTE = "note";
    public static final String NOTES = "notes";
    @Autowired
    private NoteService noteService;

    @GetMapping("notes")
    public String showAllNotesWithCategory(Model model) {
        model.addAttribute(NOTES, noteService.getAllNoteWithCategory());
        return "notes/notes";
    }

    @GetMapping("note/{idNote}")
    public String showNote(@PathVariable(value = "idNote", required = true) Long idNote, Model model) {
        model.addAttribute(NOTE, noteService.getNoteById(idNote));
        return "notes/note";
    }
}
