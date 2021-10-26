package org.perfumepedia.DataBase.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_note")
    private Long idNote;
    @ManyToOne
    @JoinColumn(name = "id_category_note", insertable = false, updatable = false)
    private CategoryNote category;
    @Column(name = "name_note")
    private String nameNote;
    @Column(name = "note_description")
    private String noteDescription;
    @OneToMany
    @JoinColumn(name = "id_note", insertable = false, updatable = false) /// sprawdzić
    private List<ProductNote> productNotes;

    @Tolerate
    public Note(){}
}
