package clueGame;

public class Card{
	public enum cardType { PERSON, WEAPON, ROOM };
	private cardType type;
	private String name;
	
	public Card() {
		type = cardType.PERSON;
		name = null;
	}
	
	public Card(cardType type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public boolean equals(Object obj) {
		return true;
	}
	
	public cardType getType() {
		return type;
	}
	
	public String getName() {
		return null;
	}
	
	public void setType(cardType newType) {
		type = newType;
	}
	
	public void setName(String name) {
		this.name = name;
	}


}
