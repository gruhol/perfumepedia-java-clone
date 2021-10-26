package org.perfumepedia.DataBase.controller;

import org.perfumepedia.DataBase.component.MessageGenerator;
import org.perfumepedia.DataBase.dto.FilterProductsDto;
import org.perfumepedia.DataBase.dto.FilterProductsInBrandDto;
import org.perfumepedia.DataBase.model.Brand;
import org.perfumepedia.DataBase.repository.BrandsRepository;
import org.perfumepedia.DataBase.repository.ProductRepository;
import org.perfumepedia.DataBase.repository.TypeRepository;
import org.perfumepedia.DataBase.service.BrandsService;
import org.perfumepedia.DataBase.service.ProductService;
import org.perfumepedia.DataBase.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BrandsController {

    public static final String FILTER_PRODUCTS_IN_BRAND_DTO = "filterProductsInBrandDto";
    public static final String TYPES = "types";
    public static final String BRAND = "brand";
    public static final String POSTS = "posts";
    public static final String LETTER = "letter";
    public static final String ALPHABET = "alphabet";

    @Autowired
    private BrandsService brandsService;
    @Autowired
    private MessageGenerator messageGenerator;
    @Autowired
    private ProductService productService;
    @Autowired
    private TypeService typeService;

    private char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    @GetMapping("/brand/{idBrand}")
    public String getInfoOfBrandWithProductsByIdBrand(
            @PathVariable(value = "idBrand", required = true) Long idBrand,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            Model model,
            HttpSession httpSession)
    {
        FilterProductsInBrandDto filterProductsInBrandDto = (FilterProductsInBrandDto) httpSession.getAttribute("filterProductsInBrandDto");
        if (filterProductsInBrandDto == null) filterProductsInBrandDto = new FilterProductsInBrandDto();
        Brand brand = brandsService.getBrandByIdBrand(idBrand);
        model.addAttribute(FILTER_PRODUCTS_IN_BRAND_DTO, filterProductsInBrandDto);
        model.addAttribute(TYPES, typeService.getAllTypes());
        model.addAttribute(BRAND, brand);
        List<String> listTypes = new ArrayList<>();
        List<String> listSex = new ArrayList<>();
        List<String> listBrands = new ArrayList<>();
        if (filterProductsInBrandDto != null || filterProductsInBrandDto.getTypes().size() > 0) {
            listTypes = filterProductsInBrandDto.getTypes();
        }
        if (filterProductsInBrandDto != null || filterProductsInBrandDto.getSex().size() > 0) {
            listSex = filterProductsInBrandDto.getSex();
        }
        listBrands.add("" + brand.getIdBrand());
        model.addAttribute(POSTS, productService.getProducts(listTypes, listSex, listBrands, pageNumber, size));
        return "brands/brand";
    }

    @GetMapping("/brand/reset/{idBrand}")
    public String deletedFilterProductsDtoInSessionValue(HttpSession session, @PathVariable(value = "idBrand", required = true) Long idBrand) {
        session.removeAttribute(FILTER_PRODUCTS_IN_BRAND_DTO);
        return "redirect:/brand/" + idBrand;
    }

    @PostMapping("brand/{idBrand}")
    public String getInfoOfBrandWithProductByIdBrandFiltered(
            @PathVariable(value = "idBrand", required = true) Long idBrand,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            Model model,
            @Valid FilterProductsInBrandDto filterProductsInBrandDto,
            BindingResult bindingResult,
            HttpSession httpSession)
    {
        Brand brand = brandsService.getBrandByIdBrand(idBrand);
        model.addAttribute(BRAND, brand);
        model.addAttribute(FILTER_PRODUCTS_IN_BRAND_DTO, filterProductsInBrandDto);
        httpSession.setAttribute(FILTER_PRODUCTS_IN_BRAND_DTO, filterProductsInBrandDto);
        model.addAttribute(TYPES, typeService.getAllTypes());
        List<String> listBrands = new ArrayList<>();
        listBrands.add("" + brand.getIdBrand());
        model.addAttribute(POSTS, productService.getProducts(
                filterProductsInBrandDto.getTypes(),
                filterProductsInBrandDto.getSex(),
                listBrands,
                pageNumber,
                size));
        return "brands/brand";
    }

    @GetMapping("/brands")
    public String getAllBrandStartOnTheLetterA() {
        String charOfLetter = "a";
        return "redirect:/brands/" + charOfLetter;
    }

    @GetMapping("/brands/{letter}")
    public String getBrandOnTheLetter(@PathVariable(value = "letter", required = false) Optional<String> letter, Model model) {
        String charOfLetter = letter.isPresent() ? letter.get() : "a";
        model.addAttribute(LETTER, charOfLetter);
        model.addAttribute(BRAND, brandsService.getListOfBrandStartingWithByLetter(charOfLetter));
        model.addAttribute(ALPHABET, alphabet);
        return "brands/brands";
    }
}
