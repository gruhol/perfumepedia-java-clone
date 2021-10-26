package org.perfumepedia.DataBase.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryNoteWithNote {
    private CategoryNote categoryNote;
    private List<Note> noteList;
}
