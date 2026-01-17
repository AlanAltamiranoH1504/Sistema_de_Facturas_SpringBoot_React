package com.example.api_sistema_de_facturas.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;
    private String name_product;
    private String description;
    private double price;
    private String sku;

    public Product() {
    }

    public Product(String name_product, String description, double price, String sku) {
        this.name_product = name_product;
        this.description = description;
        this.price = price;
        this.sku = sku;
    }

    public Product(Long id_product, String name_product, String description, double price, String sku) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.description = description;
        this.price = price;
        this.sku = sku;
    }

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
