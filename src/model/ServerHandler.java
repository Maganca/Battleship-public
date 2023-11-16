package model;

import java.io.*;
import java.net.*;
import java.util.Date;

/**
 * Basic server template code to begin working on Battleship multiplayer.
 */
public class ServerHandler {

	/**
	 * 
	 * @param args port
	 */
	public static void main(String[] args) {
		if (args.length < 1)
			return;

		int port = Integer.parseInt(args[0]);

		try (ServerSocket serverSocket = new ServerSocket(port)) {

			System.out.println("Server is listening on port " + port);

			while (true) {
				Socket socket = serverSocket.accept();

				System.out.println("Waiting for client to connect");

				OutputStream output = socket.getOutputStream();
				PrintWriter writer = new PrintWriter(output, true);

				writer.println(new Date().toString());
			}

		} catch (IOException ex) {
			System.out.println("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}