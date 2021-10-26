package org.perfumepedia.DataBase.repository;

import org.perfumepedia.DataBase.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
