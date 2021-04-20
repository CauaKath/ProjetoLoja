package br.com.kath.view;

import java.util.ArrayList;
import java.util.List;

import br.com.kath.controller.ProdutoController;
import br.com.kath.model.ProdutoModel;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		
		List<ProdutoModel> products = new ArrayList<ProdutoModel>();
		
		// Controller Object
		ProdutoController produtoController = new ProdutoController();
		
		// Output loop controller
		boolean getOut = false;
		
		do {
			produtoController.menu();
			int option = produtoController.option();
			
			switch (option) {
			case 1:
				products.add(produtoController.registerProduct());
				break;
			
			case 2:
				produtoController.storageList(products);
				break;
			
			case 3:
				produtoController.editProduct(products);
				break;
				
			case 4:
				products.remove(produtoController.removeProduct(products));
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
