package org.example.sql_connect.pattern;

import org.example.sql_connect.dao.ProductRepository;
import org.example.sql_connect.dao.ShopperRepository;
import org.springframework.stereotype.Component;

@Component
public class RepositoryFactory {

    private final ProductRepository productRepository;
    private final ShopperRepository shopperRepository;

    public RepositoryFactory(ProductRepository productRepository, ShopperRepository shopperRepository) {
        this.productRepository = productRepository;
        this.shopperRepository = shopperRepository;
    }

    public ProductRepository productRepo() { return productRepository; }
    public ShopperRepository shopperRepo() { return shopperRepository; }
}
