package br.com.kath.controller;

import java.util.List;
import java.util.Scanner;

import br.com.kath.model.ProdutoModel;

public class ProdutoController {

	private Scanner input;

	public ProdutoController() {
		input = new Scanner(System.in);
	}

	public int option() {
		System.out.print("> ");
		return input.nextInt();
	}

	public void menu() {
		System.out.println("\n--- MENU ---\n");
		System.out.println("1) Cadastrar itens");
		System.out.println("2) Listar estoque");
		System.out.println("3) Editar item");
		System.out.println("4) Remover item");
		System.out.println("5) Realizar venda");
		System.out.println("9) Sair do sistema");
		System.out.println("--------------------");
	}

	public ProdutoModel registerProduct() {
		var productModel = new ProdutoModel();

		System.out.println("\n---- CADASTRAR ITENS ----\n");
		System.out.print("Produto: ");
		input.nextLine();
		productModel.setProductName(input.nextLine());
		System.out.print("Preço: R$");
		productModel.setProductPrice(input.nextDouble());
		System.out.print("Quantidade: ");
		productModel.setProductQuantity(input.nextInt());
		productModel.setStorageBalance(productModel.getProductQuantity() * productModel.getProductPrice());

		return productModel;
	}

	public List<ProdutoModel> storageList(List<ProdutoModel> products) {
		System.out.println("\n---- PRODUTOS CADASTRADOS ----\n");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s | \n", "ID", "Produto", "Preço", "Qntd", "R$ Total");
		
		for (int i = 0; i < products.size(); i ++) {
			System.out.printf("| %2s | %10s | %8s | %4s | %9s | \n",
					i + 1,
					products.get(i).getProductName(),
					products.get(i).getProductPrice(),
					products.get(i).getProductQuantity(),
					products.get(i).getStorageBalance()
			);
		}

		return products;
	}

	public ProdutoModel editProduct(List<ProdutoModel> products) {
		var product = new ProdutoModel();
		int productId, fieldIndex;
		
		if (products.size() <= 0) {
			System.out.println("Não existem produtos cadastrados");
			return null;
		}
		
		this.storageList(products);
			
		System.out.println("\n---- EDITAR DADOS DE PRODUTO ----\n");
		System.out.print("Informe o Id do produto: ");
		productId = input.nextInt() - 1;

		if (productId > products.size()) {
			System.out.println("Esse produto não existe!");
			return null;
		}
		
		System.out.print("---- CAMPOS ----" + 
				"\n1) Nome do produto" + 
				"\n2) Preço do produto" + 
				"\n3) Quantidade do produto" + 
				"\nInforme o campo que deseja editar: ");
		fieldIndex = input.nextInt();
		
		switch (fieldIndex) {
		case 1:
			System.out.print("Informe o novo nome do produto: ");
			product.setProductName(input.next());
			
			product.setProductPrice(products.get(productId).getProductPrice());
			product.setProductQuantity(products.get(productId).getProductQuantity());
			product.setStorageBalance(products.get(productId).getStorageBalance());
			
			products.set(productId, product);
			
			break;
		
		case 2:
			System.out.print("Informe o novo preço do produto: ");
			product.setProductPrice(input.nextDouble());
			
			product.setProductName(products.get(productId).getProductName());
			product.setProductQuantity(products.get(productId).getProductQuantity());
			product.setStorageBalance(product.getProductPrice() * products.get(productId).getProductQuantity());
			
			products.set(productId, product);
			
			break;
		
		case 3:
			System.out.print("Informe a nova quantidade do produto: ");
			product.setProductQuantity(input.nextInt());
			
			product.setProductName(products.get(productId).getProductName());
			product.setProductPrice(products.get(productId).getProductPrice());
			product.setStorageBalance(product.getProductQuantity() * products.get(productId).getProductPrice());
			
			products.set(productId, product);
			
			break;
		}

		return product;
	}

	public void removeProduct(List<ProdutoModel> products) {
		
		if (products.size() <= 0) {
			System.out.println("Não existem produtos para serem removidos!");
			return;
		}
		
		System.out.println("\n---- REMOVER PRODUTO ----\n");
		System.out.print("Informe o ID do produto: ");
		int productId = input.nextInt();
		
		if (productId > products.size()) {
			System.out.println("Esse produto não existe!");
			return;
		}

		products.remove(productId - 1);
	}

}
