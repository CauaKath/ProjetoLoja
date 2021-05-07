package br.com.kath.controller.product;

import java.util.List;

import br.com.kath.model.ProdutoModel;

public class StorageList {

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
	
}
