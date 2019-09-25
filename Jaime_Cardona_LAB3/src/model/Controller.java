package model;

import java.io.*;
import java.util.InputMismatchException;

public class Controller {
	private Clan clan1;

	public Controller() {
		escene1();
	}
	
	public void escene1() {
		addClan ("Power Clan");
		addClan("The Super Dragon");
		addClan("The Fire Clan");
		addFigure("Power Clan","Naruto","Happy","21 de julio",34);
		addFigure("The Super Dragon","Naruto Super Sayayin","Angry","21 de julio",104);
		addTechnique("Power Clan","Naruto","Fire Technique",35.4);
	}

	public String addClan(String n) throws ClanSameName{
		String text = "Could not add clan";
		try {
			if(searchClan(n) == null ) {
				Clan newClan = new Clan(n);
				Clan next = clan1;
				boolean add = false;
				if (clan1 == null) {
					clan1 = newClan;
					text = "The clan was added correctly";
				} else {
					while (next != null && add == false) {
						if (next.getNext() == null) {
							next.setNext(newClan);
							text = "The clan was added correctly";
							add = true;
						} else {
							next = next.getNext();
						}
					}
				}
			}else {
				throw new ClanSameName();
			}
		}catch(InputMismatchException e) {
			text = "Make sure you are entering the data correctly";
		}catch(ClanSameName e) {
			text = e.getText();
		}
		return text;
	}

	public String deletedClan(String n) {
		String text = "The clan could not be deleted";
		Clan next = clan1;
		boolean erase = false;
		if (empty() == true) {
			text = "The clan could not be deleted";
		} else if (clan1.getName().equals(n)) {
			clan1 = clan1.getNext();
			text = "The clan was deleted successfully";
		} else {
			while (next != null && erase == false) {
				if (next.getNext() != null && next.getNext().getName().equals(n)) {
					next.setNext(next.getNext().getNext());
					text = "The clan was deleted successfully";
					erase = true;
				} else {
					next = next.getNext();
				}
			}
		}
		return text;
	}

	public boolean empty() {
		boolean resul = false;
		if (clan1 == null) {
			resul = true;
		}
		return resul;
	}

	public Clan searchClan(String name) {
		Clan foundClan = null;
		Clan next = clan1;
		boolean found = false;
		while (next != null && found == false) {
			if (next.getName().equals(name)) {
				foundClan = next;
				found = true;
			} else {
				next = next.getNext();
			}
		}
		return foundClan;
	}

	public String addFigure(String nameClan, String name, String personality, String creationDate, int power) {
		String text = "The clan to which you want to add the figure does not exist";
		try {
			if (searchClan(nameClan) != null) {
				text = searchClan(nameClan).addFigure(name, personality, creationDate, power);
			}
		}catch(InputMismatchException e) {
			text = "Make sure you are entering the data correctly";
		}catch(FigureSameName e) {
			text = e.getText();
		}
		return text;
	}

	public String deletedFigure(String nameClan, String name) {
		String text = "The clan to which you want to deleted the figure does not exist";
		if (searchClan(nameClan) != null) {
			text = searchClan(nameClan).deletedFigure(name);
		}
		return text;
	}

	public String addTechnique(String nameClan, String nameFigure, String name, double factor) {
		String text = "The figure to which you want to add the technique does not exist or you entered the clan name that was not";
		try {
			if (searchClan(nameClan) != null && searchClan(nameClan).searchFigure(nameFigure) != null) {
				text = searchClan(nameClan).searchFigure(nameFigure).addTechnique(name, factor);
			}
		}catch(InputMismatchException e) {
			text = "Make sure you are entering the data correctly";
		}catch(TechniqueSameName e){
			text = e.getText();
		}
		
		return text;
	}

	public String deletedTechnique(String nameClan, String nameFigure, String n) {
		String text = "The figure to which you want to deleted the technique does not exist or you entered the clan name that was not";
		if (searchClan(nameClan) != null && searchClan(nameClan).searchFigure(nameFigure) != null) {
			text = searchClan(nameClan).searchFigure(nameFigure).deletedTechnique(n);
		}
		return text;
	}

	public void serializableWorld() {
		try {
			FileOutputStream fs = new FileOutputStream(".\\" + "Serializable");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			Clan sig = clan1;
			while (sig != null) {
				os.writeObject(sig);
				sig = sig.getNext();
			}
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addDirect(Clan newClan) {
		Clan next = clan1;
		boolean add = false;
		if (clan1 == null) {
			clan1 = newClan;
		} else {
			while (next != null && add == false) {
				if (next.getNext() == null) {
					next.setNext(newClan);
					add = true;
				} else {
					next = next.getNext();
				}
			}
		}
	}

	public void readWorld() {
		try {

			File f = new File(".\\" + "Serializable");
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			addDirect((Clan) ois.readObject());
			ois.close();
			fis.close();

		} catch (IOException | ClassNotFoundException ioe) {
			ioe.printStackTrace();
		}
	}

	public String printClan() {
		
		String text = "";
		Clan sig = clan1;
		if (empty() == true) {
			text = "There are no clans";
		} else {
			while (sig != null) {
				text += sig.toString() + "\n";
				sig = sig.getNext();
			}
		}
		return text;
	}

	public String printFigures(String nameClan) {
		String text = "The clan to which you want to print your figures does not exist";
		if (searchClan(nameClan) != null) {
			text = searchClan(nameClan).printFigure();
		}
		return text;
	}

	public String printTechniques(String nameClan, String nameFigure) {
		String text = "The clan or the figures to which you want to print the techniques on screen does not exist";
		if (searchClan(nameClan) != null && searchClan(nameClan).searchFigure(nameFigure) != null) {
			text = searchClan(nameClan).searchFigure(nameFigure).printTechnique();
		}
		return text;
	}

	public int sizeOfFigure() {
		int counter = 0;
		Clan next = clan1;
		while (next != null) {
			counter++;
			next = next.getNext();
		}
		return counter;
	}

	public void insercionClanName() {
		int j;
		Clan temp = null;
		Clan temp2 = null;
		Clan sig = clan1;
		for (int i = 1; i < sizeOfFigure(); i++) {
			while (sig != null) {
				temp = sig;
				temp2 = getPrev(sig.getName());
				j = i - 1;
				while (temp2 != null && (j >= 0) && (temp.compareTo(temp2) < 0)) {
					temp2.getNext().setNext(temp2);
					j--;
				}
				sig = sig.getNext();
				if(temp2 != null && temp2.getNext() != null) {
					temp2.getNext().setNext(temp);
				}
				
			}

		}

	}

	public Clan getPrev(String cn) {
		Clan get = null;
		Clan sig = clan1;
		boolean found = false;
		while (sig != null && found == false) {
			if (sig.getNext() != null && sig.getNext().getName().equals(cn)) {
				get = sig;
				found = true;
			} else {
				sig = sig.getNext();
			}
		}

		return get;

	}

}
