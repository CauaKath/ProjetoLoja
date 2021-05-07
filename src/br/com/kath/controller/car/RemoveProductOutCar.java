package br.com.kath.controller.car;

import java.util.Scanner;

import br.com.kath.model.PersonModel;

public class RemoveProductOutCar {
	
	private Scanner input = new Scanner(System.in);

	public void removeProductOutCar(PersonModel person) {
		int productId;
		String productName;
		var carProductList = new CarProductList();
		var nameValidate = new NameValidate();
		
		carProductList.carProductsList(person);
		
		System.out.print("\nInforme o nome do produto que deseja remover do carrinho: ");
		productName = input.next();
		
		productId = nameValidate.nameValidate(person.getCar().getCarProducts(), productName);
		
		if (productId == -1) {
			System.out.println("\nProduto não existe no carrinho!");
			return;
		}
		
		person.getCar().getCarProducts().remove(productId);
		
		return;
	}
	
}
