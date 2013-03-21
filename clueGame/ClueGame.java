package clueGame;

import java.util.ArrayList;

public class ClueGame {
	private Solution solution;
	private ArrayList<Card> cards;
	private ArrayList<Player> players;
	private int whoseTurn;

	public void deal() {
		
	}
	
	public void loadConfigFiles() {
		
	}
	
	public void selectAnswer() {
		
	}
	
	public void handleSuggestion(String person, String room, String weapon, Player accusingPerson) {
		
	}
	public boolean checkAccusation(Solution solution) {
		return false;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}
}
