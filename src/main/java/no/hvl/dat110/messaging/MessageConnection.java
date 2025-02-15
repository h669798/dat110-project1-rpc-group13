package no.hvl.dat110.messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static no.hvl.dat110.messaging.MessageUtils.*;

public class MessageConnection {

	// Variables:
	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection

	// Constructor:
	public MessageConnection(Socket socket) {
		try {
			this.socket = socket;
			outStream = new DataOutputStream(socket.getOutputStream());
			inStream = new DataInputStream (socket.getInputStream());
		} catch (IOException ex) {
			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	// Method for sending message:
	public void send(Message message) {
		byte[] data = encapsulate(message);
		try {
			outStream.write(data);
			outStream.flush();
		} catch (IOException e) {
			System.err.println("Error in sending message. " + e.getMessage());
		}
	}

	// Method for receiving message:
	public Message receive() {
		Message message = null;
		byte[] data = new byte[SEGMENTSIZE];
		try {
			inStream.read(data);
			message = decapsulate(data);
		}
		catch (IOException e) {
			System.err.println("Error in receiving message. " + e.getMessage());
		}
		return message;
	}

	// Close the connection by closing streams and the underlying socket
	public void close() {
		try {
			outStream.close();
			inStream.close();
			socket.close();
		} catch (IOException e) {
			System.err.println("Connection: " + e.getMessage());
		}
	}
}