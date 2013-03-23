package clueGame;

import java.awt.Color;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.classfile.Field;

public class Player {
	private String name;
	private ArrayList<Card> cards;
	private Color color;
	private int location;
	private Suggestion suggestion;
	
	public Player() {
		name = null;
		cards = new ArrayList<Card>();
	}

	public Player(String name, int location ) {
		this.name = name;
		this.location = location;
		cards = new ArrayList<Card>();
	}
	
	public Player(String name, int location, String color) {
		this.name = name;
		this.location = location;
		cards = new ArrayList<Card>();
		this.color = convertColor(color);
	}
	
	public void addCard(Card card) {
		
	}
	
	public String disproveSuggestion(Card name, Card room, Card weapon) {
		return null;
	}
	
	public String disproveSuggestion(Suggestion suggestion) {
		return null;
	}
	
	public void createSuggestion() {}

	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public Suggestion getSuggestion() {
		return new Suggestion();
	}
	
	public Color convertColor(String strColor) {
		Color color; 
		try {     
			// We can use reflection to convert the string to a color
			java.lang.reflect.Field field = Class.forName("java.awt.Color").getField(strColor.trim());     
			color = (Color)field.get(null); } 
		catch (Exception e) {  
			color = null; // Not defined } 
		}
		return color;
	}


}
