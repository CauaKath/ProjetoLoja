package br.com.kath.model;

import java.util.ArrayList;
import java.util.List;

public class CarModel {

	// Atributte's
	private double total;
	private String model;
	private List<ProdutoModel> carProducts = new ArrayList<ProdutoModel>();
	
	// Constructor's
	public CarModel() {
		this.total = 0;
	}
	
	public CarModel(String model) {
		this.model = model;
	}
	
	// Getter's and Setter's
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public List<ProdutoModel> getCarProducts() {
		return carProducts;
	}
	public void setCarProducts(List<ProdutoModel> carProducts) {
		this.carProducts = carProducts;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
}
