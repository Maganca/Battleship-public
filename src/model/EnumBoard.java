// Kade Dean

package model;

import java.util.ArrayList;

public class EnumBoard {
	// Main board object
	private int size;
	private int totalBoardHealth;
	private ShotResult[][] theBoard;
	private ArrayList<int[][]> ships;

	public enum ShotResult {
		SHIP, HIT, MISS, SUNK, REPEAT_ATTEMPT;
	}

	public EnumBoard(int size) {
		this.size = size;
		theBoard = new ShotResult[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				theBoard[i][j] = null;
			}
		}
		ships = new ArrayList<int[][]>();
		totalBoardHealth = 0;
	}

	/**
	 * Attempts a new shot on the board and returns one of the resulting ShotResult
	 * enum types
	 * 
	 * @param x
	 * @param y
	 * @return ShotResult enum
	 */
	public ShotResult shotAttempt(int x, int y) {
		if (theBoard[y][x] == null) {
			theBoard[y][x] = ShotResult.MISS;
		} else if (theBoard[y][x] == ShotResult.SHIP) {
			theBoard[y][x] = ShotResult.HIT;
			totalBoardHealth--;
			isSunk(x, y);
		} else {
			return ShotResult.REPEAT_ATTEMPT;
		}
		return theBoard[y][x];
	}

	public boolean isSunk(int x, int y) {
		//for (int i = start[0]; i < end[0]; i++) {
			//for (int j = start[1]; j < end[1]; j++) {
				//if (theBoard[j][i] != ShotResult.HIT)
					//return false;
			//}
		//}
		return true;
	}

	public boolean addShipToBoard(int[] start, int direction, int size) {
		int[][] temp = new int[size][2];
		if (direction == 0) {
			if (start[1] + size > theBoard.length)
				return false;
			for (int i = 0; i < size; i++) {
				if (theBoard[start[1] + i][start[0]] != null)
					return false;
			}
			for (int i = 0; i < size; i++) {
				theBoard[start[1] + i][start[0]] = ShotResult.SHIP;
				temp[i] = new int[] { start[0], start[1] + i };
			}
		} else {
			if (start[0] + size > theBoard.length)
				return false;
			for (int i = 0; i < size; i++) {
				if (theBoard[start[1]][start[0] + i] != null)
					return false;
			}
			for (int i = 0; i < size; i++) {
				theBoard[start[1]][start[0] + i] = ShotResult.SHIP;
				temp[i] = new int[] { start[0], start[1] + i };
			}
		}
		ships.add(temp);
		totalBoardHealth += size;
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
	public ShotResult[][] getBoard() {
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

	public String toString() {
		String result = "";
		for (ShotResult[] row : theBoard) {
			for (ShotResult col : row) {
				if (col == ShotResult.HIT) {
					result += "H ";
				} else if (col == ShotResult.MISS) {
					result += "M ";
				} else if (col == ShotResult.SHIP) {
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
	 * This toString hides the ships in the final string output.
	 * 
	 * TODO: somehow figure out how to show opponents ships on this view too??
	 * 
	 * @return string
	 */
	public String toStringOpponentView() {
		String result = "";
		for (ShotResult[] row : theBoard) {
			for (ShotResult col : row) {
				if (col == ShotResult.HIT) {
					result += "H";
				} else if (col == ShotResult.MISS) {
					result += "M";
				} else {
					result += "_";
				}
			}
			result += "\n";
		}
		return result;
	}
}
