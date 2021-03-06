package br.com.kath.controller.car;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.dao.DataBaseConnection;

public class UpdateAmountInStorage {

	private Connection connection;
	
	public UpdateAmountInStorage() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public void updateAmountInStorage(int productId, int productQntd, double productPrice) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "UPDATE products SET productQuantity = ?, storageBalance = ? WHERE cod = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, productQntd);
			preparedStatement.setDouble(2, productPrice * productQntd);
			preparedStatement.setInt(3, productId);
			
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}
