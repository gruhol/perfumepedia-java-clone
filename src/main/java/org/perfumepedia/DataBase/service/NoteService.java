package org.perfumepedia.DataBase.service;

import org.perfumepedia.DataBase.model.CategoryNote;
import org.perfumepedia.DataBase.model.CategoryNoteWithNote;
import org.perfumepedia.DataBase.model.Note;
import org.perfumepedia.DataBase.repository.CategoryNoteRepository;
import org.perfumepedia.DataBase.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private CategoryNoteRepository categoryNoteRepository;

    public List<CategoryNoteWithNote> getAllNoteWithCategory() {
        List<Note> notes = noteRepository.findAll();
        List<CategoryNote> categoryNotes = categoryNoteRepository.findAll();
        List<CategoryNoteWithNote> categoryNoteWithNoteList = categoryNotes.stream()
                .map(categoryNote -> mapToCategoryNoteWithNote(categoryNote, notes))
                .collect(Collectors.toList());
        return categoryNoteWithNoteList;
    }

    private CategoryNoteWithNote mapToCategoryNoteWithNote(CategoryNote categoryNote, List<Note> notes) {
        return CategoryNoteWithNote.builder()
                .categoryNote(categoryNote)
                .noteList(notes.stream()
                        .filter(note -> note.getCategory().getIdCategory() == categoryNote.getIdCategory())
                        .collect(Collectors.toList()))
                .build();
    }


    public Note getNoteById(Long idNote) {
        return noteRepository.getById(idNote);
    }
}
