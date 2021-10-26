package org.perfumepedia.DataBase.service;

import org.perfumepedia.DataBase.model.Brand;
import org.perfumepedia.DataBase.model.Paged;
import org.perfumepedia.DataBase.component.PagingBuilder;
import org.perfumepedia.DataBase.repository.BrandsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandsService {
    @Autowired
    private BrandsRepository brandsRepository;

    public List<Brand> getAllBrands() {
        return brandsRepository.findAll();
    }

    public Brand getBrandByIdBrand(Long idBrand) {
        return brandsRepository.findById(idBrand).get();
    }

    public List<Brand> getListOfBrandStartingWithByLetter(String letter) {
        return brandsRepository.findByNameBrandStartingWith(letter);
    }
}
