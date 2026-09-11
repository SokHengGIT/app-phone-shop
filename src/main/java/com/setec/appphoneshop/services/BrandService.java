package com.setec.appphoneshop.services;


import com.setec.appphoneshop.models.Brand;
import com.setec.appphoneshop.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BrandService {


    private final BrandRepository brandRepository;



    public Page<Brand> paginated(int pageNo, String keyword) {
        // Teacher sets the size to 10 right here, and does the -1 math here!
        PageRequest pageable = PageRequest.of(pageNo - 1, 10);

        if (keyword == null || keyword.trim().isEmpty()) {
            return this.brandRepository.findAll(pageable);
        }

        // Note: I kept your 'IgnoreCase' here because it's better for search functionality!
        return this.brandRepository.findByNameContainingIgnoreCase(keyword, pageable);
    }



    public void createBrand(Brand brand){

        if (brandRepository.existsByNameIgnoreCase(brand.getName())) {
            throw new IllegalArgumentException("Brand name already exists: " + brand.getName());
        }

        brandRepository.save(brand);

    }



    public Brand getBrandById(int id){

        return brandRepository.findById(id)
                .orElse(null);

    }



    public void updateBrand(Brand brand){

        if (brandRepository.existsByNameIgnoreCaseAndIdNot(brand.getName(), brand.getId())) {
            throw new IllegalArgumentException("Brand name already exists: " + brand.getName());
        }

        brandRepository.save(brand);

    }



    public void deleteBrand(int id){

        brandRepository.deleteById(id);

    }

    public List<Brand> getBrandAll() {
        return this.brandRepository.findAll();
    }

}