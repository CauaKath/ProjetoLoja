package br.com.kath.view;

import java.util.List;
import java.util.Scanner;

import br.com.kath.controller.product.EditProduct;
import br.com.kath.controller.product.ProductMenu;
import br.com.kath.controller.product.RegisterProduct;
import br.com.kath.controller.product.RemoveProduct;
import br.com.kath.controller.product.StorageList;
import br.com.kath.model.ProdutoModel;

public class ManagerMenu {
	
	private Scanner input = new Scanner(System.in);

	public void managerMenu(List<ProdutoModel> products) {
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
			this.managerMenu(products);
			break;
		
		case 2:
			storageList.listData();
			this.managerMenu(products);
			break;
		
		case 3:
			editProduct.editProduct();
			this.managerMenu(products);
			break;
			
		case 4:
			removeProduct.removeProduct(products);
			this.managerMenu(products);
			break;
		
		case 5:
			break;
		
		default:
			System.out.println("\nOpção inválida!!!");
			this.managerMenu(products);
			break;
		}
	}
	
	private int option() {
		System.out.print("> ");
		return input.nextInt();
	}
	
	
}
