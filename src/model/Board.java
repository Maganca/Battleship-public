// Kade Dean

package model;

public class Board {
	// Main board object
	private Object[][] theBoard;
	private int size;
	private int totalBoardHealth;

	public enum ShotResult {
		HIT, MISS, SUNK, REPEAT_ATTEMPT, GAME_WON, INVALID;
	}

	public Board(int size) {
		this.size = size;
		theBoard = new Object[size][size];
		this.totalBoardHealth = 0;
	}

	/**
	 * Attempts a new shot on the board and returns one of the resulting
	 * ShotResult enum types
	 * 
	 * @param x
	 * @param y
	 * @return ShotResult enum
	 */
	public ShotResult shotAttempt(int x, int y) {
		// Checks to make sure shot attempt is valid
		if (x >= theBoard[0].length || y >= theBoard.length) {
			return ShotResult.REPEAT_ATTEMPT;
		}
		// Checks to see if we've hit a ship
		if (theBoard[y][x] instanceof Ship) {
			// Damages ship and reduces board health
			((Ship) theBoard[y][x]).damage();
			this.totalBoardHealth--;
			if (this.totalBoardHealth == 0) {
				return ShotResult.GAME_WON;
			}
			if (((Ship) theBoard[y][x]).getHealth() == 0) {
				// The ship is now fully sunk so return SUNK
				theBoard[y][x] = ShotResult.HIT;
				return ShotResult.SUNK;
			}
			// The ship did not sink so this is just a hit.
			theBoard[y][x] = ShotResult.HIT;
			return ShotResult.HIT;
		}

		// Handles a miss.
		if (theBoard[y][x] == null) {
			theBoard[y][x] = ShotResult.MISS;
			return ShotResult.MISS;
		}

		// The spot is neither null or a ship at this point so
		// it has to be either a HIT or a MISS, meaning that its a repeat
		return ShotResult.REPEAT_ATTEMPT;

	}

	// Chi thieu
	public boolean addShipToBoard(Ship ship) {
		for (int[] location : ship.locations) {
			int x = location[0];
			int y = location[1];
			if (x >= theBoard[0].length || y >= theBoard.length || theBoard[y][x] != null)
				return false;
		}
		for (int[] location : ship.locations) {
			int x = location[0];
			int y = location[1];
			this.totalBoardHealth++;
			theBoard[y][x] = ship;
		}
		return true;
	}

	
	public int getSize() {
		return size;
	}
	public Object[][] getBoard() {
		return theBoard;
	}
	public void clearBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				theBoard[i][j] = null;
			}
		}
		this.totalBoardHealth = 0;
	}

	public int[] calculateTotalHitsAndMisses(Board playerBoard) {
		int[] result = new int[2];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (playerBoard.theBoard[i][j] == ShotResult.HIT) {
					result[0]++;
				} else if (playerBoard.theBoard[i][j] == ShotResult.MISS) {
					result[1]++;
				}
			}
		}

		return result;
	}
}
