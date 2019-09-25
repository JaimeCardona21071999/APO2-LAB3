package model;

import java.io.Serializable;
import java.util.Comparator;

public class Clan implements Serializable, Comparable<Clan>, Comparator<Clan> {
	private String name;
	private Figure figure1;
	private Clan next;

	public Clan(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Figure getFigure1() {
		return figure1;
	}

	public void setFigure1(Figure figure1) {
		this.figure1 = figure1;
	}

	public Clan getNext() {
		return next;
	}

	public void setNext(Clan next) {
		this.next = next;
	}

	public String addFigure(String name, String personality, String creationDate, int power) throws FigureSameName {
		String text = "The figure could not be added";
		if(searchFigure(name) == null) {
			Figure next = figure1;
			Figure newFigure = new Figure(name, personality, creationDate, power);
			boolean add = false;
			if (figure1 == null) {
				figure1 = newFigure;
				text = "The figure was created successfully";
				sizeOfFigure();
			} else {
				while (next != null && add == false) {
					if (next.getNext() == null) {
						next.setNext(newFigure);
						newFigure.setPrev(next);
						text = "The figure was created successfully";
						sizeOfFigure();
						add = true;
					} else {
						next = next.getNext();
					}
				}
			}
		}else {
			throw new FigureSameName();
		}
		
		return text;
	}

	public String deletedFigure(String name) {
		String text = "The figure could not be deleted";
		Figure next = figure1;
		boolean deleted = false;
		if (empty() == true) {
			text = "The figure could not be deleted";
		} else if (figure1.getName().equals(name)) {
			if (figure1.getNext() == null) {
				figure1 = null;
				text = "Successfully deleted";
			} else {
				figure1.getNext().setPrev(null);
				figure1 = figure1.getNext();
				text = "Successfully deleted";
			}
		} else {
			while (next != null && deleted == false) {
				if (next.getNext() != null && next.getNext().getName().equals(name)) {
					next.setNext(next.getNext().getNext());
					if (next.getNext() != null) {
						next.getNext().setPrev(next);
						text = "The Figure was deleted successfully";
						deleted = true;
					} else {
						text = "The Figure was deleted successfully";
						deleted = true;
					}
				} else {
					next = next.getNext();
				}

			}
		}
		return text;
	}

	public Figure searchFigure(String name) {
		Figure foundFigure = null;
		Figure next = figure1;
		boolean found = false;
		while (next != null && found == false) {
			if (next.getName().equals(name)) {
				foundFigure = next;
				found = true;
			} else {
				next = next.getNext();
			}
		}
		return foundFigure;
	}

	public int sizeOfFigure() {
		int counter = 0;
		counter++;
		return counter;
	}

	public void bubbleFigureName() {
		Figure aux;
		Figure aux1;
		Figure aux2;
		aux2 = figure1;
		while(aux2 != null) {
			aux1 = aux2.getNext();
		while(aux1 != null){
			if(aux2.compareTo(aux1)<0) {
				aux = aux2;
				aux2 = aux1;
				aux1 = aux;
			}
			aux1 = aux1.getNext();
		}
	}
	}

	public boolean empty() {
		boolean resp = false;
		if (figure1 == null) {
			resp = true;
		}
		return resp;
	}

	@Override
	public String toString() {
		return name;
	}

	public String printFigure() {
		String text = "";
		Figure sig = figure1;
		if (empty() == true) {
			text = "In this clan there are no figures";
		} else {
			while (sig != null) {
				text += sig.toString() + "\n";
				sig = sig.getNext();
			}
		}
		return text;
	}

	@Override
	public int compareTo(Clan o) {
		return this.name.compareToIgnoreCase(o.getName());
	}

	@Override
	public int compare(Clan c1, Clan c2) {
		if(c1.equals(c2)) {
			return 0;
		} else if(c2 == null || c1 == null) {
			return -1;
		}else {
			return c1.compareTo(c2);
		}
}
}
