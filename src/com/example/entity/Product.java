package com.example.entity;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private int soldQuantity;

    public Product() {
    }

    public Product(String name, int quantity, int soldQuantity) {
        this.name = name;
        this.quantity = quantity;
        this.soldQuantity = soldQuantity;
    }

    public Product(int id, String name, int quantity, int soldQuantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.soldQuantity = soldQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }
}
