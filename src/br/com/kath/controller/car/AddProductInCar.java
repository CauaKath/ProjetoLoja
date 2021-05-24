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
		var updateAmountInStorage = new UpdateAmountInStorage();
		var editQuantityInCar = new EditQuantityInCar();
		
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
		
		if (!checkIfProductAlreadyExists(productId)) {
			System.out.print("\nInforme a quantidade do produto: ");
			productQntd = input.nextInt();
			
			editQuantityInCar.updateProductAmount(productId, getAmountInCar(productId) + productQntd);
			
			updateAmountInStorage.updateAmountInStorage(productId,
					product.getProductQuantity() - productQntd, product.getProductPrice());
			
			return product;
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
			
			updateAmountInStorage.updateAmountInStorage(productId,
					product.getProductQuantity() - productQntd, product.getProductPrice());
			
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return product;
	}
	
	private boolean checkIfProductAlreadyExists(int productId) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "SELECT * FROM shopping_carts WHERE productId = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, productId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return false;
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private int getAmountInCar(int productId) {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "SELECT * FROM shopping_carts WHERE productId = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, productId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("\nProduto não existe no carrinho!");
				return -1;
			}
			
			return resultSet.getInt("productAmount");
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
}
