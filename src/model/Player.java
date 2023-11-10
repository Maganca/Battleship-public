package model;

public class Player {
	PlayerType playerType;
	int wins;
	int losses;

	public enum PlayerType {
		HUMAN, AI_HARD, AI_RANDOM, LAN_PLAYER1, LAN_PLAYER2;
	}

	public Player(PlayerType player) {
		this.wins = 0;
		this.losses = 0;
		this.playerType = player;
	}

	public PlayerType getType() {
		return playerType;
	}

	public void addWin() {
		wins++;
	}

	public int getWins() {
		return wins;
	}

	public void addLoss() {
		losses++;
	}

	public int getLosses() {
		return losses;
	}
}
