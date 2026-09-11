package com.setec.appphoneshop.controller;

import com.setec.appphoneshop.models.Category;
import com.setec.appphoneshop.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("category")
public class CategoryController {
    protected final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String index(Model model,
                        @RequestParam(defaultValue = "") String keyword) {
        return paginated(1, model, keyword);
    }

    @GetMapping("/page/{pageNo}")
    public String paginated(@PathVariable int pageNo, Model model, @RequestParam(defaultValue = "") String keyword) {

        // Safety check so users can't enter 0 or negative numbers
        int safePageNo = Math.max(pageNo, 1);

        // FIXED: Removed the "- 1" from this call
        Page<Category> categories = categoryService.paginated(safePageNo, keyword);

        model.addAttribute("categories", categories);
        model.addAttribute("currentPage", safePageNo);
        model.addAttribute("totalPages", categories.getTotalPages());
        model.addAttribute("totalItems", categories.getTotalElements());
        model.addAttribute("keyword", keyword);

        return "layout/pages/admin-pages/pages/categories/index";
    }

    @GetMapping("/show")
    public String show(@ModelAttribute Category category, Model model) {
        model.addAttribute("category", category);
        return "layout/pages/admin-pages/pages/categories/create";
    }

    @PostMapping("create")
    public String create(@ModelAttribute Category category, RedirectAttributes redirectAttributes) {
        this.categoryService.createCategory(category);
        redirectAttributes.addFlashAttribute("success", "Category created successfully");
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        var category = this.categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "layout/pages/admin-pages/pages/categories/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Category category,
                         RedirectAttributes redirectAttributes) {
        category.setId(id);
        this.categoryService.updateCategory(category);
        redirectAttributes.addFlashAttribute("success", "Category updated successfully");
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        this.categoryService.deleteCategory(id);
        redirectAttributes.addFlashAttribute("success", "Category deleted successfully");
        return "redirect:/category";
    }
}