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
		totalBoardHealth = 0;
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
			return ShotResult.INVALID;
		}

		// Checks to see if we've hit a ship
		if (theBoard[y][x] instanceof Ship) {
			// Damages ship and reduces board health
			((Ship) theBoard[y][x]).damage();
			totalBoardHealth--;
			theBoard[y][x] = ShotResult.HIT;
			if (totalBoardHealth == 0) {
				return ShotResult.GAME_WON;
			}
			if (((Ship) theBoard[y][x]).getHealth() == 0) {
				// The ship is now fully sunk so return SUNK
				return ShotResult.SUNK;
			}
			// The ship did not sink so this is just a hit.
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

	/**
	 * Adds a given ship to the board.
	 * 
	 * @param ship      to be added
	 * @param locations int array of locations for the ship to be added in the
	 *                  form of [[[x1][y1]],[[x2][y2]]]
	 * @return true if ship was added, false if ship cannot be added
	 */
	public boolean addShipToBoard(Ship ship) {
		for (int[] location : ship.locations) {
			int x = location[0];
			int y = location[1];
			if (x >= theBoard[0].length || y >= theBoard.length
					|| theBoard[y][x] != null)
				return false;
		}
		for (int[] location : ship.locations) {
			int x = location[0];
			int y = location[1];
			totalBoardHealth++;
			theBoard[y][x] = ship;
		}
		return true;
	}

	/**
	 * Returns the dimension of the square board
	 * 
	 * @return int
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Returns the board.
	 * 
	 * @return Object[][]
	 */
	public Object[][] getBoard() {
		return theBoard;
	}

	/**
	 * Returns the total health of remaining ships on the board.
	 * 
	 * @return int
	 */
	public int getBoardHealth() {
		return totalBoardHealth;
	}

	/**
	 * This returns an obfuscated object of a board at x, y
	 * 
	 * @return Object
	 */
	public Object getHiddenCoord(int x, int y) {
		if (x >= size || y >= size) {
			return ShotResult.INVALID;
		}
		if (theBoard[y][x] instanceof Ship) {
			return null;
		}
		return theBoard[y][x];
	}

	public String toString() {
		String result = "";
		for (Object[] row : theBoard) {
			for (Object col : row) {
				if (col == Board.ShotResult.HIT) {
					result += "H ";
				} else if (col == Board.ShotResult.MISS) {
					result += "M ";
				} else if (col instanceof Ship) {
					result += "S ";
				} else if (col == null) {
					result += "_ ";
				}
			}
			result += "\n";
		}
		return result;
	}

	/**
	 * This toString includes the ships in the final string output, which should
	 * be only visible to the current player.
	 * 
	 * @return string
	 */
	public String toStringCoord(int x, int y) {
		String result = "";

		if (theBoard[y][x] == Board.ShotResult.HIT) {
			result += "H ";
		} else if (theBoard[y][x] == Board.ShotResult.MISS) {
			result += "M ";
		} else if (theBoard[y][x] instanceof Ship) {
			result += "S ";
		} else if (theBoard[y][x] == null) {
			result += "_ ";
		}

		return result;
	}

	/**
	 * This toString hides the ships in the final string output.
	 * 
	 * TODO: somehow figure out how to show opponents ships on this view too??
	 * 
	 * @return string
	 */
	public String toStringOpponentView() {
		String result = "";
		for (Object[] row : theBoard) {
			for (Object col : row) {
				if (col == Board.ShotResult.HIT) {
					result += "H";
				} else if (col == Board.ShotResult.MISS) {
					result += "M";
				} else {
					result += "_";
				}
			}
			result += "\n";
		}
		return result;
	}

	public boolean predictShipPlacement(Ship ship, int x, int y,
			String direction) {
		int[][] locations = new int[ship.getSize()][2];
		locations[0] = new int[] { x, y };

		// Predict ship placement in the specified direction
		for (int i = 1; i < ship.getSize(); i++) {
			switch (direction) {
			case "up":
				locations[i] = new int[] { x, y - i };
				break;
			case "down":
				locations[i] = new int[] { x, y + i };
				break;
			case "left":
				locations[i] = new int[] { x - i, y };
				break;
			case "right":
				locations[i] = new int[] { x + i, y };
				break;
			default:
				return false; // Invalid direction
			}

			// Check if the predicted location is within the board boundaries
			if (locations[i][0] < 0 || locations[i][0] >= size
					|| locations[i][1] < 0 || locations[i][1] >= size) {
				return false;
			}
		}

		// Check if the predicted locations are available for placement
		return addShipToBoard(ship, locations);
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
