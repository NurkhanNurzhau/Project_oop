package org.example.sql_connect.dao;

import org.example.sql_connect.entity.Shopper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopperDAO {

    private final JdbcTemplate jdbc;

    public ShopperDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Shopper> mapper = (rs, rowNum) -> {
        Shopper s = new Shopper();
        s.setShopperId(rs.getInt("shopper_id"));
        s.setFullName(rs.getString("full_name"));
        s.setEmail(rs.getString("email"));
        s.setPhone(rs.getString("phone"));
        return s;
    };

    public int create(Shopper s) {
        String sql = "INSERT INTO shopper(full_name, email, phone) VALUES (?, ?, ?)";
        return jdbc.update(sql, s.getFullName(), s.getEmail(), s.getPhone());
    }

    public List<Shopper> readAll() {
        String sql = "SELECT shopper_id, full_name, email, phone FROM shopper ORDER BY shopper_id";
        return jdbc.query(sql, mapper);
    }

    public Shopper findById(int id) {
        String sql = "SELECT shopper_id, full_name, email, phone FROM shopper WHERE shopper_id = ?";
        List<Shopper> list = jdbc.query(sql, mapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    public int updateEmail(int id, String newEmail) {
        String sql = "UPDATE shopper SET email = ? WHERE shopper_id = ?";
        return jdbc.update(sql, newEmail, id);
    }

    public int delete(int id) {
        String sql = "DELETE FROM shopper WHERE shopper_id = ?";
        return jdbc.update(sql, id);
    }
}
