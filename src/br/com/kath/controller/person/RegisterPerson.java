package br.com.kath.controller.person;

import java.util.List;
import java.util.Scanner;

import br.com.kath.model.PersonModel;

public class RegisterPerson {

	private Scanner input = new Scanner(System.in);
	
	public PersonModel register(List<PersonModel> people) {
		var personModel = new PersonModel();
		
		System.out.println("\n--- CADASTRO ---\n");
		System.out.println("Informe o seu nome: ");
		personModel.setNome(input.next());
		System.out.println("\nInforme o modelo do seu carrinho: ");
		personModel.getCar().setModel(input.next());
		
		return personModel;
	}
	
}
