package org.example.sql_connect.dao;

import org.example.sql_connect.entity.Shopper;

import java.util.List;

public interface ShopperRepository {
    int create(Shopper s);
    List<Shopper> readAll();
    Shopper findById(int id);
    int updateEmail(int id, String email);
    int delete(int id);
}
