package com.example.lab;

import java.io.Serializable;

public class Product implements Serializable {
    public int id;
    public String name;
    public String upc;
    public String manufacturer;
    public double price;
    public int shelfLife;
    public int quantity;

    public Product(int id, String name, String upc, String manufacturer, double price, int shelfLife, int quantity) {
        this.id = id;
        this.name = name;
        this.upc = upc;
        this.manufacturer = manufacturer;
        this.price = price;
        this.shelfLife = shelfLife;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name;
    }
}
