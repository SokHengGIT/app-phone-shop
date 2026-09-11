package com.setec.appphoneshop.model;

import java.math.BigDecimal;

public class Phone {

    private Long id;
    private String brand;
    private String model;
    private BigDecimal price;
    private String imageUrl;

    public Phone() {
    }

    public Phone(Long id, String brand, String model, BigDecimal price, String imageUrl) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
