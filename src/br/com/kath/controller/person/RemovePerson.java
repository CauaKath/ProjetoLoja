package br.com.kath.controller.person;

import java.util.List;
import java.util.Scanner;

import br.com.kath.model.PersonModel;

public class RemovePerson {
	
	private Scanner input = new Scanner(System.in);

	public PersonModel removePerson(List<PersonModel> people) {
		String name;
		int id;
		Login login = new Login();
		
		System.out.println("\n--- REMOÇÃO ---\n");
		System.out.println("Informe o nome da pessoa a ser removida: ");
		name = input.next();
		
		id = login.validateLogin(people, name);
		
		if (id == -1) {
			System.out.println("\nPessoa não existe no sistema!");
			return null;
		}
		
		return people.get(id);
	}
	
}
