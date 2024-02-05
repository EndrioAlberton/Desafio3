package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;

public class eCommerceaApplication {
    public static void main(String[] args) {

    	Scanner teclado = new Scanner(System.in);
    	
		ProductDao productDao = DaoFactory.createProductDao();

		System.out.println("\n Test Insert product");
		Product newProduct = new Product( "Product name", "Description", 150.0, 2);
		productDao.insert(newProduct);
		System.out.println("Inserted! New id = " + newProduct.getId());
		
       teclado.close();
    }
}
