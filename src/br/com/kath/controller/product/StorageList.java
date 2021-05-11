package br.com.kath.controller.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;

public class StorageList {
	
	private Connection connection;
	
	public StorageList() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public ResultSet listData() {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "select * from produto";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.println("\n---- PRODUTOS CADASTRADOS ----\n");
			System.out.printf("| %2s | %14s | %8s | %4s | %9s | \n", "ID", "Produto", "Pre�o", "Qntd", "R$ Total");
			
			while(resultSet.next()) {
				System.out.printf("| %2s | %14s | %8s | %4s | %9s | \n", 
						resultSet.getInt("cod"),
						resultSet.getString("productName"),
						resultSet.getDouble("productPrice"),
						resultSet.getInt("productQuantity"),
						resultSet.getDouble("storageBalance")
				);
			}
			
			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}
	
}
