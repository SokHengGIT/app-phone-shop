package com.setec.appphoneshop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// This ensures only users with these roles can access the dashboard
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("/admin/dashboard/")
public class AdminHomeController {

    @GetMapping("")
    public String index(Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {
            return "redirect:/product"; // I changed this to point to your working product list!
        }
        return "redirect:/login";
    }
}