package clueGame;

import java.awt.Color;
import java.util.ArrayList;

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
	
	public Player(String name, int location) {
		this.name = name;
		this.location = location;
		cards = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		
	}
	
	public String disproveSuggestion(Card name, Card room, Card weapon) {
		return null;
	}
	
	public String disproveSuggestion(Suggestion suggestion) {
		return null;
	}

	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return Color.red;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public void createSuggestion() {}
	
	public Suggestion getSuggestion() {
		return new Suggestion();
	}


}
