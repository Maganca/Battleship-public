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

    public ServerClient(Socket socket, Board board) {
        this.clientSocket = socket;
        this.gameBoard = board;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    // Other methods for handling client communication
}
