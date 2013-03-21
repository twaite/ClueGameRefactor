package clueGame;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
	private List cards;
	
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

	public String getName(String name) {
		return name;
	}
	
	public List getCards() {
		return cards;
	}

}
