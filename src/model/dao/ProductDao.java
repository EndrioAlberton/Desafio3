package model.dao;

import java.util.List;
import java.util.Optional;

import model.entities.Product;

public interface ProductDao {

    void insert(Product obj);
    void update(Product obj);
    List<Product> findAll();
    Optional<Product> findByIdProduct(Integer id);
    List<Product> findByNameProduct(String name);
}
