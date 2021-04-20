package br.com.kath.model;

public class ProdutoModel {

	// Attributes
	private String productName;
	private double productPrice;
	private int productQuantity;
	private double storageBalance;
	
	// Constructor
	public ProdutoModel() {
	}
	
	public ProdutoModel(String productName, double productPrice, int productQuantity, double storageBalance) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.storageBalance = storageBalance;
	}

	// Accessor's and Modifier's
	public double getStorageBalance() {
		return storageBalance;
	}

	public void setStorageBalance(double storageBalance) {
		this.storageBalance = storageBalance;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	
}
