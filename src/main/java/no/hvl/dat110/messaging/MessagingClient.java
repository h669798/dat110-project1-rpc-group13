package no.hvl.dat110.messaging;

import java.io.IOException;
import java.net.Socket;

public class MessagingClient {

	// Name/IP address of the messaging server
	private String server;

	// Server port on which the messaging server is listening
	private int port;

	// Constructor:
	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}
	
	// Setup of a messaging connection to a messaging server
	public MessageConnection connect () {
		// Client-side socket for underlying TCP connection to messaging server
		MessageConnection connection = null;
		try {
			Socket clientSocket = new Socket(server, port);
			connection = new MessageConnection(clientSocket);
		} catch (IOException e) {
			System.err.println("Could not connect to server. " + e.getMessage());
		}
		return connection;
	}
}