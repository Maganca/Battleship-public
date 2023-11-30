package model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import views_controller.ViewManager;

public class ClientHandler {
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Board gameBoard;
	private ViewManager vm;

	public ClientHandler(String serverAddress, int port, ViewManager vm) throws Exception {
		this.vm = vm;
		socket = new Socket(serverAddress, port);
		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());
		startGameLoop();
	}

	private void startGameLoop() {
		while (socket.isConnected()) {

		}
	}
	// Logic to handle communication with the server
}
