package model;

public class ClanSameName extends RuntimeException {
	String text;
	public ClanSameName() {
		super();
		text = "There cannot be two clans with the same name";
	}
	public String getText() {
		return text;
	}
}
