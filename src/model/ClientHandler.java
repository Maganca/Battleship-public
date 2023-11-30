package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javafx.scene.input.InputEvent;

public class ClientHandler {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Board gameBoard;

    public ClientHandler(String serverAddress, int port) {
        try {
            socket = new Socket(serverAddress, port);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            startGameLoop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void startGameLoop() {
    }
    // Logic to handle communication with the server
}
