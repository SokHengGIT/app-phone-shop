package com.setec.appphoneshop.services;

import com.setec.appphoneshop.models.Product;
import com.setec.appphoneshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> paginated(int pageNo, String keyword) {
        // FIXED: Page size is strictly 10, and the -1 math is handled here
        Pageable pageable = PageRequest.of(pageNo - 1, 10);

        if (keyword == null || keyword.trim().isEmpty()) {
            return productRepository.findAll(pageable);
        }

        return productRepository.findByNameContainingIgnoreCase(keyword, pageable);
    }

    // Handles saving the product and uploading the photo
    public void createProduct(Product product, MultipartFile file) throws IOException {
        Path path = Paths.get("target/uploads");

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
            Path targetPath = path.resolve(fileName);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            product.setPhoto(fileName);
        }

        this.productRepository.save(product);
    }

    public Product editById(int id) {
        return this.productRepository.findById(id).orElse(null);
    }

    public void updateById(int id, Product product, MultipartFile file) throws IOException {
        Product existingProduct = this.productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setStatus(product.isStatus());
            existingProduct.setBrand(product.getBrand());

            // Only upload and change the photo if a new one was selected
            if (file != null && !file.isEmpty()) {
                Path path = Paths.get("target/uploads");
                if (!Files.exists(path)) {
                    Files.createDirectories(path);
                }
                String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
                Path targetPath = path.resolve(fileName);
                Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                existingProduct.setPhoto(fileName);
            }
            this.productRepository.save(existingProduct);
        }
    }

    public void deleteById(int id) {
        this.productRepository.deleteById(id);
    }


    public List<Product> getActiveProducts() {
        // Call the boolean method
        return productRepository.findByStatusTrue();
    }
}