package clueGame;

import java.util.Set;
import clueGame.BoardCell;

public class ComputerPlayer extends Player {
	private char lastRoomVisited;
	public Suggestion suggestion;
	
	public ComputerPlayer() {
		super();
	}
	
	public ComputerPlayer(String name, int location) {
		super(name, location);
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		return new BoardCell();
	}
	
	public void createSuggestion(String room) {
		
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
