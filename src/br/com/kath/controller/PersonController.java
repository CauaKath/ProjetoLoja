package br.com.kath.controller;

import java.util.List;
import java.util.Scanner;

import br.com.kath.model.PersonModel;

public class PersonController {

	private Scanner input;
	
	public PersonController() {
		input = new Scanner(System.in);
	}
	
	public int option() {
		System.out.print("> ");
		return input.nextInt();
	}
	
	public void menu() {
		System.out.println("\n--- MENU ---\n");
		System.out.println("1) Cadastrar pessoa");
		System.out.println("2) Remover pessoa");
		System.out.println("3) Voltar");
		System.out.println("--------------------");
	}
	
	public PersonModel register(List<PersonModel> people) {
		var personModel = new PersonModel();
		
		System.out.println("\n--- CADASTRO ---\n");
		System.out.println("Informe o seu nome: ");
		personModel.setNome(input.next());
		System.out.println("\nInforme o modelo do seu carrinho: ");
		personModel.getCar().setModel(input.next());
		
		return personModel;
	}
	
	public PersonModel removePerson(List<PersonModel> people) {
		String name;
		int id;
		
		System.out.println("\n--- REMOÇÃO ---\n");
		System.out.println("Informe o nome da pessoa a ser removida: ");
		name = input.next();
		
		id = this.validateLogin(people, name);
		
		if (id == -1) {
			System.out.println("\nPessoa não existe no sistema!");
			return null;
		}
		
		return people.get(id);
	}
	
	public PersonModel login(List<PersonModel> people) {
		String name;
		int id;
		
		System.out.println("\n--- CADASTRO ---\n");
		System.out.println("Informe o seu nome: ");
		name = input.next();
		
		id = this.validateLogin(people, name);
		
		if (id == -1) {
			System.out.println("\nLogin inválido!");
			return null;
		} else {
			System.out.println("\nBem vindo a loja, " + people.get(id).getNome());
			return people.get(id);
		}
	}
	
	private int validateLogin(List<PersonModel> people, String name) {
		for (int i = 0; i < people.size(); i++) {
			if (people.get(i).getNome().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
}
