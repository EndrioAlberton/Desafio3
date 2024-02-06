package model.dao;

import model.entities.Product;

public interface ProductDao {

    void createProduct(Product obj);
    void updateProduct(Product obj);
    void deleteById(Integer id);
}
