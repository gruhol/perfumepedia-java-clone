package org.perfumepedia.DataBase.controller;

import org.perfumepedia.DataBase.dto.ProductReviewByUserDto;
import org.perfumepedia.DataBase.model.ProductReview;
import org.perfumepedia.DataBase.model.ResponseAjax;
import org.perfumepedia.DataBase.model.User;
import org.perfumepedia.DataBase.service.ProductReviewService;
import org.perfumepedia.DataBase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class ProductReviewController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductReviewService productReviewService;


    @PostMapping(value = "/addproductreview")
    public String addProductReviewByUser(@Valid ProductReviewByUserDto productReviewByUserDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByLogin(login);
        ProductReview existProductReview = productReviewService.findProductReviewByIdUserAndIdProduct(user, productReviewByUserDto.getIdProduct());
        if(existProductReview == null) {
            ProductReview newProductReview = ProductReview.builder()
                    .user(user)
                    .idProduct(productReviewByUserDto.getIdProduct())
                    .score(productReviewByUserDto.getScore())
                    .textReview(productReviewByUserDto.getTextReview())
                    .build();
            productReviewService.saveProductReviewByUser(newProductReview);
        } else {
            existProductReview.setTextReview(productReviewByUserDto.getTextReview());
            existProductReview.setScore(productReviewByUserDto.getScore());
            existProductReview.setDataReview(new Date());
            productReviewService.saveProductReviewByUser(existProductReview);
        }
        return "redirect:/product/" + productReviewByUserDto.getIdProduct();
    }
}
