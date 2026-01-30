package org.example.sql_connect.service;

import org.example.sql_connect.dao.ProductDAO;
import org.example.sql_connect.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDAO dao;

    public ProductService(ProductDAO dao) {
        this.dao = dao;
    }

    public Product create(Product p) {
        dao.create(p);
        List<Product> all = dao.readAll();
        return all.isEmpty() ? null : all.get(all.size() - 1);
    }

    public List<Product> readAll() {
        return dao.readAll();
    }

    public Product findById(int id) {
        return dao.findById(id);
    }

    public boolean updateStock(int id, int stock) {
        return dao.updateStock(id, stock) > 0;
    }

    public boolean delete(int id) {
        return dao.delete(id) > 0;
    }
}
