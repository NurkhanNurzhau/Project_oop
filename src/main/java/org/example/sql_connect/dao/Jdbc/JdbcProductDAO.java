package org.example.sql_connect.dao.Jdbc;

import org.example.sql_connect.dao.ProductRepository;
import org.example.sql_connect.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcProductDAO implements ProductRepository {

    private final JdbcTemplate jdbc;

    public JdbcProductDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Product> mapper = (rs, rowNum) -> {
        Product p = new Product();
        p.setProductId(rs.getInt("product_id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getBigDecimal("price").doubleValue());
        p.setStock(rs.getInt("stock"));
        return p;
    };

    @Override
    public int create(Product p) {
        String sql = "INSERT INTO e_commerce(name, price, stock) VALUES (?, ?, ?)";
        return jdbc.update(sql, p.getName(), p.getPrice(), p.getStock());
    }

    @Override
    public List<Product> readAll() {
        String sql = "SELECT product_id, name, price, stock FROM e_commerce ORDER BY product_id";
        return jdbc.query(sql, mapper);
    }

    @Override
    public Product findById(int id) {
        String sql = "SELECT product_id, name, price, stock FROM e_commerce WHERE product_id = ?";
        List<Product> list = jdbc.query(sql, mapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int updateStock(int id, int stock) {
        String sql = "UPDATE e_commerce SET stock = ? WHERE product_id = ?";
        return jdbc.update(sql, stock, id);
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM e_commerce WHERE product_id = ?";
        return jdbc.update(sql, id);
    }
}
