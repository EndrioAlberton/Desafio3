package model.dao;

import java.util.List;
import java.util.Optional;

import model.entities.Product;

public interface ProductDao {
	/*responsavel por inserir ... no banco de dados,
	este objeto a ser enviado como parametro de entrada 
	 */
    void createProduct(Product obj);
    void updateProduct(Product obj);
    void deleteById(Integer id);
    List<Product> findAll();
    Optional<Product> findByIdProduct(Integer id);
    List<Product> findByNameProduct(String name);
}
