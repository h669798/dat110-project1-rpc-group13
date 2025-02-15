package no.hvl.dat110.messaging;

public class MessageUtils {

    // Constants:
    public static final int SEGMENTSIZE = 128;

    // Variables:
    public static int MESSAGINGPORT = 4000;
    public static String MESSAGINGHOST = "localhost";

    // Method for encapsulation:
    public static byte[] encapsulate(Message message) {
        byte[] data = message.getData();
        byte[] segment = new byte[SEGMENTSIZE];
        segment[0] = (byte) data.length;
        System.arraycopy(data, 0, segment, 1, data.length);
        return segment;
    }

    // Method for decapsulation:
    public static Message decapsulate(byte[] segment) {
        int length = segment[0];  
        byte[] data = new byte[length];
        System.arraycopy(segment, 1, data, 0, length);
        return new Message(data);
    }
}