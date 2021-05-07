package br.com.kath.controller.product;

import java.util.Scanner;

import br.com.kath.model.ProdutoModel;

public class RegisterProduct {
	
	private Scanner input = new Scanner(System.in);

	public ProdutoModel registerProduct() {
		var productModel = new ProdutoModel();

		System.out.println("\n---- CADASTRAR ITENS ----\n");
		System.out.print("Produto: ");
		productModel.setProductName(input.nextLine());
		System.out.print("Preço: R$");
		productModel.setProductPrice(input.nextDouble());
		System.out.print("Quantidade: ");
		productModel.setProductQuantity(input.nextInt());
		productModel.setStorageBalance(productModel.getProductQuantity() * productModel.getProductPrice());

		return productModel;
	}
	
}
