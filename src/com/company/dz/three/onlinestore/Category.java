package com.company.dz.three.onlinestore;

public class Category {
    private String nameCategory;
    private Product[] products;

    public Category(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public Product[] getProducts() {
        return products;
    }
}
