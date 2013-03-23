package clueGame;

public class Suggestion {
	private String room, weapon, person;

	public Suggestion() {
		room = "";
		weapon = "";
		person = "";
	}
	
	public Suggestion(String room, String weapon, String person) {
		this.room = room;
		this.weapon = weapon;
		this.person = person;
	}
	
	public String getRoom() {
		return room;
	}
	
	public String getWeapon() {
		return weapon;
	}
	
	public String getPerson() {
		return person;
	}
}
