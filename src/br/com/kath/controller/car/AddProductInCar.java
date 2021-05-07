package br.com.kath.controller.car;

import java.util.List;
import java.util.Scanner;

import br.com.kath.controller.product.StorageList;
import br.com.kath.model.ProdutoModel;

public class AddProductInCar {

	private Scanner input = new Scanner(System.in);
	
	public ProdutoModel addProductInCar(List<ProdutoModel> products) {
		StorageList storageList = new StorageList();
		
		var product = new ProdutoModel();
		
		int productId, productQntd;
		
		storageList.storageList(products);
		
		System.out.print("\nInforme o ID do produto que deseja adicionar ao carrinho: ");
		productId = input.nextInt();
		
		if (productId > products.size()) {
			System.out.println("\nProduto não existe!");
			return null;
		}
		
		System.out.print("\nInforme a quantidade do produto: ");
		productQntd = input.nextInt();
		
		if (productQntd > products.get(productId - 1).getProductQuantity()) {
			System.out.println("\nQuantidade maior do que a listada em estoque");
			return null;
		}
		
		product.setProductQuantity(productQntd);
		products.get(productId - 1).setProductQuantity(products.get(productId - 1).getProductQuantity() - productQntd);
		products.get(productId - 1).setStorageBalance(products.get(productId - 1).getProductQuantity() * products.get(productId - 1).getProductPrice());
		
		product.setProductName(products.get(productId - 1).getProductName());
		product.setProductPrice(products.get(productId - 1).getProductPrice());
		product.setStorageBalance(product.getProductPrice() * product.getProductQuantity());
		
		return product;
	}
	
}
