package br.com.kath.controller.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

public class RemoveProduct {
	
	private Scanner input = new Scanner(System.in);
	private Connection connection;

	public RemoveProduct() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public boolean checkIfProductExists(int productId) {
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
	
	public void removeProduct() {
		PreparedStatement preparedStatement;
		StorageList storageList = new StorageList();
		
		if (storageList.listData() == null) {
			return;
		}
		
		System.out.println("\n---- REMOVER PRODUTO ----\n");
		System.out.print("Informe o ID do produto: ");
		int productId = input.nextInt();
		
		try {
			
			if (!this.checkIfProductExists(productId)) {
				return;
			}
			
			String sql = "DELETE FROM products WHERE cod = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, productId);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		return;
	}
	
}
