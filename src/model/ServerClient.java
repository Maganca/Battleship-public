package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.ServerHandler.ServerComms;

public class ServerClient extends Thread {
	private Socket clientSocket;
	private Player player;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private ServerHandler serverHandler;

	public ServerClient(Socket socket, ServerHandler handler) {
		this.clientSocket = socket;
		this.serverHandler = handler;

		try {
			output = new ObjectOutputStream(clientSocket.getOutputStream());
			input = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(ServerComms msg) {
		try {
			output.writeObject(msg);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stopSocket() {
		if (clientSocket != null) {
			try {
				clientSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void run() {
		try {
			while (true) {
				Object received = input.readObject();
				if (received instanceof ServerComms) {
					ServerComms message = (ServerComms) received;
					if (message == ServerComms.READY) {
						System.out.println("[SERVER] recived ready state");
						serverHandler.playerReady(); // Signal the server that this player is ready
					}
				}
				if (received instanceof Player) {
					System.out.println("[SERVER] recived player object containing ships");
					Player player = (Player) received;
					this.player = player;
				}
				// Add logic to handle other types of incoming messages if needed
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Other methods for handling client communication
}
