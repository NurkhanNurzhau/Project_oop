package org.example.sql_connect.pool;

import org.example.sql_connect.entity.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ProductPool implements DataPool<Product> {

    private final List<Product> items = new ArrayList<>();

    @Override
    public void add(Product item) {
        items.add(item);
    }

    @Override
    public List<Product> all() {
        return List.copyOf(items);
    }

    @Override
    public Optional<Product> findById(int id) {
        return items.stream()
                .filter(p -> p.getProductId() == id)
                .findFirst();
    }

    @Override
    public boolean removeById(int id) {
        return items.removeIf(p -> p.getProductId() == id);
    }

    @Override
    public void clear() {
        items.clear();
    }

    public List<Product> filterByMinPrice(double minPrice) {
        return items.stream()
                .filter(p -> p.getPrice() >= minPrice)
                .toList();
    }

    public List<Product> searchByName(String keyword) {
        String k = keyword == null ? "" : keyword.toLowerCase();
        return items.stream()
                .filter(p -> p.getName() != null && p.getName().toLowerCase().contains(k))
                .toList();
    }

    public List<Product> sortByPriceAsc() {
        return items.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .toList();
    }

    public List<Product> sortByStockDesc() {
        return items.stream()
                .sorted((a, b) -> Integer.compare(b.getStock(), a.getStock()))
                .toList();
    }
}
