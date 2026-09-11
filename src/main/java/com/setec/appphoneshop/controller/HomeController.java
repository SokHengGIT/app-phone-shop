package com.setec.appphoneshop.controller;

import com.setec.appphoneshop.model.Phone;
import com.setec.appphoneshop.services.CategoryService;
import com.setec.appphoneshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        var categories = this.categoryService.getCategoryAndBrand();

        // FIXED: Now fetching only active products instead of all products
        var products = this.productService.getActiveProducts();

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "layout/front-end/index";
    }
}