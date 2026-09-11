package com.setec.appphoneshop.services;

import com.setec.appphoneshop.models.Category;
import com.setec.appphoneshop.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategoryAll() {
        var categories = this.categoryRepository.findAll();
        return categories;
    }

    public void createCategory(Category category) {
        this.categoryRepository.save(category);
    }

    public Category getCategoryById(Integer id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found: " + id));
    }

    public void updateCategory(Category category) {
        this.categoryRepository.save(category);
    }

    public void deleteCategory(Integer id) {
        this.categoryRepository.deleteById(id);
    }

    public Page<Category> paginated(int pageNo, String keyword) {
        // FIXED: Moved the - 1 math here and changed size to 10
        Pageable pageable = PageRequest.of(pageNo - 1, 10);

        if (keyword == null || keyword.trim().isEmpty()) {
            return categoryRepository.findAll(pageable);
        }

        return categoryRepository.findCategoryByNameContaining(keyword, pageable);
    }

    public List<Category> getCategoryAndBrand() {
        return this.categoryRepository.findAll();
    }
}