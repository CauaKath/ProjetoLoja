package br.com.kath.controller;

import java.util.List;
import java.util.Scanner;

import br.com.kath.model.CarModel;
import br.com.kath.model.ProdutoModel;

public class CarController {

	private Scanner input;
	
	public CarController() {
		input = new Scanner(System.in);
	}
	
	public int option() {
		System.out.print("> ");
		return input.nextInt();
	}
	
	public void carMenu() {
		System.out.println("\n--- MENU ---\n");
		System.out.println("1) Adicionar produto");
		System.out.println("2) Alterar quantidade no carrinho");
		System.out.println("3) Remover produto");
		System.out.println("4) Listar produtos no carrinho");
		System.out.println("5) Mostrar valor total no carrinho");
		System.out.println("6) Finalizar compra");
		System.out.println("--------------------");
	}
	
	public ProdutoModel addProductInCar(List<ProdutoModel> products) {
		var carModel = new CarModel();
		var productController = new ProdutoController();
		var product = new ProdutoModel();
		
		int productId, productQntd;
		
		productController.storageList(products);
		
		System.out.print("Informe o ID do produto que deseja adicionar ao carrinho: ");
		productId = input.nextInt();
		
		if (productId > products.size()) {
			System.out.println("Produto não existe!");
			return null;
		}
		
		System.out.print("Informe a quantidade do produto: ");
		productQntd = input.nextInt();
		
		if (productQntd > products.get(productId - 1).getProductQuantity()) {
			System.out.println("Quantidade maior do que a listada em estoque");
			return null;
		}
		
		product.setProductQuantity(productQntd);
		products.get(productId - 1).setProductQuantity(products.get(productId - 1).getProductQuantity() - productQntd);
		
		product.setProductName(products.get(productId - 1).getProductName());
		product.setProductPrice(products.get(productId - 1).getProductPrice());
		
		carModel.setTotal(product.getProductPrice() * product.getProductQuantity());
		
		return product;
	}
	
	public void carProductsList(List<ProdutoModel> carProducts) {
		System.out.println("\n---- PRODUTOS NO CARRINHO ----\n");
		System.out.printf("| %10s | %8s | %4s | \n", "Produto", "Preço", "Qntd");
		
		for (int i = 0; i < carProducts.size(); i ++) {
			System.out.printf("| %10s | %8s | %4s | \n",
					carProducts.get(i).getProductName(),
					carProducts.get(i).getProductPrice(),
					carProducts.get(i).getProductQuantity()
			);
		}
	}
	
}
