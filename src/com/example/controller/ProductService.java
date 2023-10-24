package com.example.controller;

import com.example.entity.Product;
import com.example.model.ProductDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductService {

    ProductDaoImpl productDao = new ProductDaoImpl();

    public ProductService() throws SQLException {
    }

    public void createProduct(Product product) {
        productDao.createProduct(product);
    }

    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }

    public ArrayList<Product> getAllProduct() {
        return productDao.getAllProduct();
    }

    public void deleteProduct(int id) {
        productDao.deleteProduct(id);
    }

    public ArrayList<Product> getProduct0Quantity(){
        return productDao.getProduct0Quantity();
    }

    public ArrayList<Product> getProductSold(){
        return productDao.getProductSold();
    }
}
