package br.com.kath.controller.car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;
import br.com.kath.model.ProdutoModel;

public class FinalizePurchase {
	
	private Connection connection;
	
	public FinalizePurchase() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void generateNF(int clientId) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "SELECT * FROM shopping_carts WHERE clientId = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, clientId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				System.out.println("\nNão há itens no seu carrinho!");
				return;
			}
			
			System.out.println("--------------------------------------------------");
			System.out.println("Comprador: " + getClientName(clientId));
			System.out.println("--------------------------------------------------");
			System.out.printf("%-10s     %-4s     %-10s     %-10s \n", "Produto", "Qtd", "V Unit", "V Total");
			System.out.println("--------------------------------------------------");
			
			resultSet.previous();
			
			while (resultSet.next()) {
				System.out.printf("%-10s     %-4s     %-10s     %-10s \n", 
						getProductName(resultSet.getInt("productId")),
						resultSet.getInt("productAmount"),
						getProductPrice(resultSet.getInt("productId")),
						getProductPrice(resultSet.getInt("productId")) * resultSet.getInt("productAmount")
				);
				
			}
			
			System.out.println("--------------------------------------------------");
			System.out.println("                        Total:         R$  " + calculateTotal(clientId));
			System.out.println("--------------------------------------------------");
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		cleanCart(clientId);
		
	}
	
	private void cleanCart(int clientId) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "DELETE FROM shopping_carts WHERE clientId = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, clientId);
			
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	private double calculateTotal(int clientId) {
		PreparedStatement preparedStatement;
		double total = 0;
		
		try {
			String sql = "SELECT * FROM shopping_carts WHERE clientId = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, clientId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				total += resultSet.getInt("productAmount") * getProductPrice(resultSet.getInt("productId"));
			}
			
			return total;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	private double getProductPrice(int productId) {
		PreparedStatement preparedStatement;
		var product = new ProdutoModel();
		
		try {
			String sql = "SELECT * FROM products WHERE cod = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, productId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				System.out.println("\nEste produto não existe");
				return 0;
			} else {
				product.setProductPrice(resultSet.getDouble("productPrice"));
			}
			
			return product.getProductPrice();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	private String getProductName(int productId) {
		PreparedStatement preparedStatement;
		var product = new ProdutoModel();
		
		try {
			String sql = "SELECT * FROM products WHERE cod = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, productId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				System.out.println("\nEste produto não existe");
				return null;
			} else {
				product.setProductName(resultSet.getString("productName"));
			}
			
			return product.getProductName();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String getClientName(int clientId) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "SELECT * FROM clients WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, clientId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				System.out.println("\nCliente não encontrado!");
				return null;
			}
			
			return resultSet.getString("clientName");
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
