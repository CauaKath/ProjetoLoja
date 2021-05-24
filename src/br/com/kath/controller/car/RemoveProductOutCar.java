package br.com.kath.controller.car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

public class RemoveProductOutCar {
	
	private Scanner input = new Scanner(System.in);
	private Connection connection;
	
	public RemoveProductOutCar() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void removeProductOutCar(int clientId) {
		PreparedStatement preparedStatement;
		int productId, productAmount;
		double productPrice;
		var carProductList = new CarProductList();
		var updateAmountInStorage = new UpdateAmountInStorage();
		
		if (carProductList.carProductsList(clientId) == false) {
			return;
		}
		
		System.out.print("\nInforme o ID do produto que deseja remover do carrinho: ");
		productId = input.nextInt();
		
		productAmount = getAmountInStock(productId) + getProductAmount(productId);
		productPrice = getProductPrice(productId);
		
		try {
			
			if (!this.checkIfProductExists(productId)) {
				return;
			}
			
			String sql = "DELETE FROM shopping_carts WHERE productId = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, productId);
			
			updateAmountInStorage.updateAmountInStorage(productId, productAmount, productPrice);
			
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	private boolean checkIfProductExists(int productId) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "SELECT * FROM products WHERE cod = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, productId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				System.out.println("\nEste produto não existe");
				return false;
			} else {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int getProductAmount(int productId) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "SELECT * FROM shopping_carts WHERE productId = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, productId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("\nEste produto não existe no carrinho");
				return -1;
			}
			
			return resultSet.getInt("productAmount");
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int getAmountInStock(int productId) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "SELECT * FROM products WHERE cod = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, productId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("\nEste produto não existe no carrinho");
				return -1;
			}
			
			return resultSet.getInt("productQuantity");
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public double getProductPrice(int productId) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "SELECT * FROM products WHERE cod = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, productId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("\nEste produto não existe no carrinho");
				return -1;
			}
			
			return resultSet.getInt("productPrice");
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
}
