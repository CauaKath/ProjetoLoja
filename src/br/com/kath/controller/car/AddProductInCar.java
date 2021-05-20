package br.com.kath.controller.car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.kath.controller.product.StorageList;
import br.com.kath.model.ProdutoModel;

public class AddProductInCar {

	private Scanner input = new Scanner(System.in);
	private Connection connection;
	private ProdutoModel product = new ProdutoModel();
	
	public AddProductInCar() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public ProdutoModel addProductInCar(int clientId) {
		PreparedStatement preparedStatement;
		var storageList = new StorageList();
		var removeProductFromStock = new RemoveProductFromStock();
		
		int productId, productQntd;
		
		if (storageList.listData() == null) {
			return null;
		}
		
		System.out.print("\nInforme o ID do produto que deseja adicionar ao carrinho: ");
		productId = input.nextInt();
		
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
				product.setProductPrice(resultSet.getDouble("productPrice"));
				product.setProductQuantity(resultSet.getInt("productQuantity"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		System.out.print("\nInforme a quantidade do produto: ");
		productQntd = input.nextInt();
		
		if (product.getProductQuantity() < productQntd) {
			System.out.println("\nQuantidade maior do que a listada em estoque");
			return null;
		}
		
		try {
			String sql = "INSERT INTO shopping_carts (clientId, productId, productAmount) "
					+ "VALUES(?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, clientId);
			preparedStatement.setInt(2, productId);
			preparedStatement.setInt(3, productQntd);
			
			removeProductFromStock.removeProductFromStock(productId,
					product.getProductQuantity() - productQntd, product.getProductPrice());
			
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return product;
	}
	
}
