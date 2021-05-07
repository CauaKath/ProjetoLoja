package br.com.kath.controller.car;

import br.com.kath.model.PersonModel;

public class CarProductList {

	public void carProductsList(PersonModel person) {
		
		if (person.getCar().getCarProducts().size() == 0) {
			System.out.println("\nNão há itens no carrinhos");
			return;
		}
		
		System.out.println("\n---- PRODUTOS NO CARRINHO ----\n");
		System.out.printf("| %10s | %8s | %4s | \n", "Produto", "Preço", "Qntd");
		
		for (int i = 0; i < person.getCar().getCarProducts().size(); i ++) {
			System.out.printf("| %10s | %8s | %4s | \n",
					person.getCar().getCarProducts().get(i).getProductName(),
					person.getCar().getCarProducts().get(i).getProductPrice(),
					person.getCar().getCarProducts().get(i).getProductQuantity()
			);
		}
	}
	
}
