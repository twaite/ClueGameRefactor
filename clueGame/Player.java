package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
	public Color purple = new Color(255, 0, 255);
	private String name;
	private ArrayList<Card> cards;
	private Color color;
	private int location;
	
	public Player() {
		name = null;
		cards = new ArrayList<Card>();
	}
	
	public Player(String name) {
		this.name = name;
		cards = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		
	}
	
	public Card disproveSuggestion(String name, String room, String weapon) {
		return new Card();
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

}
