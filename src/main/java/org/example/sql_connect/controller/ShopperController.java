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
    public List<Shopper> allDb() {
        return service.allFromDb();
    }

    @GetMapping("/{id}")
    public Shopper byId(@PathVariable int id) {
        return service.byId(id);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Shopper s) {
        service.create(s);
        return ResponseEntity.ok("Created");
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<String> updateEmail(@PathVariable int id, @RequestParam String email) {
        service.updateEmail(id, email);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
