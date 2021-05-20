package br.com.kath.view;

import java.util.Scanner;

import br.com.kath.controller.person.Login;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
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
			System.out.print("> ");
			int option = input.nextInt();
			
			switch (option) {
			case 1:
				admMenu.admMenu();
				break;
				
			case 2:
				managerMenu.managerMenu();
				break;
				
			case 3:
				var user = login.login();
				
				if (user == -1) {
					break;
				}
				
				clientMenu.clientMenu(user);
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
