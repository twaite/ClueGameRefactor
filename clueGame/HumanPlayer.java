package clueGame;

public class HumanPlayer extends Player {
	
	public HumanPlayer() {
		super();
	}
	
	public HumanPlayer(String name, int location) {
		super(name, location);
	}
	
	public HumanPlayer(String name, int location, String color) {
		super(name, location, color);
	}
	
	public void createSuggestion(String person, String room, String weapon) {
		suggestion = new Suggestion(person, room, weapon);
	}
	
}
