package org.perfumepedia.DataBase.service;

import org.perfumepedia.DataBase.model.ProductNote;
import org.perfumepedia.DataBase.repository.ProductNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductNoteService {

    @Autowired
    private ProductNoteRepository productNoteRepository;

    public List<ProductNote> getProductNoteByIdProduct(Long idProduct) {
        return productNoteRepository.findByIdProduct(idProduct);
    }
}
