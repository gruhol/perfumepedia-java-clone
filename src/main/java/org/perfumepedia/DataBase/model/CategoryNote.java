package org.perfumepedia.DataBase.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Data
@Entity
@Builder
public class CategoryNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category_note")
    private Long idCategory;
    @Column(name = "name_category")
    private String nameCategory;
    @Column(name = "category_description")
    private String categoryDescription;

    @Tolerate
    public CategoryNote(){}
}
