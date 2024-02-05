package application;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;

public class eCommerceaApplication {
    public static void main(String[] args) {

		ProductDao productDao = DaoFactory.createProductDao();

		System.out.println("Test Insert product");
		Product newProduct = new Product( "Product name", "Product description", 150.0, 2);
		productDao.insert(newProduct);
		if (newProduct.getId() > 0) {
			System.out.println("Inserted! New id = " + newProduct.getId());
		} else {
			System.out.println("Product not iserted!");
		}
	}
}
