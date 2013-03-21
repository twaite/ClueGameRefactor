package clueGame;

import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Card> cards;
	
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
	
	public ArrayList<Card> getCards() {
		return cards;
	}

}
