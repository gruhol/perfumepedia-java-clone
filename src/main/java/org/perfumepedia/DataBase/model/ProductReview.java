package org.perfumepedia.DataBase.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "product_review")
@EntityListeners(AuditingEntityListener.class)
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review")
    private Long idReview;
    @ManyToOne
    @JoinColumn(name= "id_user", insertable = true, updatable = true)
    private User user;
    @Column(name = "id_product")
    private Long idProduct;
    private Short score;
    @Column(name = "textreview")
    private String textReview;
    @CreatedDate
    @Column(name = "data_review")
    private Date dataReview;

    @Tolerate
    ProductReview() {}
}
