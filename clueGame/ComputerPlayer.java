package clueGame;

import java.util.ArrayList;
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
		return new BoardCell();
	}
	
	public void createSuggestion(String room) {
		suggestion = new Suggestion();
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
