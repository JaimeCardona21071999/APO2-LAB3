package model;

public class FigureSameName extends RuntimeException {
	String text;
	public FigureSameName() {
		super();
		text = "There cannot be two figures with the same name";
	}
	public String getText() {
		return text;
	}
}

