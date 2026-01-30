package org.example.sql_connect.controller;

import org.example.sql_connect.entity.Product;
import org.example.sql_connect.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> all() {
        return service.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> byId(@PathVariable int id) {
        Product p = service.findById(id);
        return (p == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product p) {
        Product created = service.create(p);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<String> updateStock(@PathVariable int id, @RequestParam int stock) {
        boolean ok = service.updateStock(id, stock);
        return ok ? ResponseEntity.ok("Updated") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        boolean ok = service.delete(id);
        return ok ? ResponseEntity.ok("Deleted") : ResponseEntity.notFound().build();
    }
}
