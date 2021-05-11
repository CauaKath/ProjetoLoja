package br.com.kath.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.kath.controller.person.Login;
import br.com.kath.model.PersonModel;
import br.com.kath.model.ProdutoModel;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		List<ProdutoModel> products = new ArrayList<ProdutoModel>();
		List<PersonModel> people = new ArrayList<PersonModel>();
		
		boolean getOut = false;
		
		do {
			Login login = new Login();
			AdmMenu admMenu = new AdmMenu();
			ManagerMenu managerMenu = new ManagerMenu();
			ClientMenu clientMenu = new ClientMenu();
			
			System.out.println("\n--- ENTRADA ---\n");
			System.out.println("1) Adm");
			System.out.println("2) Gestor de estoque");
			System.out.println("3) Cliente");
			System.out.println("4) Sair");
			int option = input.nextInt();
			
			switch (option) {
			case 1:
				admMenu.admMenu(people);
				break;
				
			case 2:
				managerMenu.managerMenu(products);
				break;
				
			case 3:
				var user = login.login(people);
				
				if (user == null) {
					break;
				}
				
				clientMenu.clientMenu(user, products);
				break;
				
			case 4:
				System.out.println("\nSistema encerrado!!!");
				getOut = true;
				break;

			default:
				System.out.println("\nOpção inválida!!!");
				break;
			}
		} while (getOut != true);
		
		input.close();

	}

}
