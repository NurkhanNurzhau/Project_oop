package org.example.sql_connect.service;

import org.example.sql_connect.dao.ProductRepository;
import org.example.sql_connect.entity.Product;
import org.example.sql_connect.exception.InvalidDataException;
import org.example.sql_connect.exception.NotFoundException;
import org.example.sql_connect.pattern.ProductBuilder;
import org.example.sql_connect.pattern.RepositoryFactory;
import org.example.sql_connect.pool.ProductPool;
import org.example.sql_connect.util.RttiUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;
    private final ProductPool pool = new ProductPool();

    public ProductService(RepositoryFactory factory) {
        this.repo = factory.productRepo();
    }

    public List<Product> allFromDb() {
        return repo.readAll();
    }

    public Product byId(int id) {
        Product p = repo.findById(id);
        if (p == null) throw new NotFoundException("Product not found: id=" + id);
        return p;
    }

    public Product createFromBuilder(String name, double price, int stock) {
        validate(name, price, stock);
        Product p = new ProductBuilder()
                .name(name)
                .price(price)
                .stock(stock)
                .build();

        repo.create(p);

        Product last = repo.readAll().stream().reduce((a, b) -> b).orElse(null);
        if (last != null) pool.add(last);

        if (last != null) RttiUtils.detectEntityType(last);
        return last;
    }

    public void updateStock(int id, int stock) {
        if (stock < 0) throw new InvalidDataException("Stock must be >= 0");
        int affected = repo.updateStock(id, stock);
        if (affected == 0) throw new NotFoundException("Product not found: id=" + id);
    }

    public void delete(int id) {
        int affected = repo.delete(id);
        if (affected == 0) throw new NotFoundException("Product not found: id=" + id);
        pool.removeById(id);
    }

    public void refreshPoolFromDb() {
        pool.clear();
        repo.readAll().forEach(pool::add);
    }

    public List<Product> poolAll() {
        return pool.all();
    }

    public List<Product> poolFilterMinPrice(double minPrice) {
        return pool.filterByMinPrice(minPrice);
    }

    public List<Product> poolSearchName(String keyword) {
        return pool.searchByName(keyword);
    }

    public List<Product> poolSortPriceAsc() {
        return pool.sortByPriceAsc();
    }

    public List<Product> poolSortStockDesc() {
        return pool.sortByStockDesc();
    }

    private void validate(String name, double price, int stock) {
        if (name == null || name.isBlank()) throw new InvalidDataException("Name is required");
        if (price < 0) throw new InvalidDataException("Price must be >= 0");
        if (stock < 0) throw new InvalidDataException("Stock must be >= 0");
    }
}
