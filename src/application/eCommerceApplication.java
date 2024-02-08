package application;

import java.util.InputMismatchException;
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

        int option = scanner.nextInt();
		

        switch (option) {
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
                System.out.println("How would you like to search for the product?");
                System.out.println("1 - By ID");
                System.out.println("2 - By name");
                System.out.print("Enter your option: ");
                try {
                    int searchOption = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer de entrada

                    switch (searchOption) {
                        case 1:
                            System.out.print("Enter the ID of the product you want to search for: ");
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
                            System.out.print("Enter the name of the product you want to search for: ");
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
                            System.out.println("Invalid option. Please select 1 or 2.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer option.");
                }
                break;
            case 3:
                scanner.nextLine();
				System.out.println("Create new product");
                System.out.print("Product name: ");
                String name = scanner.nextLine();

                System.out.print("Description: ");
                String description = scanner.nextLine();
                
                System.out.print("Value: ");
                double value = scanner.nextDouble();
                
                System.out.print("Quantity: ");
                int quantity = scanner.nextInt();
                
                System.out.print("Voltage 1- 110V 2- 220V 3- Bivolt: ");
                int voltageOption = scanner.nextInt();
                String voltage = null;
                switch (voltageOption) {
                    case 1:
                    voltage = "110V";
                    break;
                    
                    case 2:
                    voltage = "220V";
                    break;
                    
                    case 3:
                    voltage = "BIVOLT";
                    break;
                    
                    default:
                    System.out.println("Invalid voltage option");
                    break;
                }
                
                scanner.nextLine();
                System.out.print("Brand: ");
                String brand = scanner.nextLine();

				Product newProduct = new Product(name, description, value, quantity, voltage, brand);
				productDao.createProduct(newProduct);


                if (newProduct.getId() == 0) {
                    System.out.println("\nProduct not created");
                } else {    
                    System.out.println("\nCreated! New product id: " + newProduct.getId());
                }
                break;

            case 4:
                // Atualizar produto: atualiza as informações de um pedido existente.
				System.out.println("Update product");
                System.out.print("Product id to update: ");
				int id = scanner.nextInt();
				Product product = productDao.findByIdProduct(id).orElse(null);
                System.out.printf("\nActual info about the product: \n Name: %s \n Description: %s \n Value: %.2f \n Quantity: %d\n\n", product.getName(), product.getDescription(), product.getValue(), product.getQuantity(), product.getVoltage(), product.getBrand());
				
                scanner.nextLine();
                System.out.print("New name:");
                String newName = scanner.nextLine();
                
				System.out.print("New description: ");
                String newDescription = scanner.nextLine();
                
				System.out.print("New value: ");
                double newValue = scanner.nextDouble();
                
				System.out.print("New quantity: ");
                int newQuantity = scanner.nextInt();

                System.out.print("Voltage 1- 110V 2- 220V 3- Bivolt: ");
                int newVoltageOption = scanner.nextInt();
                String newVoltage = null;
                switch (newVoltageOption) {
                    case 1:
                    newVoltage = "110V";
                    break;
                    
                    case 2:
                    newVoltage = "220V";
                    break;
                    
                    case 3:
                    newVoltage = "BIVOLT";
                    break;
                    
                    default:
                    System.out.println("Invalid voltage option");
                    break;
                }
                
                scanner.nextLine();
				System.out.print("New brand: ");
                String newBrand = scanner.nextLine();

                product.setName(newName);
                product.setDescription(newDescription);
                product.setValue(newValue);
                product.setQuantity(newQuantity);
                product.setVoltage(newVoltage);
                product.setBrand(newBrand);
                
				productDao.updateProduct(product);
                System.out.printf("\nUpdated product: \n Name: %s \n Description: %s \n Value: %.2f \n Quantity: %d\n\n", product.getName(), product.getDescription(), product.getValue(), product.getQuantity(), product.getVoltage(), product.getBrand());
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
