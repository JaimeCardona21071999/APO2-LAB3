package model;

import java.io.Serializable;
import java.util.Comparator;

public class Technique implements Serializable,Comparable<Technique>,Comparator<Technique> {
	private String name;
	private double factor;
	private Technique next;

	public Technique(String name, double factor) {
		this.name = name;
		this.factor = factor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}

	public Technique getNext() {
		return next;
	}

	public void setNext(Technique next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return name  + factor ;
	}

	@Override
	public int compare(Technique c1, Technique c2) {
		if(c1.equals(c2)) {
			return 0;
		} else if(c2 == null || c1 == null) {
			return -1;
		}else {
			return c1.compareTo(c2);
		}
	}

	@Override
	public int compareTo(Technique o) {
		return this.name.compareToIgnoreCase(o.getName());
	}

}
