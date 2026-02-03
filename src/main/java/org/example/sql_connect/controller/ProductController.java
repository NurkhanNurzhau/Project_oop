package org.example.sql_connect.controller;

import org.example.sql_connect.entity.Product;
import org.example.sql_connect.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> allDb() {
        return service.allFromDb();
    }

    @GetMapping("/{id}")
    public Product byId(@PathVariable int id) {
        return service.byId(id);
    }

    @PostMapping
    public Product create(@RequestBody Map<String, Object> body) {
        String name = body.get("name") == null ? null : body.get("name").toString();
        double price = body.get("price") == null ? 0 : Double.parseDouble(body.get("price").toString());
        int stock = body.get("stock") == null ? 0 : Integer.parseInt(body.get("stock").toString());
        return service.createFromBuilder(name, price, stock);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<String> updateStock(@PathVariable int id, @RequestParam int stock) {
        service.updateStock(id, stock);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/pool/refresh")
    public ResponseEntity<String> refreshPool() {
        service.refreshPoolFromDb();
        return ResponseEntity.ok("Pool refreshed from DB");
    }

    @GetMapping("/pool")
    public List<Product> poolAll() {
        return service.poolAll();
    }

    @GetMapping("/pool/filter")
    public List<Product> poolFilter(@RequestParam double minPrice) {
        return service.poolFilterMinPrice(minPrice);
    }

    @GetMapping("/pool/search")
    public List<Product> poolSearch(@RequestParam String q) {
        return service.poolSearchName(q);
    }

    @GetMapping("/pool/sort/price")
    public List<Product> poolSortPriceAsc() {
        return service.poolSortPriceAsc();
    }

    @GetMapping("/pool/sort/stock")
    public List<Product> poolSortStockDesc() {
        return service.poolSortStockDesc();
    }
}
