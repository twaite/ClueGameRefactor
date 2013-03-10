package clueGame;

public class WalkwayCell extends BoardCell {
	
	public WalkwayCell(Integer row, Integer column){
		super(row, column);
	}

	@Override
	public boolean isWalkway(){
		return true;
	}

	@Override
	public void draw() {
		
	}
	

}
