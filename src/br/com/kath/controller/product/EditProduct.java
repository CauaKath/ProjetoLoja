package br.com.kath.controller.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.kath.model.ProdutoModel;

public class EditProduct {
	
	private Scanner input = new Scanner(System.in);
	private Connection connection;
	private ProdutoModel product = new ProdutoModel();

	public EditProduct() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public ProdutoModel editProduct() {
		PreparedStatement preparedStatement;
		
		StorageList storageList = new StorageList();
		int productId, fieldIndex;
		
		if (storageList.listData() == null) {
			return null;
		}
			
		System.out.println("\n---- EDITAR DADOS DE PRODUTO ----\n");
		System.out.print("Informe o Id do produto: ");
		productId = input.nextInt();
		
		try {
			String sql = "SELECT * FROM produto WHERE cod = ?";
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
		
		System.out.print("\n---- CAMPOS ----\n" + 
				"\n1) Nome do produto" + 
				"\n2) Preço do produto" + 
				"\n3) Quantidade do produto \n" + 
				"\nInforme o campo que deseja editar: ");
		fieldIndex = input.nextInt();
		
		switch (fieldIndex) {
		case 1:
			editProductName(productId);
			break;
		
		case 2:
			editProductPrice(productId);
			break;
		
		case 3:
			editProductQuantity(productId);
			break;
		}

		return product;
	}
	
	private ProdutoModel editProductName(int productId) {
		PreparedStatement preparedStatement;
		
		System.out.print("\nInforme o novo nome do produto: ");
		product.setProductName(input.next());
		
		try {
			String sql = "UPDATE produto SET productName = ? WHERE cod = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setInt(2, productId);
			
			preparedStatement.execute();
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return product;
	}
	
	private ProdutoModel editProductPrice(int productId) {
		PreparedStatement preparedStatement;
		
		System.out.print("\nInforme o novo preço do produto: ");
		product.setProductPrice(input.nextDouble());
		
		product.setStorageBalance(product.getProductPrice() * 
				product.getProductQuantity());
		
		try {
			String sql = "UPDATE produto SET productPrice = ?, storageBalance = ? "
					+ " WHERE cod = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setDouble(1, product.getProductPrice());
			preparedStatement.setDouble(2, product.getStorageBalance());
			preparedStatement.setInt(3, productId);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return product;
	}
	
	private ProdutoModel editProductQuantity(int productId) {
		PreparedStatement preparedStatement;
		
		System.out.print("\nInforme a nova quantidade do produto: ");
		product.setProductQuantity(input.nextInt());
		
		product.setStorageBalance(product.getProductPrice() * 
				product.getProductQuantity());
		
		try {
			String sql = "UPDATE produto SET productQuantity = ?, storageBalance = ? "
					+ " WHERE cod = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, product.getProductQuantity());
			preparedStatement.setDouble(2, product.getStorageBalance());
			preparedStatement.setInt(3, productId);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return product;
	}
	
}
