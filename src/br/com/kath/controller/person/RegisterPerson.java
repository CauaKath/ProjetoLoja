package br.com.kath.controller.person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

public class RegisterPerson {

	private Scanner input = new Scanner(System.in);
	private Connection connection;
	
	public RegisterPerson() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public void register() {
		PreparedStatement preparedStatement;
		String name;
		
		System.out.println("\n--- CADASTRO ---\n");
		System.out.println("Informe o seu nome: ");
		name = input.next();
		
		try {
			String sql = "INSERT INTO clients (clientName) VALUES (?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, name);
			
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
	}
	
}
