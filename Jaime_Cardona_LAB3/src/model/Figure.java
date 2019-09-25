package model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.InputMismatchException;

public class Figure implements Comparable<Figure>, Serializable,Comparator<Figure> {
	private String name;
	private String personality;
	private String creationDate;
	private int power;
	private Technique technique1;
	private Figure next;
	private Figure prev;

	public Figure(String name, String personality, String creationDate, int power) {
		this.name = name;
		this.personality = personality;
		this.creationDate = creationDate;
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonality() {
		return personality;
	}

	public void setPersonality(String personality) {
		this.personality = personality;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public Technique getTechnique1() {
		return technique1;
	}

	public void setTechnique1(Technique technique1) {
		this.technique1 = technique1;
	}

	public Figure getNext() {
		return next;
	}

	public void setNext(Figure next) {
		this.next = next;
	}

	public Figure getPrev() {
		return prev;
	}

	public void setPrev(Figure prev) {
		this.prev = prev;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	@Override
	public int compareTo(Figure o) {
		return this.name.compareToIgnoreCase(o.getName());
	}

	public String addTechnique(String name, double factor) throws TechniqueSameName {
		String text = "Could not add technique";
		if (searchTechnique(name) == null) {
			Technique newTechnique = new Technique(name, factor);
			Technique next = technique1;
			boolean add = false;
			if (technique1 == null) {
				technique1 = newTechnique;
				text = "The technique was added correctly";
			} else {
				while (next != null && add == false) {
					if (next.getNext() == null) {
						next.setNext(newTechnique);
						text = "The technique was added correctly";
						add = true;
					} else {
						next = next.getNext();
					}
				}
			}
		} else {
			throw new TechniqueSameName();
		}
		return text;
	}

	public String deletedTechnique(String n) {
		String text = "The technique could not be deleted";
		Technique next = technique1;
		boolean erase = false;
		if (empty() == true) {
			text = "The technique could not be deleted";
		} else if (technique1.getName().equals(n)) {
			technique1 = technique1.getNext();
			text = "The technique was deleted successfully";
		} else {
			while (next != null && erase == false) {
				if (next.getNext() != null && next.getNext().getName().equals(n)) {
					next.setNext(next.getNext().getNext());
					text = "The technique was deleted successfully";
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
		if (technique1 == null) {
			resul = true;
		}
		return resul;
	}

	public Technique searchTechnique(String name) {
		Technique foundTechnique = null;
		Technique next = technique1;
		boolean found = false;
		while (next != null && found == false) {
			if (next.getName().equals(name)) {
				foundTechnique = next;
				found = true;
			} else {
				next = next.getNext();
			}
		}
		return foundTechnique;
	}

	@Override
	public String toString() {
		return name + "," + personality + "\n";
	}

	public String printTechnique() {
		String text = "";
		Technique sig = technique1;
		if (empty() == true) {
			text = "This figure has no techniques";
		} else {
			while (sig != null) {
				text += sig.toString();
				sig = sig.getNext();
			}
		}
		return text;
	}

	@Override
	public int compare(Figure c1, Figure c2) {
		if(c1.equals(c2)) {
			return 0;
		} else if(c2 == null || c1 == null) {
			return -1;
		}else {
			return c1.compareTo(c2);
		}
	}

}
