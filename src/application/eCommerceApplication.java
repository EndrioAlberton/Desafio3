package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;

public class eCommerceApplication {
    public static void main(String[] args) {


		Scanner scanner = new Scanner(System.in);
		ProductDao productDao = DaoFactory.createProductDao();

        System.out.println("Select an option:");
        System.out.println("1 - List products: return all products.");
        System.out.println("2 - Search product: Returns the information of a specific product.");
        System.out.println("3 - Register order: Create a new product.");
        System.out.println("4 - Update product: update the information of an existing order.");
        System.out.println("5 - Delete product: Delete an existing product.");
        System.out.print("Option: ");

        int opcao = scanner.nextInt();
		scanner.close();

        switch (opcao) {
            case 1:
                List<Product> productList = productDao.findAll();

                if (productList.isEmpty()) {
                    System.out.println("No products found.");
                } else {
                    System.out.println("All products:");
                    for (int i = 0; i < productList.size(); i++) {
                        Product product = productList.get(i);
                        System.out.print(product);
                        if (i < productList.size() - 1) {
                            System.out.print(",");
                            System.out.println();
                        }
                    }
                }
                break;
            case 2:
                // Buscar produto: Retorna as informações de um produto específico.
                break;
            case 3:
				System.out.println("Test Insert product");
				Product newProduct = new Product( "Product name", "Description", 150.0, 2);
				productDao.createProduct(newProduct);
				System.out.println("Inserted! New id = " + newProduct.getId());
                break;
            case 4:
                // Atualizar produto: atualiza as informações de um pedido existente.
                break;
            case 5:
                // Excluir produto: Excluir um produto existente.
                break;
            default:
                System.out.println("Invalid option.");
        }
	}
}
