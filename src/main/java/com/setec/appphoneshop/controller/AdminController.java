package com.setec.appphoneshop.controller;

import com.setec.appphoneshop.model.Phone;
import com.setec.appphoneshop.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("admin-dashboard")
public class AdminController {
        @GetMapping("")
       public String home (){
           return "layout/pages/admin-pages/index";
       }

    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = List.of(
                new User(1L, "Alexander Pierce", "alex.pierce@example.com"),
                new User(2L, "Sokha Chan", "sokha.chan@example.com"),
                new User(3L, "Dara Long", "dara.long@example.com")
        );
        model.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping("/products")
    public String products(Model model) {
        List<Phone> phones = List.of(
                new Phone(1L, "Apple", "iPhone 16", new BigDecimal("999.00"), "/images/iphone16.png"),
                new Phone(2L, "Samsung", "Galaxy S25", new BigDecimal("899.00"), "/images/galaxys25.png"),
                new Phone(3L, "Google", "Pixel 10", new BigDecimal("799.00"), "/images/pixel10.png")
        );
        model.addAttribute("phones", phones);
        return "admin/products";
    }
}
