package no.hvl.dat110.messaging;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MessagingServer {

	// Server-side socket for accepting incoming TCP connections
	private ServerSocket welcomeSocket;

	// Constructor:
	public MessagingServer(int port) {
		try {
			this.welcomeSocket = new ServerSocket(port);
		} catch (IOException ex) {
			System.out.println("Messaging server: " + ex.getMessage());
		}
	}

	// Accept an incoming connection from a client
	public MessageConnection accept() {
		MessageConnection connection = null;
		 try {
	            System.out.println("Server waiting for connection...");
	            Socket socket = welcomeSocket.accept();
	            connection = new MessageConnection(socket);
	            System.out.println("Client connected to server!");
	        } catch (IOException e) {
	            System.err.println("Error accepting connection: " + e.getMessage());
	        }
		return connection;
	}

	// Method for stopping the server:
	public void stop() {
		if (welcomeSocket != null) {
			try {
				welcomeSocket.close();
			} catch (IOException ex) {
				System.out.println("Messaging server: " + ex.getMessage());
			}
		}
	}
}