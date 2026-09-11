package com.setec.appphoneshop.controller; // Make sure this matches your package name!

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class AuthController {

    @GetMapping("/login")
    public String login() {
        // This tells Spring to look deep inside the users folder for the HTML file
        return "layout/pages/admin-pages/pages/users/login";
    }
}