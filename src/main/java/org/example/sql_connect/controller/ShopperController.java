package org.example.sql_connect.controller;

import org.example.sql_connect.entity.Shopper;
import org.example.sql_connect.service.ShopperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shoppers")
public class ShopperController {

    private final ShopperService service;

    public ShopperController(ShopperService service) {
        this.service = service;
    }

    @GetMapping
    public List<Shopper> all() {
        return service.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shopper> byId(@PathVariable int id) {
        Shopper s = service.findById(id);
        return (s == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(s);
    }

    @PostMapping
    public ResponseEntity<Shopper> create(@RequestBody Shopper s) {
        Shopper created = service.create(s);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<String> updateEmail(@PathVariable int id, @RequestParam String email) {
        boolean ok = service.updateEmail(id, email);
        return ok ? ResponseEntity.ok("Updated") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        boolean ok = service.delete(id);
        return ok ? ResponseEntity.ok("Deleted") : ResponseEntity.notFound().build();
    }
}
