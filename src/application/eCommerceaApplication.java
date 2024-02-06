package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;

public class eCommerceaApplication {
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
        switch (opcao) {
            case 1:
				List<Product> productList = productDao.findAll();
                System.out.println("All products:");
				for (int i = 0; i < productList.size(); i++) {
					Product product = productList.get(i);
					System.out.print(product);
					if (i < productList.size() - 1) {
						System.out.print(",");
						System.out.println();
					}
				}
                break;
            case 2:
                System.out.println("In what way would you like to search for the product?");
                System.out.println("1 - By ID");
                System.out.println("2 - By nome");
                System.out.print("Option: ");
                int searchOption = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer de entrada
            
                switch (searchOption) {
                    case 1:
                    System.out.print("Enter the ID of the product you want to search for:");
                    int productId = scanner.nextInt();
                        Product foundProductById = productDao.findByIdProduct(productId).orElse(null);
                        if (foundProductById != null) {
                            System.out.println("Product found:");
                            System.out.println(foundProductById);
                        } else {
                            System.out.println("Product not found.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter the name of the product you want to search for:");
                        String productName = scanner.nextLine();
                        List<Product> foundProductsByName = productDao.findByNameProduct(productName);
                        if (!foundProductsByName.isEmpty()) {
                            System.out.println("Products found:");
                            for (Product product : foundProductsByName) {
                                System.out.println(product);
                            }
                        } else {
                            System.out.println("No products found with that name.");
                        }
                    break;
                default:
                    System.out.println("Invalid option.");
                }
                break;
            case 3:
				System.out.println("Test Insert product");
				Product newProduct = new Product( "Product name", "Description", 150.0, 2);
				productDao.insert(newProduct);
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
        scanner.close();
	}
}
