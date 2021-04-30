package br.com.kath.view;

import java.util.ArrayList;
import java.util.List;

import br.com.kath.controller.CarController;
import br.com.kath.controller.PersonController;
import br.com.kath.controller.ProdutoController;
import br.com.kath.model.PersonModel;
import br.com.kath.model.ProdutoModel;

public class ProgramaPrincipal {
	
	static List<ProdutoModel> products = new ArrayList<ProdutoModel>();
	static List<PersonModel> people = new ArrayList<PersonModel>();
	
	// Controller Object
	static ProdutoController productController = new ProdutoController();
	static CarController carController = new CarController();
	static PersonController personController = new PersonController();

	public static void main(String[] args) {
		
		mainMenu();

	}
	
	public static void mainMenu() {
		System.out.println("\n--- ENTRADA ---\n");
		System.out.println("1) Adm");
		System.out.println("2) Gestor de estoque");
		System.out.println("3) Cliente");
		System.out.println("4) Sair");
		int option = personController.option();
		
		switch (option) {
		case 1:
			admMenu();
			
		case 2:
			managerMenu();
			
		case 3:
			var user = personController.login(people);
			clientMenu(user);
			
		case 4:
			System.out.println("\nSistema encerrado!!!");
			System.exit(0);

		default:
			System.out.println("\nOpção inválida!!!");
			mainMenu();
		}
	}
	
	public static void admMenu() {
		personController.menu();
		int option = personController.option();
		
		switch (option) {
		case 1:
			people.add(personController.register(people));
			admMenu();

		case 2:
			people.remove(personController.removePerson(people));
			admMenu();
			
		case 3:
			mainMenu();
			
		default:
			System.out.println("\nOpção inválida!!!");
			admMenu();
		}
	}
	
	public static void managerMenu() {
		productController.menu();
		int option = productController.option();
		
		switch (option) {
		case 1:
			products.add(productController.registerProduct());
			managerMenu();
		
		case 2:
			productController.storageList(products);
			managerMenu();
		
		case 3:
			productController.editProduct(products);
			managerMenu();
			
		case 4:
			productController.removeProduct(products);
			managerMenu();
		
		case 5:
			mainMenu();
		
		default:
			System.out.println("\nOpção inválida!!!");
			managerMenu();
		}
	}
	
	public static void clientMenu(PersonModel user) {
		carController.carMenu();
		int option = carController.option();
		
		switch (option) {
		case 1:
			user.getCar().getCarProducts().add(carController.addProductInCar(products));
			clientMenu(user);
		
		case 2:
			carController.editQntdInCar(user, products);
			clientMenu(user);
		
		case 3:
			carController.removeProductOutCar(user);
			clientMenu(user);
		
		case 4:
			carController.carProductsList(user);
			clientMenu(user);
		
		case 5:
			carController.finalizePurchase(user);
			clientMenu(user);
			
		case 6:
			mainMenu();
			
		default:
			System.out.println("\nOpção inválida!!!");
			clientMenu(user);
		}
	}

}
