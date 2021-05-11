package br.com.kath.controller.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.kath.model.ProdutoModel;

public class RegisterProduct {
	
	private Scanner input = new Scanner(System.in);
	private Connection connection;
	
	public RegisterProduct() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ProdutoModel registerProduct() {
		var productModel = new ProdutoModel();

		System.out.println("\n---- CADASTRAR ITENS ----\n");
		System.out.print("Produto: ");
		productModel.setProductName(input.nextLine());
		System.out.print("Preço: R$");
		productModel.setProductPrice(input.nextDouble());
		System.out.print("Quantidade: ");
		productModel.setProductQuantity(input.nextInt());
		productModel.setStorageBalance(productModel.getProductQuantity() * productModel.getProductPrice());

		try {
			String sql = "INSERT INTO produto (productName, productPrice, productQuantity, storageBalance)"
					+ "VALUES(?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, productModel.getProductName());
			preparedStatement.setDouble(2, productModel.getProductPrice());
			preparedStatement.setInt(3, productModel.getProductQuantity());
			preparedStatement.setDouble(4, productModel.getStorageBalance());
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			System.out.println("\nErro ao cadastrar os dados.");
		}
		
		return productModel;
	}
	
}
