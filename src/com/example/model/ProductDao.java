package com.example.model;

import com.example.entity.Product;

import java.util.ArrayList;

public interface ProductDao {

    public void createProduct(Product product);

    public Product getProductById(int id);

    public ArrayList<Product> getAllProduct();

    public void deleteProduct(int id);

    public ArrayList<Product> getProduct0Quantity();

    public ArrayList<Product> getProductSold();
}
