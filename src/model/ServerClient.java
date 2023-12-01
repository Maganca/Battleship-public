package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.ServerHandler.ServerComms;

public class ServerClient extends Thread {
    private Socket clientSocket;
    private Board gameBoard;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private ServerHandler serverHandler;

    public ServerClient(Socket socket, Board board, ServerHandler handler) {
        this.clientSocket = socket;
        this.gameBoard = board;
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
    	if (clientSocket!= null) {
    		try {
				clientSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    public void run() {
        try {
            while (true) {
                Object received = input.readObject();
                if (received instanceof ServerComms) {
                    ServerComms message = (ServerComms) received;
                    if (message == ServerComms.READY) {
                        serverHandler.playerReady(); // Signal the server that this player is ready
                    }
                }
                // Add logic to handle other types of incoming messages if needed
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // Other methods for handling client communication
}
