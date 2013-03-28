package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Player {
	protected String name;
	protected ArrayList<Card> cards;
	protected Color color;
	protected int location;
	protected Suggestion suggestion;
	
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
	
	public Player(Player other) {
		this.name = other.name;
		this.location = other.location;
		this.color = other.color;
		this.cards = new ArrayList<Card>(other.cards);
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public String disproveSuggestion(Card person, Card room, Card weapon) {
		Random rand = new Random();
		int r = 0;
		ArrayList<Card> temp = new ArrayList<Card>();

		// If the player has any of the cards, add them to a temporary list.
		if ( cards.contains(person) )
			temp.add(person);
		if ( cards.contains(room) )
			temp.add(room);
		if ( cards.contains(weapon) )
			temp.add(weapon);
		
		// If the player has any of the cards, return one of them (randomly pick)
		if ( temp.size() > 0) {	
			r = rand.nextInt(temp.size());
			return temp.get(r).getName();
		}
		// Else don't return any cards
		
		return null;
	}
	
	public String disproveSuggestion(Suggestion suggestion) {
		Card person = new Card(Card.cardType.PERSON, suggestion.getPerson());
		Card room = new Card(Card.cardType.ROOM, suggestion.getRoom());
		Card weapon = new Card(Card.cardType.WEAPON, suggestion.getWeapon());
		return disproveSuggestion(person, room, weapon);
	}
	
	@Override
	public boolean equals(Object o) {
		Player p = (Player) o;
		if ( name.equals(p.name) )
			return true;
		return false;
	}
	
	public void createSuggestion() {}

	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}
	
	//Only for testing.
	public void clearCards() {
		cards = new ArrayList<Card>();
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public Suggestion getSuggestion() {
		return suggestion;
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
