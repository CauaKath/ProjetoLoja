package br.com.kath.controller;

import java.util.List;
import java.util.Scanner;

import br.com.kath.model.CarModel;
import br.com.kath.model.ProdutoModel;

public class CarController {

	private Scanner input;
	
	public CarController() {
		input = new Scanner(System.in);
	}
	
	public int option() {
		System.out.print("> ");
		return input.nextInt();
	}
	
	public void carMenu() {
		System.out.println("\n--- MENU ---\n");
		System.out.println("1) Adicionar produto");
		System.out.println("2) Alterar quantidade no carrinho");
		System.out.println("3) Remover produto");
		System.out.println("4) Listar produtos no carrinho");
		System.out.println("5) Finalizar compra");
		System.out.println("--------------------");
	}
	
	public ProdutoModel addProductInCar(List<ProdutoModel> products) {
		var productController = new ProdutoController();
		var product = new ProdutoModel();
		
		int productId, productQntd;
		
		productController.storageList(products);
		
		System.out.print("\nInforme o ID do produto que deseja adicionar ao carrinho: ");
		productId = input.nextInt();
		
		if (productId > products.size()) {
			System.out.println("\nProduto não existe!");
			return null;
		}
		
		System.out.print("\nInforme a quantidade do produto: ");
		productQntd = input.nextInt();
		
		if (productQntd > products.get(productId - 1).getProductQuantity()) {
			System.out.println("\nQuantidade maior do que a listada em estoque");
			return null;
		}
		
		product.setProductQuantity(productQntd);
		products.get(productId - 1).setProductQuantity(products.get(productId - 1).getProductQuantity() - productQntd);
		products.get(productId - 1).setStorageBalance(products.get(productId - 1).getProductQuantity() * products.get(productId - 1).getProductPrice());
		
		product.setProductName(products.get(productId - 1).getProductName());
		product.setProductPrice(products.get(productId - 1).getProductPrice());
		
		return product;
	}
	
	public void editQntdInCar(List<ProdutoModel> carProducts, List<ProdutoModel> products) {
		int productQntd, productId;
		String productName;
		
		this.carProductsList(carProducts);
		
		System.out.print("\nInforme o nome do produto que deseja alterar a quantidade: ");
		productName = input.next();
		
		productId = this.nameValidate(carProducts, productName);
		
		if (productId == -1) {
			System.out.println("\nProduto não existe no carrinho!");
			return;
		}
		
		System.out.print("\nInforme a quantidade do produto que deseja adicionar: ");
		productQntd = input.nextInt();
		
		if (productQntd > products.get(productId).getProductQuantity()) {
			System.out.println("\nQuantidade maior do que a listada em estoque");
			return;
		}
		
		carProducts.get(productId).setProductQuantity(carProducts.get(productId).getProductQuantity() + productQntd);
		
		products.get(productId).setProductQuantity(products.get(productId).getProductQuantity() - productQntd);
		products.get(productId).setStorageBalance(products.get(productId).getProductQuantity() * products.get(productId).getProductPrice());
		
		return;
	}
	
	public void removeProductOutCar(List<ProdutoModel> carProducts) {
		int productId;
		String productName;
		
		this.carProductsList(carProducts);
		
		System.out.print("\nInforme o nome do produto que deseja remover do carrinho: ");
		productName = input.next();
		
		productId = this.nameValidate(carProducts, productName);
		
		if (productId == -1) {
			System.out.println("\nProduto não existe no carrinho!");
			return;
		}
		
		carProducts.remove(productId);
		
		return;
	}
	
	public void carProductsList(List<ProdutoModel> carProducts) {
		System.out.println("\n---- PRODUTOS NO CARRINHO ----\n");
		System.out.printf("| %10s | %8s | %4s | \n", "Produto", "Preço", "Qntd");
		
		for (int i = 0; i < carProducts.size(); i ++) {
			System.out.printf("| %10s | %8s | %4s | \n",
					carProducts.get(i).getProductName(),
					carProducts.get(i).getProductPrice(),
					carProducts.get(i).getProductQuantity()
			);
		}
	}
	
	public void finalizePurchase(List<ProdutoModel> carProducts) {
		var carModel = this.calculateTotal(carProducts);
		
		if (carProducts.size() == 0) {
			System.out.println("\nNão há nada em seu carrinho!");
			return;
		}
		
		System.out.println("\nCalculando total...");
		try { Thread.sleep(1000); } catch ( InterruptedException ex ) {}
		System.out.println("\nTotal no carrinho: " + carModel.getTotal());
		System.out.println("\nRecebendo pagamento...");
		try { Thread.sleep(1000); } catch ( InterruptedException ex ) {}
		System.out.println("\nCompra finalizada, volte sempre!");
		
		for (int i = 0; i < carProducts.size(); i ++) {
			carProducts.remove(i);
		}
	}
	
	public CarModel calculateTotal(List<ProdutoModel> carProducts) {
		double total = 0;
		var carModel = new CarModel();
		for (int i = 0; i < carProducts.size(); i++) {
			total += carProducts.get(i).getProductPrice() * carProducts.get(i).getProductQuantity();
		}
		carModel.setTotal(total);
		return carModel;
	}
	
	private int nameValidate(List<ProdutoModel> carProducts, String name) {
		for (int i = 0; i < carProducts.size(); i ++) {
			if (carProducts.get(i).getProductName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
}
