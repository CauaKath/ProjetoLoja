package br.com.kath.view;

import java.util.ArrayList;
import java.util.List;

import br.com.kath.controller.CarController;
import br.com.kath.controller.ProdutoController;
import br.com.kath.model.ProdutoModel;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		
		List<ProdutoModel> products = new ArrayList<ProdutoModel>();
		List<ProdutoModel> car = new ArrayList<ProdutoModel>();
		
		// Controller Object
		ProdutoController productController = new ProdutoController();
		CarController carController = new CarController();
		
		// Output loop controller
		boolean getOut = false;
		
		do {
			productController.menu();
			int option = productController.option();
			
			switch (option) {
			case 1:
				products.add(productController.registerProduct());
				break;
			
			case 2:
				productController.storageList(products);
				break;
			
			case 3:
				productController.editProduct(products);
				break;
				
			case 4:
				productController.removeProduct(products);
				break;
			
			case 5:
				carController.carMenu();
				option = carController.option();
				
				switch (option) {
				case 1:
					car.add(carController.addProductInCar(products));
					break;
				
				case 4:
					carController.carProductsList(car);
					break;
				}
				break;
			
			case 9:
				getOut = true;
				break;
			
			default:
				System.out.println("Opção inválida!!!");
				break;
			}
			
		} while (!getOut);
		
		System.out.println("Sistema encerrado!!!");

	}

}
