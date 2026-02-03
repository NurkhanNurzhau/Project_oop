package org.example.sql_connect.pattern;

import org.example.sql_connect.entity.Product;

public class ProductBuilder {
    private int id;
    private String name;
    private double price;
    private int stock;

    public ProductBuilder id(int id) { this.id = id; return this; }
    public ProductBuilder name(String name) { this.name = name; return this; }
    public ProductBuilder price(double price) { this.price = price; return this; }
    public ProductBuilder stock(int stock) { this.stock = stock; return this; }

    public Product build() {
        return new Product(id, name, price, stock);
    }
}
