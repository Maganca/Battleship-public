// Kade Dean
// Kade Dean
package model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleObjectProperty;

/**
 * Board.java class that handles all battleship board functions.
 */
public class Board {
	private Object[][] theBoard; // Main board object
	private int size; // Board size
	private int totalBoardHealth; // Aggregate health of all ships on board
	
	private List<Ship> shipList = new ArrayList<>();
	
	private final SimpleObjectProperty<GameResult> gameResult = new SimpleObjectProperty<>(null);

	private List<Ship> placedShips;
	
    public enum GameResult {
        WIN, LOSE
    }

    public SimpleObjectProperty<GameResult> gameResultProperty() {
        return gameResult;
    }
	
	/**
	 * Shot result enum types.
	 */
	public enum ShotResult {
		HIT, MISS, SUNK, REPEAT_ATTEMPT, GAME_WON, INVALID;
	}

	/**
	 * Board object constructor
	 * 
	 * @param size of board
	 */
	public Board(int size) {
		this.size = size;
		theBoard = new Object[size][size];
		this.totalBoardHealth = 0;
		
		placedShips = new ArrayList<>();
	}
	
	/**
	 * Attempts a new shot on the board and returns one of the resulting ShotResult
	 * enum types
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @return ShotResult enum
	 */
	public ShotResult shotAttempt(int x, int y) {
		// Checks to make sure shot attempt is valid
		if (x >= theBoard[0].length || y >= theBoard.length || x < 0 || y < 0) {
			return ShotResult.INVALID;
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

	/**
	 * Adds a given ship to the board.
	 * 
	 * @param ship to be added. The ship object will contain the needed coordinates.
	 * @return true if ship was added, false if ship cannot be added
	 */

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
		shipList.add(ship);
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
	 * @return int of board health
	 */
	public int getBoardHealth() {
		return this.totalBoardHealth;
	}
	public List<Ship> getShipList(){
		return shipList;
	}
	/**
	 * This returns an obfuscated object of a board at x, y
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @return object at location on board
	 */
	public Object getHiddenCoord(int x, int y) {
		// basic error checking
		if ((x > size - 1 || x < 0) || (y > size - 1 || y < 0)) {
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
	 * This toString includes the ships in the final string output, which should be
	 * only visible to the current player.
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @return String representation of the coordinate.
	 */
	public String toStringCoord(int x, int y) {
		String result = "";

		if (theBoard[y][x] == Board.ShotResult.HIT) {
			result += "H";
		} else if (theBoard[y][x] == Board.ShotResult.MISS) {
			result += "M";
		} else if (theBoard[y][x] instanceof Ship) {
			result += "S";
		} else if (theBoard[y][x] == null) {
			result += "_";
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

	public boolean addShipToBoard1(Ship ship) {
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
		placedShips.add(ship);
		return true;
		
	}
	
	public boolean allShipsPlaced() {
        for (Ship ship : Ship.values()) {
            if (!placedShips.contains(ship)) {
                return false;
            }
        }
        return true;
    }
	
	public void customizeBoardSize(int newSize) {
        this.size = newSize;
        this.theBoard = new Object[newSize][newSize];
        clearBoard();
    }
	

	public void clearBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				theBoard[i][j] = null;
			}
		}
		this.totalBoardHealth = 0;
	}
}
