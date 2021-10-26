package org.perfumepedia.DataBase.service;

import org.perfumepedia.DataBase.model.ProductImage;
import org.perfumepedia.DataBase.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {

    @Autowired
    ProductImageRepository productImageRepository;

    public ProductImage getMainImagesByIdProduct(Long id) {
        return productImageRepository.findByIdProduct(id).stream()
                .filter(img -> img.isMainImage() == true)
                .findFirst().get();
    }
}
