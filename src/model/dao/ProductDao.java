package model.dao;

import java.util.List;

import model.entities.Product;

public interface ProductDao {

    void createProduct(Product obj);
    void updateProduct(Product obj);
    List<Product> findAll();
    void deleteById(Integer id);
}
