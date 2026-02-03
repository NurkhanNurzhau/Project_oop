package org.example.sql_connect.dao;

import org.example.sql_connect.entity.Product;

import java.util.List;

public interface ProductRepository {
    int create(Product p);
    List<Product> readAll();
    Product findById(int id);
    int updateStock(int id, int stock);
    int delete(int id);
}
