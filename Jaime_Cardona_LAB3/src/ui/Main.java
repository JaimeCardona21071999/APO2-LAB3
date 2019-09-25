package ui;

import java.util.*;

import model.*;

public class Main {
	private static Controller controller;

	public Main() {
		controller = new Controller();
		boolean yes = false;
		while(yes == false) {
			try {
				menu();
				yes = true;
			}catch(InputMismatchException e) {
				System.out.println("Make sure you are entering the data correctly");
			}
		}
	}

	static void menu() {
		int option;
		do {
			Scanner scn = new Scanner(System.in);
			System.out.println("1. Add Clan");
			System.out.println("2. Add Figure");
			System.out.println("3. Add Technique");
			System.out.println("4. Deleted Clan");
			System.out.println("5. Deleted Figure");
			System.out.println("6. Deleted Technique");
			System.out.println("7. Save changes");
			System.out.println("8. Read");
			System.out.println("9. Print menu");
			option = scn.nextInt();
			if (option == 1) {
				System.out.println("Enter the name of the clan");
				String nameClan;
				scn.nextLine();
				nameClan = scn.nextLine();
				System.out.println(controller.addClan(nameClan));
			}
			if (option == 2) {
				System.out.println("Enter the name of the clan to which you want to add the new figure");
				String nameClan;
				scn.nextLine();
				nameClan = scn.nextLine();
				System.out.println("Enter the name of the new figure");
				String nameFigure;
				nameFigure = scn.nextLine();
				System.out.println("Enter the personality of the new figure");
				String personality;
				personality = scn.nextLine();
				System.out.println("Enter the date of creation of the new figure");
				String dateCreation;
				dateCreation = scn.nextLine();
				System.out.println("Enter the power of the new figure");
				int power;
				power = scn.nextInt();
				System.out.println(controller.addFigure(nameClan, nameFigure, personality, dateCreation,power));
			}
			if (option == 3) {
				System.out.println(
						"Enter the name of the clan to which the figure to which you want to add the technique belongs");
				String nameClan;
				scn.nextLine();
				nameClan = scn.nextLine();
				System.out.println("Enter the name of the figure to which you want to add the technique");
				String nameFigure;
				nameFigure = scn.nextLine();
				System.out.println("Enter the name of the new technique");
				String name;
				name = scn.nextLine();
				System.out.println("Enter the factor of the new technique");
				double factor;
				factor = scn.nextDouble();
				System.out.println(controller.addTechnique(nameClan, nameFigure, name, factor));
			}
			if (option == 4) {
				System.out.println("Enter the name of the clan what do you want to remove");
				String nameClan;
				scn.nextLine();
				nameClan = scn.nextLine();
				System.out.println(controller.deletedClan(nameClan));
			}
			if (option == 5) {
				System.out.println("Enter the name of the clan to which you want to delete a figure");
				String nameClan;
				scn.nextLine();
				nameClan = scn.nextLine();
				System.out.println("Enter the name of the figure you want to delete");
				String nameFigure;
				nameFigure = scn.nextLine();
				System.out.println(controller.deletedFigure(nameClan, nameFigure));
			}
			if (option == 6) {
				System.out.println(
						"Enter the name of the clan to which the figure to which you want to delete the technique belongs");
				String nameClan;
				scn.nextLine();
				nameClan = scn.nextLine();
				System.out.println("Enter the name of the figure to which you want to delete the technique");
				String nameFigure;
				nameFigure = scn.nextLine();
				System.out.println("Ente the name of the technique what do you want to delete");
				String nameTechnique;
				nameTechnique = scn.nextLine();
				System.out.println(controller.deletedTechnique(nameClan, nameFigure, nameTechnique));
			}
			if (option == 7) {
				controller.serializableWorld();
				System.out.println("Changes saved");
			}
			if (option == 8) {
				controller.readWorld();
				System.out.println("");
			}
			if (option == 9) {
				menuPrint();
				System.exit(0);
			}

		} while (option <= 9);

	}

	static void menuPrint() {
		int option;
		do {
		Scanner scn2 = new Scanner(System.in);
		System.out.println("1. Show on screen the name of all clans");
		System.out.println("2. Show on screen the name and power of the figures of a particular clan");
		System.out.println("3. Show on screen the techniques of a specific figure");
		System.out.println("4. Exit to principal menu");
			option = scn2.nextInt();
			if (option == 1) {
				System.out.println(controller.printClan());
			}
			if (option == 2) {
				System.out.println("Enter the name of the clan to which you will print your figures on the screen");
				String nameClan;
				scn2.nextLine();
				nameClan = scn2.nextLine();
				System.out.println(controller.printFigures(nameClan));
			}
			if (option == 3) {
				System.out.println(
						"Enter the name of the clan to which the figure you wish to print your techniques belongs to");
				String nameClan;
				scn2.nextLine();
				nameClan = scn2.nextLine();
				System.out.println("Enter the name of the figure to which you want to print your screen techniques");
				String nameFigure;
				nameFigure = scn2.nextLine();
				System.out.println(controller.printTechniques(nameClan, nameFigure));
			}
			if (option == 4) {
				menu();
			}
		} while (option <= 4);
	}

	public static void main(String[] args) {
		Main main = new Main();

	}
}
