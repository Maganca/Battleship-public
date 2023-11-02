public class HitandMiss {
	public static boolean isHit(String[][] board, int x, int y) {
        if (ocean[x][y].equals("S")) {
            ocean[x][y] = "H"; // Mark as hit in the board
            return true; // Return true when it's a hit
        } else if (ocean[x][y].equals("█")) {
            ocean[x][y] = "H"; 
            return true; 
        return false; // Return false when it's not a hit
    }
	}
}
