package br.com.kath.controller.person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

public class RemovePerson {
	
	private Scanner input = new Scanner(System.in);
	private Connection connection;
	
	public RemovePerson() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void removePerson() {
		PreparedStatement preparedStatement;
		String name;
		int id;
		Login login = new Login();
		
		System.out.println("\n--- REMOÇÃO ---\n");
		System.out.println("Informe o nome da pessoa a ser removida: ");
		name = input.next();
		
		id = login.validateLogin(name);
		
		if (id == -1) {
			System.out.println("\nPessoa não existe no sistema!");
			return;
		}
		
		try {
			String sql = "DELETE FROM clients WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		return;
	}
	
}
