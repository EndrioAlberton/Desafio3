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

        System.out.println("Selecione uma opção:");
        System.out.println("1 - Lista produto: deve retornar todos os produtos.");
        System.out.println("2 - Buscar produto: Retorna as informações de um produto específico.");
        System.out.println("3 - Cadastrar pedido: Cria um novo produto.");
        System.out.println("4 - Atualizar produto: atualiza as informações de um pedido existente.");
        System.out.println("5 - Excluir produto: Excluir um produto existente.");
        System.out.print("Opção: ");

        int opcao = scanner.nextInt();
		scanner.close();

        switch (opcao) {
            case 1:
				List<Product> productList = productDao.findAll();
				System.out.println("Todos os produtos:");
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
                // Buscar produto: Retorna as informações de um produto específico.
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
                System.out.println("Opção inválida.");
        }
	}
}
