package clueGame;

public class Solution {
	private String person;
	private String room;
	private String weapon;
	
	public Solution() {
		person = new String();
		room = new String();
		weapon = new String();
	}
	
	public Solution( String person, String room, String weapon ) {
		this.person = person;
		this.room = room;
		this.weapon = weapon;
	}
}