package model;

import model.Board.ShotResult;

public interface AIStrategy {

	public ShotResult shoot(Board game);

	public boolean placeShips(Board game);

}
