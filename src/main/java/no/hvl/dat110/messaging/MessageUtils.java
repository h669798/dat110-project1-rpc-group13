package no.hvl.dat110.messaging;

import java.util.Arrays;
import no.hvl.dat110.TODO;

public class MessageUtils {

	// Constants:
	public static final int SEGMENTSIZE = 128;

	// Variables:
	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	// Method for encapsulation:
	public static byte[] encapsulate(Message message) {
		byte[] segment = new byte[SEGMENTSIZE];
		byte[] data = message.getData();
		
		segment[0] = (byte)data.length;
		System.arraycopy(data, 0, segment, 1, data.length);

		return segment;
	}

	// Method for decapsulation:
	public static Message decapsulate(byte[] segment) {
		byte length = segment[0];
		byte[] data = new byte[length];
		
		System.arraycopy(segment, 1, data, 0, data.length);

        return new Message(data);
	}
}
