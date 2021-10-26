package org.perfumepedia.DataBase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_image", nullable = false)
    private Long idImage;
    @Column(name = "id_product")
	private Long idProduct;
	@Column(name = "main_image", nullable = false)
	private boolean mainImage;
	@Column(name = "url_image", nullable = false)
	private String urlImage;
	@Column(name = "alt_image", nullable = false)
	private String altImage;
}
