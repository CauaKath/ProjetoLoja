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
	
	public void storageList(List<ProdutoModel> products) {
		System.out.println("\n---- PRODUTOS CADASTRADOS ----\n");
		System.out.printf("| %10s | %8s | %4s | %9s | \n", "Produto", "Preço", "Quantidade", "R$ Total");
		for (ProdutoModel produtoModel : products) {
			System.out.println(produtoModel);
		}
	}
	
	public ProdutoModel removeProduct(List<ProdutoModel> products) {
		System.out.println("\n---- REMOVER ITENS ----\n");
		System.out.print("Produto: ");
		String produtoRemovido = input.next();
		
		for (int i = 0; i < products.size(); i ++) {
			if (products.get(i).getProductName().equals(produtoRemovido)) {
				return products.get(i);
			}
		}
		return null;
	}

}
