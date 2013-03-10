package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

import clueGame.RoomCell.DoorDirection;

public class Board {
	
	//All instance variables needed for the board class.
	private ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	private Map<Integer, LinkedList<Integer>> adjMtx;
	private Set<BoardCell> targets;
	private boolean[] visited;
	private int numRows;
	private int numColumns;
	String configFileName;
	String legendFileName;
	
	//Initializes board with default file names.
	public Board() {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		adjMtx = new HashMap<Integer, LinkedList<Integer>>();
		targets = new HashSet<BoardCell>();
		configFileName = "ClueConfig.csv";
	}
	
	
	//Initializes board given filenames for both the legend and configuration files.
	public Board(String configFileName, String legendFileName) {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		adjMtx = new HashMap<Integer, LinkedList<Integer>>();
		targets = new HashSet<BoardCell>();
		this.configFileName = configFileName;
		this.legendFileName = legendFileName;
	}


	//Loads the configuration of the board by calling helper functions.
	public void loadConfigFiles() throws BadConfigFormatException{
		loadRoomConfig();
		loadBoardConfig();
		calcAdjacencies();
	}
	
	/* Loads the names of the rooms and their matching characters. This is done by reading 
	 * from the configuration file with a format such as: L, Library . It then takes this 
	 * line and splits it into an array that is stored like this: {L, Library} so that each 
	 * element can be accessed separately and stored into the rooms map for use in setting 
	 * up the board later.
	 */
	public void loadRoomConfig() throws BadConfigFormatException{
		try {
			FileReader reader = new FileReader(legendFileName);
			Scanner input = new Scanner(reader);
			String line;
			String[] sepByComma;
			while(input.hasNext()){
				line = input.nextLine();
				sepByComma = line.split(", ");
				if(sepByComma.length > 2)
					throw new BadConfigFormatException(this);
				
				rooms.put(sepByComma[0].charAt(0), sepByComma[1]);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void loadBoardConfig() throws BadConfigFormatException{
		
		try {
			FileReader reader = new FileReader(configFileName);
			Scanner input = new Scanner(reader);
			String line;
			String[] sepByComma = {" "};
			int rowCounter = 0;
			boolean badRoomFlag = true;
			numColumns = 0;
			while(input.hasNext()){
				rowCounter ++;
				line = input.nextLine();
				sepByComma = line.split(",");
				
				if (numColumns == 0)
					numColumns = sepByComma.length;
				
				if (numColumns != sepByComma.length)
					throw new BadConfigFormatException(this);
				
				numColumns = sepByComma.length;
				
				for(int i = 0; i < sepByComma.length; i++){
					badRoomFlag = true;
					for(Character key : rooms.keySet()){
						if(key == sepByComma[i].charAt(0)){
							badRoomFlag = false;
							break;
						}
					}
					if(badRoomFlag)
						throw new BadConfigFormatException(this);
					
					if (sepByComma[i].length() > 2)
						throw new BadConfigFormatException(this);
					
					
					if(sepByComma[i].charAt(0) == 'W'){
						cells.add(new WalkwayCell(rowCounter, i+1));
						continue;
					}
					
					if(sepByComma[i].length() > 1){
						
						if(sepByComma[i].charAt(1) == 'U')
							cells.add(new RoomCell(rowCounter, i+1, sepByComma[i].charAt(0), DoorDirection.UP));
						else if(sepByComma[i].charAt(1) == 'D')
							cells.add(new RoomCell(rowCounter, i+1, sepByComma[i].charAt(0), DoorDirection.DOWN));
						else if(sepByComma[i].charAt(1) == 'L')
							cells.add(new RoomCell(rowCounter, i+1, sepByComma[i].charAt(0), DoorDirection.LEFT));
						else if(sepByComma[i].charAt(1) == 'R')
							cells.add(new RoomCell(rowCounter, i+1, sepByComma[i].charAt(0), DoorDirection.RIGHT));
						else
							cells.add(new RoomCell(rowCounter, i+1, sepByComma[i].charAt(0), DoorDirection.NONE));
						
					}
					
					if(sepByComma[i].length() == 1)
						cells.add(new RoomCell(rowCounter, i+1, sepByComma[i].charAt(0), DoorDirection.NONE));
					
					if(numColumns == sepByComma.length){
						
					}
				}
			}
			numRows = rowCounter;
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void calcAdjacencies() {
		LinkedList<Integer> adjList = new LinkedList<Integer>();
		int index;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				adjList = new LinkedList<Integer>();
				index = calcIndex(i,j);
				if (index > numColumns-1) {
					// Up
					if (!cells.get(index).isRoom()) {
						// Non-Room Cell
						if (cells.get(index - numColumns).isWalkway()) {	
							adjList.add(index - numColumns);
						} else if (cells.get(index - numColumns).isDoorway()) {
							// Cannot move from a door space to an adjacent door space
							 if (!(cells.get(index).isDoorway()) )				
								 // Make sure door is in correct direction
								 if (getRoomCellAt(index - numColumns).getDoorDirection() == DoorDirection.DOWN)
									 adjList.add(index - numColumns);
						}
					} else {
						// Room Cell
						if (cells.get(index).isDoorway()) {
							// Make sure door is in correct direction
							if (getRoomCellAt(index).getDoorDirection() == DoorDirection.UP)
								adjList.add(index - numColumns);
						}
					}
				}
				if (index < (numColumns*numRows) - numColumns) {
					// Down
					if (!cells.get(index).isRoom()) {
						// Non-Room Cell
						if (cells.get(index + numColumns).isWalkway()) {
							adjList.add(index + numColumns);
						} else if (cells.get(index + numColumns).isDoorway()) {
							// Cannot move from a door space to an adjacent door space
							 if (!(cells.get(index).isDoorway()) )				
								 // Make sure door is in correct direction
								 if (getRoomCellAt(index + numColumns).getDoorDirection() == DoorDirection.UP)
									 adjList.add(index + numColumns);
						}
					} else {
						// Room Cell
						if (cells.get(index).isDoorway()) {
							// Make sure door is in correct direction
							if (getRoomCellAt(index).getDoorDirection() == DoorDirection.DOWN)
								adjList.add(index + numColumns);
						}
					}
				}
				if ((index % numColumns) != 0) {
					// Left
					if (!cells.get(index).isRoom()) {
						// Non-Room Cell
						if (cells.get(index - 1).isWalkway()) {
							adjList.add(index - 1);
						} else if (cells.get(index - 1).isDoorway()) {
							// Cannot move from a door space to an adjacent door space
							 if (!(cells.get(index).isDoorway()) )				
								 // Make sure door is in correct direction
								 if (getRoomCellAt(index - 1).getDoorDirection() == DoorDirection.RIGHT)
									 adjList.add(index - 1);
						}
					} else {
						// Room Cell
						if (cells.get(index).isDoorway()) {
							// Make sure door is in correct direction
							if (getRoomCellAt(index).getDoorDirection() == DoorDirection.LEFT)
								adjList.add(index - 1);
						}
					}
				}
				if ((index % numColumns) != (numColumns - 1)) {
					// Right
					if (!cells.get(index).isRoom()) {
						// Non-Room Cell
						if (cells.get(index + 1).isWalkway()) {
							adjList.add(index + 1);
						} else if (cells.get(index + 1).isDoorway()) {
							// Cannot move from a door space to an adjacent door space
							 if (!(cells.get(index).isDoorway()) )				
								 // Make sure door is in correct direction
								 if (getRoomCellAt(index + 1).getDoorDirection() == DoorDirection.LEFT)
									 adjList.add(index + 1);
						}
					} else {
						// Room Cell
						if (cells.get(index).isDoorway()) {
							// Make sure door is in correct direction
							if (getRoomCellAt(index).getDoorDirection() == DoorDirection.RIGHT)
								adjList.add(index + 1);
						}
					}
				}
				adjMtx.put(index, adjList);
			}
		}
	}
	
	public void calcTargets(int space, int num) {
		
		
		visited[space] = true;
		LinkedList<Integer> adjacentCells = new LinkedList<Integer>();
		for(Integer g : adjMtx.get(space)){
			Integer a = g;
			adjacentCells.add(a);
		}
		LinkedList<Integer> toRemove = new LinkedList<Integer>();
		for (Integer i : adjacentCells) {
			if (visited[i] == true) {
				toRemove.add(i);
			}
		}
		adjacentCells.removeAll(toRemove);
		
		for (Integer i : adjacentCells) {
			if(cells.get(i).isDoorway()){
                targets.add(cells.get(i));
                continue;
			}
			if (num == 1) {
				targets.add(cells.get(i));
			} else {
				calcTargets(i, (num-1));
				visited[i] = false;
			}
			
		}
	}
	
	// calcTargets to match CR test arguments
	public void calcTargets(int row, int col, int num){
		visited = new boolean[numRows*numColumns];
		Arrays.fill(visited, false);
		targets = new HashSet<BoardCell>();
		calcTargets(calcIndex(row, col), num);
	}
	
	public int calcIndex(int rows, int columns){
		return ((rows * numColumns) + columns);
	}
	
	public BoardCell getCellAt(int index){
		return cells.get(index);
	}
	
	public RoomCell getRoomCellAt(int row, int column){
		return ((RoomCell)cells.get(calcIndex(row, column)));
	}
	
	public RoomCell getRoomCellAt(int space){
		return ((RoomCell)cells.get(space));
	}

	public ArrayList<BoardCell> getCells() {
		return cells;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}
	
	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	public LinkedList<Integer> getAdjList(int index) {
		return adjMtx.get(index);
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}
	
	public String getFilename(){
		return configFileName;
	}
	
	public String getLegend(){
		return legendFileName;
	}
	
}
