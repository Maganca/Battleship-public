package model;

import model.Board.ShotResult;
import model.Player.PlayerType;

public class BattleshipGame {
	private Player player1, player2;
	private Board player1Board, player2Board;
	private int size;

	public BattleshipGame(Player player1, Player player2, int size) {
		this.player1 = player1;
		this.player2 = player2;
		this.size = size;
		this.player1Board = new Board(size);
		this.player2Board = new Board(size);

		// Automatically places ships if player 1 is computer
		if (player1.getType().equals(PlayerType.AI_HARD)) {
			HardAI ai = new HardAI();
			ai.placeShips(player1Board);
		}
		if (player1.getType().equals(PlayerType.AI_RANDOM)) {
			RandomAI ai = new RandomAI();
			ai.placeShips(player1Board);
		}

		// Automatically places ships if player 2 is computer
		if (player2.getType().equals(PlayerType.AI_HARD)) {
			HardAI ai = new HardAI();
			ai.placeShips(player2Board);
		}
		if (player2.getType().equals(PlayerType.AI_RANDOM)) {
			RandomAI ai = new RandomAI();
			ai.placeShips(player2Board);
		}
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public ShotResult player1Turn(int x, int y) {
		if (player1.getType().equals(PlayerType.AI_RANDOM)
				|| player1.getType().equals(PlayerType.AI_HARD)) {
			return ShotResult.INVALID;
		}
		return player2Board.shotAttempt(x, y);
	}

	public ShotResult player1Turn() {
		return aiShot(player2Board, player1.getType());
	}

	public ShotResult player2Turn(int x, int y) {
		if (player2.getType().equals(PlayerType.AI_RANDOM)
				|| player2.getType().equals(PlayerType.AI_HARD)) {
			return ShotResult.INVALID;
		}
		return player1Board.shotAttempt(x, y);
	}

	public ShotResult player2Turn() {
		return aiShot(player1Board, player2.getType());
	}

	public boolean gameStillRunning() {
		if (player1Board.getBoardHealth() == 0
				|| player2Board.getBoardHealth() == 0) {
			return false;
		}
		return true;
	}

	private ShotResult aiShot(Board board, PlayerType type) {
		switch (type) {
		case AI_RANDOM:
			RandomAI randomAI = new RandomAI();
			return randomAI.shoot(board);
		case AI_HARD:
			HardAI hardAI = new HardAI();
			return hardAI.shoot(board);
		default:
			return ShotResult.INVALID;
		}
	}

	public Board getPlayer1Board() {
		return player1Board;
	}

	public Board getPlayer2Board() {
		return player2Board;
	}

	public int getSize() {
		return size;
	}
}
