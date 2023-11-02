// Kade Dean
package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.*;

class BoardTest {

	@Test
	void testBoardSize() {
		assertEquals(10, a.board.size(10).build());
	}

}

class a {
	public static BoardBuilder board = new BoardBuilder();

	public static ShipBuilder ship = new ShipBuilder();

	public static class BoardBuilder {
		int size;

		BoardBuilder() {
			this.size = 10;
		}

		BoardBuilder(int size) {
			this.size = size;
		}

		BoardBuilder(int[][] locations, Ship ship) {
			this.size = size;
		}

		public BoardBuilder size(int size) {
			return new BoardBuilder(size);
		}

		public BoardBuilder w(Ship ship) {
			return new BoardBuilder(size);
		}

		public Board build() {
			Board result = new Board(this.size);
			return result;
		}
	}

	public static class ShipBuilder {
		int size;

		ShipBuilder() {
			this.size = 3;
		}

		ShipBuilder(int size) {
			this.size = size;
		}

		public Ship build() {
			Ship result = new Ship(this.size);
			return result;
		}
	}

}