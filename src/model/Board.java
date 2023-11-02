package model;

public class Board {

	// Main board object
	private Object[][] theBoard;
	private int size;
	private int totalHealthOfShips;

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

	/*
	 * Enum types for shots.
	 */
	public enum ShotResult {
		HIT, MISS, SUNK, REPEAT_ATTEMPT, GAME_WON;
	}

	public ShotResult shotAttempt(int x, int y) {
		// Handles a proper hit
		if (theBoard[y][x] instanceof Ship) {
			((Ship) theBoard[y][x]).damage();
			if (((Ship) theBoard[y][x]).getHealth() == 0) {
				// The ship is now fully sunk so return SUNK
				// as the ShotResult Type
				theBoard[y][x] = ShotResult.HIT;
				return ShotResult.SUNK;
			}
			// The ship did not sink so this is just a hit.
			theBoard[y][x] = ShotResult.HIT;
			return ShotResult.HIT;
		}

		// Handles a miss
		if (theBoard[y][x] == null) {
			theBoard[y][x] = ShotResult.MISS;
			return ShotResult.MISS;
		}

		// The spot is neither null or a ship at this point so
		// it has to be either a HIT or a MISS
		return ShotResult.REPEAT_ATTEMPT;
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
