
package com.setec.appphoneshop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "category_name", nullable = false, length = 50)
    private String name;


    @Column(name = "status", length = 20)
    private String status;


    @OneToMany(mappedBy = "category")
    private List<Brand> brands;

}