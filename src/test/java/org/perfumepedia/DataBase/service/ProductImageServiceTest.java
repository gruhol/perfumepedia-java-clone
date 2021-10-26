package org.perfumepedia.DataBase.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.perfumepedia.DataBase.model.ProductImage;
import org.perfumepedia.DataBase.repository.ProductImageRepository;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductImageServiceTest {

    @Mock
    private ProductImageRepository productImageRepository;

    @InjectMocks
    private ProductImageService productImageService;

    @Test
    void shouldSetMainImagesByIdProduct() {
        //given
        Mockito.when(productImageRepository.findByIdProduct(1L)).thenReturn(Arrays.asList(
                ProductImage.builder()
                        .idImage(1L)
                        .idProduct(1L)
                        .mainImage(true)
                        .urlImage("url_image1")
                        .altImage("alt_image1")
                        .build(),
                ProductImage.builder()
                        .idImage(2L)
                        .idProduct(1L)
                        .mainImage(false)
                        .urlImage("url_image2")
                        .altImage("alt_image2")
                        .build(),
                ProductImage.builder()
                        .idImage(3L)
                        .idProduct(1L)
                        .mainImage(false)
                        .urlImage("url_image2")
                        .altImage("alt_image2")
                        .build()
        ));
        //when
        ProductImage productImage = productImageService.getMainImagesByIdProduct(1L);
        //then
        assertThat(productImage.isMainImage()).isTrue();
        assertThat(productImage.getIdProduct()).isEqualTo(1L);
    }

}