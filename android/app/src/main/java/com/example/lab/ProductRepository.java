package com.example.lab;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product(1, "Product1", "12345", "Manufacturer1", 100.0, 30, 10));
        products.add(new Product(2, "Product2", "67890", "Manufacturer2", 150.0, 60, 5));
        products.add(new Product(3, "Product3", "54321", "Manufacturer1", 200.0, 20, 2));
    }

    // Фильтрация по наименованию
    public List<Product> getProductsByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.name.equalsIgnoreCase(name)) {
                result.add(product);
            }
        }
        return result;
    }

    // Фильтрация по наименованию и цене
    public List<Product> getProductsByNameAndPrice(String name, double maxPrice) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.name.equalsIgnoreCase(name) && product.price <= maxPrice) {
                result.add(product);
            }
        }
        return result;
    }

    // Фильтрация по сроку хранения
    public List<Product> getProductsByShelfLife(int minShelfLife) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.shelfLife > minShelfLife) {
                result.add(product);
            }
        }
        return result;
    }

    public Product findProductById(int id) {
        for (Product product : products) {
            if (product.id == id) {
                return product;
            }
        }
        return null;
    }

    // Обновление продукта
    public void updateProduct(Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).id == updatedProduct.id) {
                products.set(i, updatedProduct);
                break;
            }
        }
    }
}