package com.setec.appphoneshop.controller;

import com.setec.appphoneshop.models.Brand;
import com.setec.appphoneshop.services.BrandService;
import com.setec.appphoneshop.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;
    private final CategoryService categoryService;
    private static final String REDIRECT_BRAND = "redirect:/brand";
    private static final String VIEW_PATH = "layout/pages/admin-pages/pages/brands/";

    @GetMapping
    public String index(Model model, @RequestParam(defaultValue = "") String keyword) {
        return paginated(1, model, keyword);
    }

    @GetMapping("/page/{pageNo}")
    public String paginated(
            @PathVariable int pageNo,
            Model model,
            @RequestParam(defaultValue = "") String keyword) {

        int safePageNo = Math.max(pageNo, 1);

        // FIXED: Removed the "- 1" from here because the Service handles it now!
        Page<Brand> brands = brandService.paginated(safePageNo, keyword);

        model.addAttribute("brands", brands);
        model.addAttribute("currentPage", safePageNo);
        model.addAttribute("totalPages", brands.getTotalPages());
        model.addAttribute("totalItems", brands.getTotalElements());
        model.addAttribute("keyword", keyword);

        return VIEW_PATH + "index";
    }

    @GetMapping("/show")
    public String showForm(Model model) {
        model.addAttribute("brand", new Brand());
        // ADD the categories to the model for the dropdown
        model.addAttribute("categories", categoryService.getCategoryAll());
        return VIEW_PATH + "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Brand brand, RedirectAttributes redirectAttributes) {
        try {
            brandService.createBrand(brand);
            redirectAttributes.addFlashAttribute("success", "Brand created successfully");
            return REDIRECT_BRAND;
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/brand/show";
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Brand brand = brandService.getBrandById(id);
        model.addAttribute("brand", brand);
        // ADD the categories to the model for the dropdown
        model.addAttribute("categories", categoryService.getCategoryAll());
        return VIEW_PATH + "edit";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable Integer id,
            @ModelAttribute Brand brand,
            RedirectAttributes redirectAttributes) {

        brand.setId(id);
        try {
            brandService.updateBrand(brand);
            redirectAttributes.addFlashAttribute("success", "Brand updated successfully");
            return REDIRECT_BRAND;
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/brand/edit/" + id;
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        brandService.deleteBrand(id);
        redirectAttributes.addFlashAttribute("success", "Brand deleted successfully");
        return REDIRECT_BRAND;
    }
}