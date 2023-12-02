package networking;

import java.util.Date;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class BattleshipServer extends Listener {

	// Server object
	static Server server;
	
	private static int port;

	public void main() throws Exception {
		System.out.println("Creating the server...");
		// Create the server
		server = new Server();

		// Register a packet class.
		server.getKryo().register(PacketMessage.class);
		// We can only send objects as packets if they are registered.

		// Bind to a port
		server.bind(port, port);

		// Start the server
		server.start();

		// Add the listener
		server.addListener(this);

		System.out.println("Server is operational!");
	}
	
	public BattleshipServer(int port) throws Exception {
		BattleshipServer.port = port;
		main();
	}

	// This is run when a connection is received!
	public void connected(Connection c) {
		System.out.println("Received a connection from " + c.getRemoteAddressTCP().getHostString());
		// Create a message packet.
		PacketMessage packetMessage = new PacketMessage();
		// Assign the message text.
		packetMessage.message = "Hello friend! The time is: " + new Date().toString();

		// Send the message
		c.sendTCP(packetMessage);
		// Alternatively, we could do:
		// c.sendUDP(packetMessage);
		// To send over UDP.
	}

	// This method shuts down the server gracefully
	public void shutDown() {
		if (server != null) {
			// Close all connections and stop the server
			server.close();
			server.stop();
			System.out.println("Server is shut down.");
		}
	}

	// This is run when we receive a packet.
	public void received(Connection c, Object p) {
		// We will do nothing here.
		// We do not expect to receive any packets.
		// (But if we did, nothing would happen)
	}

	// This is run when a client has disconnected.
	public void disconnected(Connection c) {
		System.out.println("A client disconnected!");
	}
}
