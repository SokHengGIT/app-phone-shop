package com.setec.appphoneshop.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false, length = 50, name = "product_name")
    private String name;
    @Column(nullable = false, length = 4, name = "price")
    private double price;
    @Column(nullable = false, length = 4, name = "stock")
    private int stock;
    @Column(nullable = true, length = 300, name = "photo")
    private String photo;
    @Column(nullable = true, length = 300, name = "description")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;
    @Column(name = "create_by", nullable = true, length = 10)
    private int createBy;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createAt;
    @Column(name = "modify_by", nullable = true, length = 10)
    private int modifyBy;
    @Column(name = "modify_at")
    private LocalDateTime modifyAt;
    private boolean status;
}
