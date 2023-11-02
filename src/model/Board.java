package model;

public class Board {
	
	// Main board object
	private Object[][] theBoard;
	private int size;

	public Board(int size) {
		this.size = size;
		theBoard = new Object[size][size];
	}

	/**
	 * returns the dimension of the square board
	 * 
	 * @return int size of dimension
	 */
	public int getSize() {
		return size;
	}
	
	public boolean newShotAttempt(int[] location) {
		
		
	}

	public boolean addShipToBoard(Ship ship, int[][] locations) {
		for (int[] location : locations) {
			int x = location[0];
			int y = location[1];
			if (theBoard[y][x] == null) {
				theBoard[y][x] = ship;
			} else {
				return false;
			}
		}
		return true;
	}

}
