package clueGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import clueGame.BoardCell;

public class ComputerPlayer extends Player {
	private char lastRoomVisited;
	private ArrayList<Card> seen;
	
	public ComputerPlayer() {
		super();
		seen = new ArrayList<Card>();
	}
	
	public ComputerPlayer(String name, int location) {
		super(name, location);
		seen = new ArrayList<Card>();
	}
	
	public ComputerPlayer(String name, int location, String color) {
		super(name, location, color);
		seen = new ArrayList<Card>();
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		Random rand = new Random();
		int r = 0;
		r = rand.nextInt(targets.size());
		
		for ( BoardCell bc : targets ) {
			if ( bc.isRoom() ) {
				RoomCell cell = (RoomCell) bc;
				if ( lastRoomVisited == cell.getInitial() )
					return bc;
			}
		}
		
		int i = 0;
		for ( BoardCell bc : targets ) {
			if ( i == r ) return bc;
			++i;
		}
		
		return null;
	}
	
	public void createSuggestion(String room, ArrayList<Card> cards) {
		Random rand = new Random();
		Card card;
		int r = 0;
		String person = "";
		String weapon = "";
		
		for( Card cardSeen : seen ) {
			if ( cards.contains(cardSeen) )
				cards.remove(cardSeen);
		}
		
		for ( Card c : cards) System.out.println(c);
		
		while ( cards.size() > 0 ) {
			r = rand.nextInt(cards.size());
			card = cards.get(r);
			if ( card.getType() == Card.cardType.PERSON && person == "") {
				person = card.getName();
				System.out.println(person);
				cards.remove(rand);
			}
			else if ( card.getType() == Card.cardType.WEAPON && weapon == "") {
				weapon = card.getName();
				System.out.println(weapon);
				cards.remove(rand);
			}
			else {
				System.out.println(card);
			}
			if ( person != "" && weapon != "" )
				break;			
		}
		System.out.println("after while");
		
		suggestion = new Suggestion(person, room, weapon);
	}
	
	public void updateSeen(Card seen) {
		this.seen.add(seen);
	}
	
	public char getLastRoom() {
		return lastRoomVisited;
	}
	
	public void setLastRoom(char last) {
		lastRoomVisited = last;
	}
}
