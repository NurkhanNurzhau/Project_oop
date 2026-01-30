package org.example.sql_connect.service;

import org.example.sql_connect.dao.ShopperDAO;
import org.example.sql_connect.entity.Shopper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopperService {

    private final ShopperDAO dao;

    public ShopperService(ShopperDAO dao) {
        this.dao = dao;
    }

    public Shopper create(Shopper s) {
        dao.create(s);
        List<Shopper> all = dao.readAll();
        return all.isEmpty() ? null : all.get(all.size() - 1);
    }

    public List<Shopper> readAll() {
        return dao.readAll();
    }

    public Shopper findById(int id) {
        return dao.findById(id);
    }

    public boolean updateEmail(int id, String email) {
        return dao.updateEmail(id, email) > 0;
    }

    public boolean delete(int id) {
        return dao.delete(id) > 0;
    }
}
