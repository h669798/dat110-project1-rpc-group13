package no.hvl.dat110.messaging;

//import no.hvl.dat110.TODO;

public class Message {

	// the up to 127 bytes of data (payload) that a message can hold
	private byte[] data;

	
	public Message(byte[] data) {
		
		if (data != null && data.length<MessageUtils.SEGMENTSIZE) {
			this.data= data;;
		}
		
	
			
		// TODO - END
	}

	public byte[] getData() {
		return this.data; 
	}

}