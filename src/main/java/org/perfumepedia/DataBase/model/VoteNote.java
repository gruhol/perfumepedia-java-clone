package org.perfumepedia.DataBase.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Data
@Builder
@Entity
public class VoteNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vote_note")
    private Long idVoteNote;
    @Column(name = "id_product")
    private Long idProduct;
    @Column(name = "id_note")
    private Long idNote;
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "vote_value")
    private Integer voteValue;

    @Tolerate
    VoteNote() {}
}
