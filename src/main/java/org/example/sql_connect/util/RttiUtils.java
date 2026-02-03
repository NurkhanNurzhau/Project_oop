package org.example.sql_connect.util;

import org.example.sql_connect.entity.BaseEntity;
import org.example.sql_connect.entity.Product;
import org.example.sql_connect.entity.Shopper;

public class RttiUtils {
    public static String detectEntityType(BaseEntity e) {
        if (e instanceof Product) return "Product";
        if (e instanceof Shopper) return "Shopper";
        return "Unknown";
    }
}
