package no.hvl.dat110.messaging;

import no.hvl.dat110.TODO;

public class Message {

	// Variable (up to 127 in value):
	private byte[] data;

	// Constructor:
	public Message(byte[] data) {
		if (data == null || data.length > 127) {
			throw new IllegalArgumentException("Data can't be null or above 127 in value.");
		}
		this.data = data;
	}

	// Get-method for data:
	public byte[] getData() {
		return this.data; 
	}
}
