package br.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

	private static DataBaseConnection dataBaseConnection;
	private Connection connection;
	
	private DataBaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/"+
					"loja?user=root&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
			);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nN�o foi poss�vel conectar no Banco de dados!");
		}
	}
	
	public static DataBaseConnection getInstance() {
		if (dataBaseConnection == null) dataBaseConnection = new DataBaseConnection();
		
		return dataBaseConnection;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
}
