package com.company.dz.three.onlinestore;

public class Product {
    private String nameProduct;
    private int price;
    private double rating;

    public Product(String nameProduct, int price, double rating) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.rating = rating;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public int getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }
}
