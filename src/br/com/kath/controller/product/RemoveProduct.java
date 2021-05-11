package br.com.kath.controller.product;

import java.util.List;
import java.util.Scanner;

import br.com.kath.model.ProdutoModel;

public class RemoveProduct {
	
	private Scanner input = new Scanner(System.in);

	public void removeProduct(List<ProdutoModel> products) {
		StorageList storageList = new StorageList();
		
		if (products.size() <= 0) {
			System.out.println("\nNão existem produtos para serem removidos!");
			return;
		}
		
		storageList.listData();
		
		System.out.println("\n---- REMOVER PRODUTO ----\n");
		System.out.print("Informe o ID do produto: ");
		int productId = input.nextInt();
		
		if (productId > products.size()) {
			System.out.println("\nEsse produto não existe!");
			return;
		}

		products.remove(productId - 1);
	}
	
}
