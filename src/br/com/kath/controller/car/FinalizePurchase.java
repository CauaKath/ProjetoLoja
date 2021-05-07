package br.com.kath.controller.car;

import java.util.List;

import br.com.kath.model.CarModel;
import br.com.kath.model.PersonModel;
import br.com.kath.model.ProdutoModel;

public class FinalizePurchase {

	public void finalizePurchase(PersonModel person) {
		var carModel = this.calculateTotal(person.getCar().getCarProducts());
		
		if (person.getCar().getCarProducts().size() == 0) {
			System.out.println("\nNão há nada em seu carrinho!");
			return;
		}
		
		System.out.println("--------------------------------------------------");
		System.out.println("Comprador: " + person.getNome());
		System.out.println("--------------------------------------------------");
		System.out.printf("%-10s     %-4s     %-10s     %-10s \n", "Produto", "Qtd", "V Unit", "V Total");
		System.out.println("--------------------------------------------------");
		for (int i = 0; i < person.getCar().getCarProducts().size(); i++) {
			System.out.printf("%-10s     %-4s     %-10s     %-10s \n",
					person.getCar().getCarProducts().get(i).getProductName(),
					person.getCar().getCarProducts().get(i).getProductQuantity(),
					"R$  " + person.getCar().getCarProducts().get(i).getProductPrice(),
					"R$  " + person.getCar().getCarProducts().get(i).getStorageBalance());
		}
		System.out.println("--------------------------------------------------");
		System.out.println("                        Total:         R$  " + carModel.getTotal());
		System.out.println("--------------------------------------------------");
		
		for (int i = 0; i < person.getCar().getCarProducts().size(); i ++) {
			person.getCar().getCarProducts().remove(i);
		}
	}
	
	private CarModel calculateTotal(List<ProdutoModel> carProducts) {
		double total = 0;
		var carModel = new CarModel();
		for (int i = 0; i < carProducts.size(); i++) {
			total += carProducts.get(i).getProductPrice() * carProducts.get(i).getProductQuantity();
		}
		carModel.setTotal(total);
		return carModel;
	}
	
}
