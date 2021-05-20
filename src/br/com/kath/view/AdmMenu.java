package br.com.kath.view;

import java.util.Scanner;

import br.com.kath.controller.person.PersonMenu;
import br.com.kath.controller.person.RegisterPerson;
import br.com.kath.controller.person.RemovePerson;

public class AdmMenu {
	
	private Scanner input = new Scanner(System.in);

	public void admMenu() {
		PersonMenu personMenu = new PersonMenu();
		RegisterPerson registerPerson = new RegisterPerson();
		RemovePerson removePerson = new RemovePerson();
		
		personMenu.menu();
		int option = option();
		
		switch (option) {
		case 1:
			registerPerson.register();
			this.admMenu();
			break;
			
		case 2:
			removePerson.removePerson();
			this.admMenu();
			break;
			
		case 3:
			break;
			
		default:
			System.out.println("\nOpção inválida!!!");
			this.admMenu();
			break;
		}
	}
	
	private int option() {
		System.out.print("> ");
		return input.nextInt();
	}
	
}
