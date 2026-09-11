package com.setec.appphoneshop.controller;

import com.setec.appphoneshop.services.RoleService;
import com.setec.appphoneshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.setec.appphoneshop.models.AppUser;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping({"", "/"})
    public String index(Model model, @RequestParam(defaultValue = "") String keyword) {
        return page(1, model, keyword);
    }

    @GetMapping("/show")
    public String showCreateUserPage(Model model) {
        // Fetch the roles from the database
        var roles = this.roleService.getRoleAll();

        model.addAttribute("user", new AppUser());
        // Send the roles to the HTML template
        model.addAttribute("roles", roles);

        return "layout/pages/admin-pages/pages/users/create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute AppUser user) {

        String encryptedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        this.userService.createUser(user);
        return "redirect:/user";
    }

    @GetMapping("page/{pageNo}")
    public String page(@PathVariable int pageNo, Model model, @RequestParam(defaultValue = "", required = false) String keyword){
        var users = this.userService.paginated(pageNo, keyword);
        model.addAttribute("users", users.getContent());
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("totalItems", users.getTotalElements());
        return "layout/pages/admin-pages/pages/users/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserPage(@PathVariable int id, Model model) {
        AppUser user = this.userService.findById(id);
        model.addAttribute("user", user);
        return "layout/pages/admin-pages/pages/users/edit";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute AppUser user) {
        AppUser existingUser = this.userService.findById(id);
        if (existingUser != null) {
            user.setId(id);
            user.setPassword(existingUser.getPassword());
            // We removed setting the enabled status from the existing user
            // because the edit form now sends the updated status!
            this.userService.createUser(user);
        }
        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        this.userService.deleteById(id);
        return "redirect:/user";
    }
}