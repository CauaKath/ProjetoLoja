package br.com.kath.controller.person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

public class Login {

	private Scanner input = new Scanner(System.in);
	private Connection connection;
	
	public Login() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public int login() {
		String name;
		int clientId;
		
		System.out.println("\n--- Login ---\n");
		System.out.println("Informe o seu nome: ");
		name = input.next();
		
		clientId = this.validateLogin(name);
		
		if (clientId == -1) {
			System.out.println("\nLogin inválido!");
			return -1;
		} else {
			System.out.println("\nBem vindo a loja, " + name);
			return clientId;
		}
	}
	
	public int validateLogin(String name) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "SELECT * FROM clients WHERE clientName = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, name);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				return -1;
			} else {
				return resultSet.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	
}
