package br.com.kath.view;

import java.util.List;
import java.util.Scanner;

import br.com.kath.controller.person.PersonMenu;
import br.com.kath.controller.person.RegisterPerson;
import br.com.kath.controller.person.RemovePerson;
import br.com.kath.model.PersonModel;

public class AdmMenu {
	
	private Scanner input = new Scanner(System.in);

	public void admMenu(List<PersonModel> people) {
		PersonMenu personMenu = new PersonMenu();
		RegisterPerson registerPerson = new RegisterPerson();
		RemovePerson removePerson = new RemovePerson();
		
		personMenu.menu();
		int option = option();
		
		switch (option) {
		case 1:
			people.add(registerPerson.register(people));
			this.admMenu(people);
			break;
			
		case 2:
			people.remove(removePerson.removePerson(people));
			this.admMenu(people);
			break;
			
		case 3:
			break;
			
		default:
			System.out.println("\nOpção inválida!!!");
			this.admMenu(people);
			break;
		}
	}
	
	private int option() {
		System.out.print("> ");
		return input.nextInt();
	}
	
}
