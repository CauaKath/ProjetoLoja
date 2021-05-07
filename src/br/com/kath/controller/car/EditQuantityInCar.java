package br.com.kath.controller.car;

import java.util.List;
import java.util.Scanner;

import br.com.kath.model.PersonModel;
import br.com.kath.model.ProdutoModel;

public class EditQuantityInCar {
	
	private Scanner input = new Scanner(System.in);

	public void editQntdInCar(PersonModel person, List<ProdutoModel> products) {
		int productQntd, productId;
		String productName;
		var carProductList = new CarProductList();
		var nameValidate = new NameValidate();
		
		carProductList.carProductsList(person);
		
		System.out.print("\nInforme o nome do produto que deseja alterar a quantidade: ");
		productName = input.next();
		
		productId = nameValidate.nameValidate(person.getCar().getCarProducts(), productName);
		
		if (productId == -1) {
			System.out.println("\nProduto não existe no carrinho!");
			return;
		}
		
		System.out.print("\nInforme a quantidade do produto que deseja adicionar: ");
		productQntd = input.nextInt();
		
		if (productQntd > products.get(productId).getProductQuantity()) {
			System.out.println("\nQuantidade maior do que a listada em estoque");
			return;
		}
		
		person.getCar().getCarProducts().get(productId).setProductQuantity(person.getCar().getCarProducts().get(productId).getProductQuantity() + productQntd);
		person.getCar().getCarProducts().get(productId).setStorageBalance(person.getCar().getCarProducts().get(productId).getProductQuantity() *
				person.getCar().getCarProducts().get(productId).getProductPrice());
		
		products.get(productId).setProductQuantity(products.get(productId).getProductQuantity() - productQntd);
		products.get(productId).setStorageBalance(products.get(productId).getProductQuantity() * products.get(productId).getProductPrice());
		
		return;
	}
	
}
