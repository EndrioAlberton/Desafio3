package model.dao;

import java.util.List;
import java.util.Optional;

import model.entities.Product;

public interface ProductDao {
    void createProduct(Product obj);
    void updateProduct(Product obj);
    List<Product> findAll();
    Optional<Product> findById(Integer id);
    List<Product> findByName(String name);
}
