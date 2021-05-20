package br.com.kath.view;

import java.util.Scanner;

import br.com.kath.controller.car.AddProductInCar;
import br.com.kath.controller.car.CarMenu;
import br.com.kath.controller.car.CarProductList;
import br.com.kath.controller.car.EditQuantityInCar;
import br.com.kath.controller.car.FinalizePurchase;
import br.com.kath.controller.car.RemoveProductOutCar;

public class ClientMenu {
	
	private Scanner input = new Scanner(System.in);

	public void clientMenu(int clientId) {
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
			addProductInCar.addProductInCar(clientId);
			this.clientMenu(clientId);
			break;
		
		case 2:
			editQntdInCar.editQntdInCar(clientId);
			this.clientMenu(clientId);
			break;
		
		case 3:
			removeProductOutCar.removeProductOutCar(clientId);
			this.clientMenu(clientId);
			break;
		
		case 4:
			carProductList.carProductsList(clientId);
			this.clientMenu(clientId);
			break;
		
		case 5:
			finalizePurchase.generateNF(clientId);
			this.clientMenu(clientId);
			break;
			
		case 6:
			break;
			
		default:
			System.out.println("\nOpção inválida!!!");
			this.clientMenu(clientId);
			break;
		}
	}
	
	public int option() {
		System.out.print("> ");
		return input.nextInt();
	}
	
}
