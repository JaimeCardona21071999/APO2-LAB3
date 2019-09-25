package model;

public class TechniqueSameName extends RuntimeException{
	String text;
	public TechniqueSameName() {
		super();
		text = "There cannot be two technique with the same name";
	}
	public String getText() {
		return text;
	}
}
