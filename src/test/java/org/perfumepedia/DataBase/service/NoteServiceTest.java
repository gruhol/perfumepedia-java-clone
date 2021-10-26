package org.perfumepedia.DataBase.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.perfumepedia.DataBase.model.CategoryNote;
import org.perfumepedia.DataBase.model.CategoryNoteWithNote;
import org.perfumepedia.DataBase.model.Note;
import org.perfumepedia.DataBase.repository.CategoryNoteRepository;
import org.perfumepedia.DataBase.repository.NoteRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;
    @Mock
    private CategoryNoteRepository categoryNoteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    void shouldSetNoteById() {
        //given
        CategoryNote categoryNote = CategoryNote.builder()
                .idCategory(1L)
                .nameCategory("Cytrusy")
                .categoryDescription("Opis...")
                .build();

        Mockito.when(noteRepository.getById(1L)).thenReturn(
            Note.builder()
                    .idNote(1L)
                    .nameNote("Cytryna")
                    .category(categoryNote)
                    .build()
        );
        //when
        Note note = noteService.getNoteById(1L);
        //then
        assertThat(note.getNameNote()).isSameAs("Cytryna");
        assertThat(note).isNotNull();
    }

    @Test
    void shouldSetCategoriesNoteWithNote() {
        //given
        Mockito.when(noteRepository.findAll()).thenReturn(Arrays.asList(
            Note.builder()
                    .idNote(1L)
                    .nameNote("Note1")
                    .category(CategoryNote.builder()
                            .idCategory(1L)
                            .nameCategory("Category1")
                            .build())
                    .build(),
            Note.builder()
                    .idNote(2L)
                    .nameNote("Note2")
                    .category(CategoryNote.builder()
                            .idCategory(2L)
                            .nameCategory("Category2")
                            .build())
                    .build(),
            Note.builder()
                    .idNote(3L)
                    .nameNote("Note2")
                    .category(CategoryNote.builder()
                            .idCategory(2L)
                            .nameCategory("Category2")
                            .build())
                    .build(),
            Note.builder()
                    .idNote(4L)
                    .nameNote("Note2")
                    .category(CategoryNote.builder()
                            .idCategory(3L)
                            .nameCategory("Category3")
                            .build())
                    .build()
        ));
        Mockito.when(categoryNoteRepository.findAll()).thenReturn(Arrays.asList(
            CategoryNote.builder()
                    .idCategory(1L)
                    .nameCategory("Category1")
                    .categoryDescription("Description1")
                    .build(),
            CategoryNote.builder()
                    .idCategory(2L)
                    .nameCategory("Category2")
                    .categoryDescription("Description2")
                    .build(),
            CategoryNote.builder()
                    .idCategory(3L)
                    .nameCategory("Category3")
                    .categoryDescription("Description3")
                    .build()
        ));
        //when
        List<CategoryNoteWithNote> categoryNoteWithNoteList = noteService.getAllNoteWithCategory();
        //then
        assertThat(categoryNoteWithNoteList).isNotNull();
        assertThat(categoryNoteWithNoteList).hasSize(3);
    }
}