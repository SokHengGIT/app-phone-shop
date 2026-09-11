package com.setec.appphoneshop.controller;

import com.setec.appphoneshop.models.Product;
import com.setec.appphoneshop.services.BrandService; // Needed for the dropdown
import com.setec.appphoneshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final BrandService brandService; // Injected to load the dropdown menu

    @GetMapping("")
    public String index(Model model, @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
        this.paginated(1, model, keyword);
        return  "layout/pages/admin-pages/pages/products/index";
    }

    @GetMapping("/page/{pageNo}")
    public String paginated(@PathVariable int pageNo, Model model, @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {

        // FIXED: Added the safety check so Page 0 doesn't crash the server
        int safePageNo = Math.max(pageNo, 1);

        // FIXED: Calling the updated service method with safePageNo
        var products = this.productService.paginated(safePageNo, keyword);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", safePageNo);
        model.addAttribute("totalItems", products.getTotalElements());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "layout/pages/admin-pages/pages/products/index";
    }

    @GetMapping("/show")
    public String show(@ModelAttribute Product product, Model model) {
        var brands = this.brandService.getBrandAll(); // Grab all brands
        model.addAttribute("product", product);
        model.addAttribute("brands", brands); // Send them to the HTML dropdown
        return "layout/pages/admin-pages/pages/products/create";
    }

    // Notice the @RequestParam("file") to catch the uploaded image
    @PostMapping("/create")
    public String create(@ModelAttribute Product product, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        this.productService.createProduct(product, file);
        redirectAttributes.addFlashAttribute("success", "Product created successfully");
        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        var brands = this.brandService.getBrandAll();
        var productExist = this.productService.editById(id);
        model.addAttribute("product", productExist);
        model.addAttribute("brands", brands);
        return "layout/pages/admin-pages/pages/products/create"; // Reusing the same form for editing
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Product product, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        product.setId(id);
        this.productService.updateById(id, product, file);
        redirectAttributes.addFlashAttribute("success", "Product updated successfully");
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        this.productService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Product deleted successfully");
        return "redirect:/product";
    }
}