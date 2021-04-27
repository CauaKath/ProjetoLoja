package br.com.kath.model;

import java.util.ArrayList;
import java.util.List;

public class CarModel {

	// Atributte's
	private double total;
	private List<ProdutoModel> carProducts = new ArrayList<ProdutoModel>();
	
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
	
	public CarModel() {
	}
	
}
