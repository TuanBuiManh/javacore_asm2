package com.example.ui;

import com.example.controller.ProductService;
import com.example.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductConsole {
    private final Scanner sc;
    ProductService productService = new ProductService();

    public ProductConsole() throws SQLException {
        this.sc = new Scanner(System.in);
    }

    private int menu() {
        System.out.println("---PRODUCT MENU---");
        System.out.println("1. Add Product");
        System.out.println("2. Get Product By ID");
        System.out.println("3. Remove product");
        System.out.println("4. Get All Product ");
        System.out.println("5. Get 0 Quantity Product");
        System.out.println("6. Get Max Sold Quantity Product");
        System.out.println("0. Exit");
        int choice = readInt(0, 6);
        return choice;
    }

    public void start() {
        while (true) {
            int choice = menu();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    addProduct();
                    break;
                case 2:
                    getProductById();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    showAll();
                    break;
                case 5:
                    get0QuantityProduct();
                    break;
                case 6:
                    getMaxSoldQuantityProduct();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private int readInt(int min, int max) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= min && choice <= max) {
                    break;
                }
            } catch (NumberFormatException e) {
            }
        }
        return choice;
    }

    private void addProduct() {
        System.out.println("Enter product name: ");
        String name = sc.nextLine();
        System.out.println("Enter product quantity");
        int quantity = readInt(0, Integer.MAX_VALUE);
        System.out.println("Enter product sold quantity");
        int soldQuantity = readInt(0, Integer.MAX_VALUE);
        Product product = new Product(name, quantity, soldQuantity);
        productService.createProduct(product);
    }

    private void showAll() {
        System.out.println("--All product--");
        System.out.println("ID\tName\tQuantity\tSoldQuantity");
        ArrayList<Product> allProducts = productService.getAllProduct();

        allProducts.forEach(product -> {
            System.out.println(product.getId() + "\t" + product.getName() + "\t"
                    + product.getQuantity() + "\t\t\t" + product.getSoldQuantity());
        });
    }

    private void removeProduct() {
        System.out.println("Enter ID of product");
        int id = readInt(0, Integer.MAX_VALUE);
        productService.deleteProduct(id);
    }

    private void getProductById() {
        System.out.println("Enter ID of product");
        int id = readInt(0, Integer.MAX_VALUE);
        Product product = productService.getProductById(id);
        System.out.println(product.getId() + "\t" + product.getName() + "\t"
                + product.getQuantity() + "\t\t\t" + product.getSoldQuantity());
    }

    private void get0QuantityProduct() {
        System.out.println("--Product sold out--");
        System.out.println("ID\tName\tQuantity\tSoldQuantity");
        ArrayList<Product> allProducts = productService.getProduct0Quantity();

        allProducts.forEach(product -> {
            System.out.println(product.getId() + "\t" + product.getName() + "\t"
                    + product.getQuantity() + "\t\t\t" + product.getSoldQuantity());
        });
    }

    private void getMaxSoldQuantityProduct() {
        System.out.println("--Product best seller--");
        System.out.println("ID\tName\tQuantity\tSoldQuantity");
        ArrayList<Product> allProducts = productService.getProductSold();

        allProducts.forEach(product -> {
            System.out.println(product.getId() + "\t" + product.getName() + "\t"
                    + product.getQuantity() + "\t\t\t" + product.getSoldQuantity());
        });
    }
}
