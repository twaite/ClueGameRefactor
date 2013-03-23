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

	public boolean checkAccusation() {
		return true;
	}

	public boolean checkAccusation(Solution solution) {
		return false;
	}

	public ArrayList<Player> getPlayers() {
		return new ArrayList<Player>();
	}

	public ArrayList<Card> getCards() {
		return new ArrayList<Card>();
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	
	public void setWhoseTurn(String name) {
		
	}
	
	public Card handleSuggestion(Player player) {
		return new Card();
	}
	
}
