package org.perfumepedia.DataBase.controller;

import org.perfumepedia.DataBase.component.HtmlTagGenerator;
import org.perfumepedia.DataBase.component.MessageGenerator;
import org.perfumepedia.DataBase.component.SexNameGenerator;
import org.perfumepedia.DataBase.dto.FilterProductsDto;
import org.perfumepedia.DataBase.dto.ProductReviewByUserDto;
import org.perfumepedia.DataBase.model.*;
import org.perfumepedia.DataBase.repository.*;
import org.perfumepedia.DataBase.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {
    public static final String FILTER_PRODUCTS_DTO = "filterProductsDto";
    public static final String TYPES = "types";
    public static final String BRANDS = "brands";
    public static final String POSTS = "posts";
    public static final String PRODUCT = "product";
    public static final String SEX = "sex";
    public static final String AVERAGE_SCORE = "averageScore";
    public static final String VOTE_PRODUCT = "voteProduct";
    public static final String USER_VOTE_PRODUCT = "userVoteProduct";
    public static final String USER_VOTE_REVIEW = "userVoteReview";
    public static final String LOGIN = "login";
    public static final String BASE_NOTE = "baseNote";
    public static final String HEAD_NOTE = "headNote";
    public static final String HEART_NOTE = "heartNote";
    public static final String IMAGE = "image";
    public static final String PRODUCT_REVIEW = "productReview";
    public static final String PRODUCT_REVIEW_BY_USER_DTO = "productReviewByUserDto";
    public static final String THERE_IS_NOT_PRODUCT_BY_ID = "there.is.not.product.by.id";
    public static final String ERROR = "error";
    @Autowired
    private SexNameGenerator sexNameGenerator;
    @Autowired
    private HtmlTagGenerator htmlTagGenerator;
    @Autowired
    private VoteProductService voteProductService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductReviewService productReviewService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private BrandsService brandsService;
    @Autowired
    private ProductNoteService productNoteService;
    @Autowired
    private MessageGenerator messageGenerator;
    @Autowired
    private VoteNoteService voteNoteService;
    @Autowired
    private ProductImageService productImageService;

    @GetMapping("/products")
    public String showProducts(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            Model model,
            HttpSession session)
    {
        FilterProductsDto filterProductsDto = (FilterProductsDto) session.getAttribute(FILTER_PRODUCTS_DTO);
        if (filterProductsDto == null) filterProductsDto = new FilterProductsDto();
        model.addAttribute(FILTER_PRODUCTS_DTO, filterProductsDto);
        model.addAttribute(TYPES, typeService.getAllTypes());
        model.addAttribute(BRANDS, brandsService.getAllBrands());
        List<String> listTypes = new ArrayList<>();
        List<String> listSex = new ArrayList<>();
        List<String> listBrands = new ArrayList<>();
        if (filterProductsDto != null || filterProductsDto.getTypes().size() > 0) {
            listTypes = filterProductsDto.getTypes();
        }
        if (filterProductsDto != null || filterProductsDto.getSex().size() > 0) {
            listSex = filterProductsDto.getSex();
        }
        if (filterProductsDto != null || filterProductsDto.getBrands().size() > 0) {
            listBrands = filterProductsDto.getBrands();
        }

        model.addAttribute(POSTS, productService.getProducts(listTypes, listSex, listBrands, pageNumber, size));
        return "products/products";
    }

    @GetMapping("/products/reset")
    public String deletedFilterProductsDtoInSessionValue(HttpSession session) {
        session.removeAttribute(FILTER_PRODUCTS_DTO);
        return "redirect:/products";
    }

    @PostMapping("/products")
    public String showFilterProducts(
            @Valid FilterProductsDto filterProductsDto,
            BindingResult bindingResult,
            Model model,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            HttpSession session)
    {
        model.addAttribute(FILTER_PRODUCTS_DTO, filterProductsDto);
        session.setAttribute(FILTER_PRODUCTS_DTO, filterProductsDto);
        model.addAttribute(TYPES, typeService.getAllTypes());
        model.addAttribute(BRANDS, brandsService.getAllBrands());
        model.addAttribute(POSTS, productService.getProducts(
                filterProductsDto.getTypes(),
                filterProductsDto.getSex(),
                filterProductsDto.getBrands(),
                pageNumber, size));
        return "products/products";
    }

    @GetMapping("/product/{id}")
    public String getProductById2(@PathVariable(name = "id") Long id, Model model) {
        if (productService.getProductById(id).isPresent()) {
            Product product = productService.getProductById(id).get();
            model.addAttribute(PRODUCT, product);
            model.addAttribute(SEX, sexNameGenerator.getSexName(product.getSex()));
            Double score = productReviewService.getAverageScoreFromReviewUser(id);
            model.addAttribute(AVERAGE_SCORE, (score == null) ? htmlTagGenerator.getScoreStar(0.0) : htmlTagGenerator.getScoreStar(score));
            model.addAttribute(VOTE_PRODUCT, voteProductService.getVoteProductStatsByIdProduct(id));
            String login = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findUserByLogin(login);
            if (user != null) {
                model.addAttribute(USER_VOTE_PRODUCT, voteProductService.findVoteProduct(user.getIdUser(), id));
                model.addAttribute(USER_VOTE_REVIEW, productReviewService.findProductReviewByIdUserAndIdProduct(user, id));
                model.addAttribute(LOGIN, true);
            } else {
                model.addAttribute(USER_VOTE_PRODUCT, null);
                model.addAttribute(USER_VOTE_REVIEW, null);
                model.addAttribute(LOGIN, false);
            }
            List<ProductNote> allProductNoteList = productNoteService.getProductNoteByIdProduct(id);
            model.addAttribute(BASE_NOTE, getListNoteWithVoteScoreByCategoryNote(allProductNoteList, 1, id));
            model.addAttribute(HEAD_NOTE, getListNoteWithVoteScoreByCategoryNote(allProductNoteList, 2, id));
            model.addAttribute(HEART_NOTE, getListNoteWithVoteScoreByCategoryNote(allProductNoteList, 3, id));
            model.addAttribute(IMAGE, productImageService.getMainImagesByIdProduct(id));
            model.addAttribute(PRODUCT_REVIEW, productReviewService.getProductReviewsByIdProduct(id));
            ProductReviewByUserDto productReviewByUserDto = new ProductReviewByUserDto();
            model.addAttribute(PRODUCT_REVIEW_BY_USER_DTO,productReviewByUserDto);
            return "products/product";
        } else {
            model.addAttribute(ERROR, messageGenerator.getMessage(THERE_IS_NOT_PRODUCT_BY_ID) + id);
            return "error";
        }
    }

    private List<ListNoteWithVoteScore> getListNoteWithVoteScoreByCategoryNote(List<ProductNote> productNoteList, int category, Long idProduct) {
        boolean isBaseNote = category == 1 ? true : false;
        boolean isHeadNote = category == 2 ? true : false;
        boolean isHeartNote = category == 3 ? true : false;
        return productNoteList.stream()
                .filter(note -> note.isBaseNote() == isBaseNote)
                .filter(note -> note.isHeadNote() == isHeadNote)
                .filter(note -> note.isHeartNote() == isHeartNote)
                .map(note -> ListNoteWithVoteScore.builder()
                        .productNote(note)
                        .score(voteNoteService.getVoteNoteValueForProduct(idProduct, note.getIdNote()) != null ? voteNoteService.getVoteNoteValueForProduct(idProduct, note.getIdNote()) : 0)
                        .build())
                .sorted(Comparator.comparingDouble(ListNoteWithVoteScore::getScore).reversed())
                .collect(Collectors.toList());
    }
}
