package no.hvl.dat110.messaging;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MessagingServer {

	// server-side socket for accepting incoming TCP connections
	private ServerSocket welcomeSocket;

	public MessagingServer(int port) {

		try {

			this.welcomeSocket = new ServerSocket(port);

		} catch (IOException ex) {

			System.out.println("Messaging server: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	// accept an incoming connection from a client
	public MessageConnection accept() {

		MessageConnection connection = null;

		// TODO - START
		// accept TCP connection on welcome socket and create messaging connection to be returned

		 try {
	            System.out.println("Server waiting for connection...");
	            Socket socket = welcomeSocket.accept();
	            connection = new MessageConnection(socket);
	            System.out.println("Client connected to server");
	        } catch (IOException e) {
	            System.out.println("Error accepting connection: " + e.getMessage());
	            e.printStackTrace();
	        }
		// TODO - END
		
		return connection;

	}

	public void stop() {

		if (welcomeSocket != null) {

			try {
				welcomeSocket.close();
			} catch (IOException ex) {

				System.out.println("Messaging server: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

}

