package br.com.kath.controller.product;

import java.util.List;
import java.util.Scanner;

import br.com.kath.model.ProdutoModel;

public class EditProduct {
	
	private Scanner input = new Scanner(System.in);

	public void editProduct(List<ProdutoModel> products) {
		StorageList storageList = new StorageList();
		int productId, fieldIndex;
		
		if (products.size() <= 0) {
			System.out.println("\nNão existem produtos cadastrados");
			return;
		}
		
		storageList.listData();
			
		System.out.println("\n---- EDITAR DADOS DE PRODUTO ----\n");
		System.out.print("\nInforme o Id do produto: ");
		productId = input.nextInt() - 1;

		if (productId > products.size()) {
			System.out.println("\nEsse produto não existe!");
			return;
		}
		
		System.out.print("---- CAMPOS ----" + 
				"\n1) Nome do produto" + 
				"\n2) Preço do produto" + 
				"\n3) Quantidade do produto" + 
				"\nInforme o campo que deseja editar: ");
		fieldIndex = input.nextInt();
		
		switch (fieldIndex) {
		case 1:
			editProductName(products, productId);
			
			break;
		
		case 2:
			editProductPrice(products, productId);
			
			break;
		
		case 3:
			editProductQuantity(products, productId);
			
			break;
		}

		return;
	}
	
	private ProdutoModel editProductName(List<ProdutoModel> products, int productId) {
		var product = new ProdutoModel();
		
		System.out.print("\nInforme o novo nome do produto: ");
		product.setProductName(input.next());
		
		product.setProductPrice(products.get(productId).getProductPrice());
		product.setProductQuantity(products.get(productId).getProductQuantity());
		product.setStorageBalance(products.get(productId).getStorageBalance());
		
		products.set(productId, product);
		
		return product;
	}
	
	private ProdutoModel editProductPrice(List<ProdutoModel> products, int productId) {
		var product = new ProdutoModel();
		
		System.out.print("\nInforme o novo preço do produto: ");
		product.setProductPrice(input.nextDouble());
		
		product.setProductName(products.get(productId).getProductName());
		product.setProductQuantity(products.get(productId).getProductQuantity());
		product.setStorageBalance(product.getProductPrice() * products.get(productId).getProductQuantity());
		
		products.set(productId, product);
		
		return product;
	}
	
	private ProdutoModel editProductQuantity(List<ProdutoModel> products, int productId) {
		var product = new ProdutoModel();
		
		System.out.print("\nInforme a nova quantidade do produto: ");
		product.setProductQuantity(input.nextInt());
		
		product.setProductName(products.get(productId).getProductName());
		product.setProductPrice(products.get(productId).getProductPrice());
		product.setStorageBalance(product.getProductQuantity() * products.get(productId).getProductPrice());
		
		products.set(productId, product);
		
		return product;
	}
	
}
