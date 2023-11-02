package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.*;

class a {
	public static BoardBuilder board = new BoardBuilder();
	
	public static ShipBuilder ship = new ShipBuilder();
	
	public static class BoardBuilder{
		
	}
	
	public static class ShipBuilder{
		int size;
		public Ship build() {
			Ship result = new Ship(this.size);
		}
	}
	
	
}

class BoardTest {

	@Test
	void test1() {
		
	}

}
