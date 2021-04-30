package br.com.kath.model;

public class PersonModel {

	private String nome;
	private CarModel car = new CarModel();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CarModel getCar() {
		return car;
	}

	public void setCar(CarModel car) {
		this.car = car;
	}
	
}
