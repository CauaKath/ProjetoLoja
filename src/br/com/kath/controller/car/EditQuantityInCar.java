package br.com.kath.controller.car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.kath.model.ProdutoModel;

public class EditQuantityInCar {
	
	private Scanner input = new Scanner(System.in);
	private Connection connection;
	private ProdutoModel product = new ProdutoModel();;
	
	public EditQuantityInCar() { 
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void editQntdInCar(int clientId) {
		PreparedStatement preparedStatement;
		int productQntd, productId;
		var carProductsList = new CarProductList();
		var updateAmountInStorage = new UpdateAmountInStorage();
		
		if (carProductsList.carProductsList(clientId) == false) {
			return;
		}
		
		System.out.print("\nInforme o id do produto que deseja alterar a quantidade: ");
		productId = input.nextInt();
		
		try {
			String sql = "SELECT * FROM shopping_carts WHERE productId = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, productId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				System.out.println("\nEste produto não existe no carrinho");
				return;
			} else {
				product.setProductQuantity(resultSet.getInt("productAmount"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		System.out.print("\nInforme a quantidade do produto que deseja adicionar: ");
		productQntd = input.nextInt();
		
		if (product.getProductQuantity() < productQntd) {
			System.out.println("\nQuantidade maior do que a listada em estoque");
			return;
		}
		
		updateProductAmount(productId, productQntd);
		
		updateAmountInStorage.updateAmountInStorage(productId, 
				getProductQuantity(productId) - productQntd,
				getProductPrice(productId));
		
		return;
	}
	
	public void updateProductAmount(int productId, int productQntd) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "UPDATE shopping_carts SET productAmount = ? WHERE productId = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, product.getProductQuantity() + productQntd);
			preparedStatement.setInt(2, productId);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	private int getProductQuantity(int productId) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "SELECT * FROM products WHERE cod = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, productId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				System.out.println("\nEste produto não existe no estoque");
				return -1;
			}
			
			return resultSet.getInt("productQuantity");
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	private double getProductPrice(int productId) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "SELECT * FROM products WHERE cod = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, productId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				System.out.println("\nEste produto não existe no estoque");
				return -1;
			}
			
			return resultSet.getDouble("productPrice");
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
}
