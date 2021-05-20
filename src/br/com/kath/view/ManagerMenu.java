package br.com.kath.view;

import java.util.Scanner;

import br.com.kath.controller.product.EditProduct;
import br.com.kath.controller.product.ProductMenu;
import br.com.kath.controller.product.RegisterProduct;
import br.com.kath.controller.product.RemoveProduct;
import br.com.kath.controller.product.StorageList;

public class ManagerMenu {
	
	private Scanner input = new Scanner(System.in);

	public void managerMenu() {
		ProductMenu productMenu = new ProductMenu();
		RegisterProduct registerProduct = new RegisterProduct();
		StorageList storageList = new StorageList();
		EditProduct editProduct = new EditProduct();
		RemoveProduct removeProduct = new RemoveProduct();
		
		productMenu.menu();
		int option = option();
		
		switch (option) {
		case 1:
			registerProduct.registerProduct();
			this.managerMenu();
			break;
		
		case 2:
			storageList.listData();
			this.managerMenu();
			break;
		
		case 3:
			editProduct.editProduct();
			this.managerMenu();
			break;
			
		case 4:
			removeProduct.removeProduct();
			this.managerMenu();
			break;
		
		case 5:
			break;
		
		default:
			System.out.println("\nOpção inválida!!!");
			this.managerMenu();
			break;
		}
	}
	
	private int option() {
		System.out.print("> ");
		return input.nextInt();
	}
	
	
}
