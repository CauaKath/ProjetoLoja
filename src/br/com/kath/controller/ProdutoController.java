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
		var produtoModel = new ProdutoModel();

		System.out.println("\n---- CADASTRAR ITENS ----\n");
		System.out.print("Produto: ");
		input.nextLine();
		produtoModel.setProductName(input.nextLine());
		System.out.print("Preço: R$");
		produtoModel.setProductPrice(input.nextDouble());
		System.out.print("Quantidade: ");
		produtoModel.setProductQuantity(input.nextInt());
		produtoModel.setStorageBalance(produtoModel.getProductQuantity() * produtoModel.getProductPrice());

		return produtoModel;
	}

	public List<ProdutoModel> storageList(List<ProdutoModel> products) {
		System.out.println("\n---- PRODUTOS CADASTRADOS ----\n");
		System.out.printf("| %10s | %8s | %4s | %9s | \n", "Produto", "Preço", "Qntd", "R$ Total");

		products.forEach(produto -> {
			System.out.printf("| %10s | %8s | %4s | %9s | \n", produto.getProductName(), produto.getProductPrice(),
					produto.getProductQuantity(), produto.getStorageBalance());
		});

		return products;
	}

	public ProdutoModel editProduct(List<ProdutoModel> products) {
		var produto = new ProdutoModel();
		int productId, fieldIndex;

		System.out.println("\n---- EDITAR DADOS DE PRODUTO ----\n");
		System.out.print("Informe o Id do produto: ");
		productId = input.nextInt();

		System.out.print("---- CAMPOS ----" + 
				"\n1) Nome do produto" + 
				"\n2) Preço do produto" + 
				"\n3) Quantidade do produto" + 
				"\nInforme o campo que deseja editar: ");
		fieldIndex = input.nextInt();
		
		switch (fieldIndex) {
		case 1:
			System.out.print("Informe o novo nome do produto: ");
			produto.setProductName(input.next());
			
			produto.setProductPrice(products.get(productId).getProductPrice());
			produto.setProductQuantity(products.get(productId).getProductQuantity());
			produto.setStorageBalance(products.get(productId).getProductPrice() * products.get(productId).getProductQuantity());
			
			products.set(productId, produto);
			
			break;
		
		case 2:
			System.out.print("Informe o novo preço do produto: ");
			produto.setProductPrice(input.nextDouble());
			
			produto.setProductName(products.get(productId).getProductName());
			produto.setProductQuantity(products.get(productId).getProductQuantity());
			produto.setStorageBalance(products.get(productId).getProductPrice() * products.get(productId).getProductQuantity());
			
			products.set(productId, produto);
			
			break;
		}

		return produto;
	}

	public ProdutoModel removeProduct(List<ProdutoModel> products) {
		System.out.println("\n---- REMOVER ITENS ----\n");
		System.out.print("Produto: ");
		String produtoRemovido = input.next();

		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getProductName().equals(produtoRemovido)) {
				return products.get(i);
			}
		}

		return null;
	}

}
