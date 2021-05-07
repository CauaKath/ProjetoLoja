package br.com.kath.controller.person;

import java.util.List;
import java.util.Scanner;

import br.com.kath.model.PersonModel;

public class Login {

	private Scanner input = new Scanner(System.in);
	
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
	
	public int validateLogin(List<PersonModel> people, String name) {
		for (int i = 0; i < people.size(); i++) {
			if (people.get(i).getNome().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
}
