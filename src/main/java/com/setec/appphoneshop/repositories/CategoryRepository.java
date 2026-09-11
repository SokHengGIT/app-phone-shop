package com.setec.appphoneshop.repositories;

import com.setec.appphoneshop.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Page<Category> findCategoryByNameContaining(String keyword, Pageable pageable);
    @Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.brands")
    List<Category> getCategoryAndBrand();
}
