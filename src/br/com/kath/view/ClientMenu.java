package br.com.kath.view;

import java.util.List;
import java.util.Scanner;

import br.com.kath.controller.car.AddProductInCar;
import br.com.kath.controller.car.CarMenu;
import br.com.kath.controller.car.CarProductList;
import br.com.kath.controller.car.EditQuantityInCar;
import br.com.kath.controller.car.FinalizePurchase;
import br.com.kath.controller.car.RemoveProductOutCar;
import br.com.kath.model.PersonModel;
import br.com.kath.model.ProdutoModel;

public class ClientMenu {
	
	private Scanner input = new Scanner(System.in);

	public void clientMenu(PersonModel user, List<ProdutoModel> products) {
		CarProductList carProductList = new CarProductList();
		AddProductInCar addProductInCar = new AddProductInCar();
		CarMenu carMenu = new CarMenu();
		EditQuantityInCar editQntdInCar = new EditQuantityInCar();
		RemoveProductOutCar removeProductOutCar = new RemoveProductOutCar();
		FinalizePurchase finalizePurchase = new FinalizePurchase();
		
		carMenu.carMenu();
		int option = option();
		
		switch (option) {
		case 1:
			user.getCar().getCarProducts().add(addProductInCar.addProductInCar(products));
			this.clientMenu(user, products);
			break;
		
		case 2:
			editQntdInCar.editQntdInCar(user, products);
			this.clientMenu(user, products);
			break;
		
		case 3:
			removeProductOutCar.removeProductOutCar(user);
			this.clientMenu(user, products);
			break;
		
		case 4:
			carProductList.carProductsList(user);
			this.clientMenu(user, products);
			break;
		
		case 5:
			finalizePurchase.finalizePurchase(user);
			this.clientMenu(user, products);
			break;
			
		case 6:
			break;
			
		default:
			System.out.println("\nOpção inválida!!!");
			this.clientMenu(user, products);
			break;
		}
	}
	
	public int option() {
		System.out.print("> ");
		return input.nextInt();
	}
	
}
