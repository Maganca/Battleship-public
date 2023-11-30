package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerHandler {
	private ServerSocket serverSocket;
	private List<ServerClient> clients = new ArrayList<>();
	private Board gameBoard;
	private GameStartListener gameStartListener; // Listener for game start event

	public enum ServerComms {
		START_GAME, PLAYER_1, PLAYER_2;
	}

	public interface GameStartListener {
		void onGameStart(); // Method to be called when the game starts
	}

	public void setGameStartListener(GameStartListener listener) {
		this.gameStartListener = listener;
	}

	public ServerHandler(int port) throws IOException {
		try {
			serverSocket = new ServerSocket(port);
			gameBoard = new Board(10); // Initialize the game board with a size

			// Start listening for incoming connections
			while (clients.size() < 2) {
				Socket clientSocket = serverSocket.accept();
				ServerClient serverClient = new ServerClient(clientSocket, gameBoard);
				clients.add(serverClient);
				serverClient.start();
			}
			startGame();
		} catch (IOException e) {
			throw e;
		}
	}

	private void startGame() {
		System.out.println("game is goin!");
		if (gameStartListener != null) {
			gameStartListener.onGameStart();
		}
		clients.get(1).send(ServerComms.START_GAME);
		clients.get(0).send(ServerComms.START_GAME);
	}

	// Other methods to manage game logic, sessions, etc.
}
