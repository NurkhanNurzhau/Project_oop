package org.example.sql_connect.pool;

import java.util.List;
import java.util.Optional;

public interface DataPool<T> {
    void add(T item);
    List<T> all();
    Optional<T> findById(int id);
    boolean removeById(int id);
    void clear();
}
