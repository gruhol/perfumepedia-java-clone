package org.perfumepedia.DataBase.repository;

import org.perfumepedia.DataBase.model.CategoryNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryNoteRepository extends JpaRepository<CategoryNote, Long> {
}
