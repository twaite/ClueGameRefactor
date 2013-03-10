package clueGame;

public class RoomCell extends BoardCell {

	public enum DoorDirection{UP, DOWN, LEFT, RIGHT, NONE}
	
	DoorDirection doorDirection;
	private char initial;
	
	public RoomCell(Integer row, Integer column, char initial, DoorDirection doorDirection){
		super(row, column);
		this.initial = initial;
		this.doorDirection = doorDirection;
	}
	
	@Override
	public boolean isRoom(){
		return true;
	}
	
	@Override
	public boolean isDoorway(){
		if(doorDirection != DoorDirection.NONE){
			return true;
		}
		else return false;
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

	public char getInitial() {
		return initial;
	}

	@Override
	public void draw() {

	}

}
