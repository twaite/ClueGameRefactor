package clueGame;

import java.util.Set;

public class ComputerPlayer extends Player {
	private char lastRoomVisited;
	
	public ComputerPlayer() {
		super();
	}
	
	public ComputerPlayer(String name) {
		super(name);
	}
	
	public void pickLocation(Set<BoardCell> targets) {
		
	}
	
	public void createSuggestion() {
		
	}
	
	public void updateSeen(Card seen) {
		
	}
	
	public char getLastRoom() {
		return lastRoomVisited;
	}
	
	public void setLastRoom(char last) {
		lastRoomVisited = last;
	}
}
