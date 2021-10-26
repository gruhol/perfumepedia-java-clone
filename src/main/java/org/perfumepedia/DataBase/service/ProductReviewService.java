package org.perfumepedia.DataBase.service;

import org.perfumepedia.DataBase.model.ProductReview;
import org.perfumepedia.DataBase.model.User;
import org.perfumepedia.DataBase.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductReviewService {
    @Autowired
    private ProductReviewRepository productReviewRepository;

    public ProductReview findProductReviewByIdUserAndIdProduct(User user, Long idProduct) {
        return productReviewRepository.findByUserAndIdProduct(user, idProduct);
    }

    public ProductReview saveProductReviewByUser(ProductReview productReview) {
        return productReviewRepository.save(productReview);
    }

    public Double getAverageScoreFromReviewUser(Long idProduct) {
        return productReviewRepository.getAverageScoreFromReviewUser(idProduct);
    }

    public List<ProductReview> getProductReviewsByIdProduct(Long idProduct) {
        return productReviewRepository.findByIdProduct(idProduct);
    }

}
