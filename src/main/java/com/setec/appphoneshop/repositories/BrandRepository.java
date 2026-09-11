package com.setec.appphoneshop.repositories;

import com.setec.appphoneshop.models.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {

    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);

    // The new Paginated search method!
    Page<Brand> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

}