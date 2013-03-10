package clueGame;

public abstract class BoardCell {
	
	Integer row, column;
	
	public BoardCell(Integer row, Integer column){
		this.row = row;
		this.column = column;
	}
	
	public boolean isWalkway(){
		return false;
	}
	
	public boolean isRoom(){
		return false;
	}
	
	public boolean isDoorway(){
		return false;
	}
	
	public Integer getRow() {
		return row;
	}

	public Integer getColumn() {
		return column;
	}
	
	
	public abstract void draw();

}
