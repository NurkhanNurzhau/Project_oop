package org.example.sql_connect.entity;

import java.util.Objects;

public class Product extends BaseEntity {
    private String name;
    private double price;
    private int stock;

    public Product() {}

    public Product(int productId, String name, double price, int stock) {
        super(productId);
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getProductId() { return id; }
    public void setProductId(int productId) { this.id = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 &&
                stock == product.stock &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, price, stock);
    }
}
