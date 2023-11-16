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
	public void clearBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                theBoard[i][j] = null;
            }
        }
        totalBoardHealth = 0;
    }

    /**
     * Checks if a ship can be placed at the given location.
     *
     * @param ship      The ship to be placed.
     * @param locations The locations where the ship is to be placed.
     * @return true if the ship can be placed, false otherwise.
     */
    public boolean canPlaceShip(Ship ship, int[][] locations) {
        for (int[] location : locations) {
            int x = location[0];
            int y = location[1];
            if (x < 0 || x >= size || y < 0 || y >= size || theBoard[y][x] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the status of the specified cell on the board.
     *
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     * @return ShotResult enum representing the status of the cell.
     */
    public ShotResult getCellStatus(int x, int y) {
        // basic error checking
        if ((x > size - 1 || x < 0) || (y > size - 1 || y < 0)) {
            return ShotResult.INVALID;
        }

        if (theBoard[y][x] instanceof Ship) {
            return ShotResult.HIT;
        } else if (theBoard[y][x] == ShotResult.MISS) {
            return ShotResult.MISS;
        } else {
            return ShotResult.REPEAT_ATTEMPT;
        }
    }
}

