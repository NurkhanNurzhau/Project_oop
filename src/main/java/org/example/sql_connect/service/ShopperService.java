package org.example.sql_connect.service;

import org.example.sql_connect.dao.ShopperRepository;
import org.example.sql_connect.entity.Shopper;
import org.example.sql_connect.exception.InvalidDataException;
import org.example.sql_connect.exception.NotFoundException;
import org.example.sql_connect.pattern.RepositoryFactory;
import org.example.sql_connect.util.RttiUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopperService {

    private final ShopperRepository repo;

    public ShopperService(RepositoryFactory factory) {
        this.repo = factory.shopperRepo();
    }

    public List<Shopper> allFromDb() {
        return repo.readAll();
    }

    public Shopper byId(int id) {
        Shopper s = repo.findById(id);
        if (s == null) throw new NotFoundException("Shopper not found: id=" + id);
        RttiUtils.detectEntityType(s);
        return s;
    }

    public void create(Shopper s) {
        validate(s);
        repo.create(s);
    }

    public void updateEmail(int id, String email) {
        if (email == null || email.isBlank()) throw new InvalidDataException("Email is required");
        int affected = repo.updateEmail(id, email);
        if (affected == 0) throw new NotFoundException("Shopper not found: id=" + id);
    }

    public void delete(int id) {
        int affected = repo.delete(id);
        if (affected == 0) throw new NotFoundException("Shopper not found: id=" + id);
    }

    private void validate(Shopper s) {
        if (s == null) throw new InvalidDataException("Shopper is required");
        if (s.getFullName() == null || s.getFullName().isBlank()) throw new InvalidDataException("Full name is required");
        if (s.getEmail() == null || s.getEmail().isBlank()) throw new InvalidDataException("Email is required");
    }
}
