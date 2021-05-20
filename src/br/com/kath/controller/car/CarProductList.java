package br.com.kath.controller.car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;

public class CarProductList {
	
	private Connection connection;
	
	public CarProductList() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public boolean carProductsList(int clientId) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "SELECT * FROM shopping_carts WHERE clientId = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, clientId);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			if(!resultSet.next()) {
				System.out.println("\nNão possui produtos no carrinho!");
				return false;
			}
			
			System.out.println("\n---- PRODUTOS NO CARRINHO ----\n");
			System.out.printf("| %-2s | %-14s | %-8s | %-4s | \n", "ID", "Nome", "Preço", "Qntd");
			
			resultSet.previous();
			
			while (resultSet.next()) {
				listProductsInCar(resultSet.getInt("productId"), resultSet.getInt("productAmount"));
			}
				
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private void listProductsInCar(int productId, int productAmount) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "SELECT * FROM products WHERE cod = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, productId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("\nEste produto não consta no estoque");
				return;
			}
			
			resultSet.previous();
			
			while(resultSet.next()) {
				System.out.printf("| %-2s | %-14s | %-8s | %-4s | \n",
						resultSet.getInt("cod"),
						resultSet.getString("productName"),
						resultSet.getDouble("productPrice"),
						productAmount
				);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
}
