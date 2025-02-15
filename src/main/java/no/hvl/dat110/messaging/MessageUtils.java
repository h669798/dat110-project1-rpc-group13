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

        for (int i = 0; i < data.length; i++) {
            segment[i + 1] = data[i];
        }
        return segment;
    }

    // Method for decapsulation:
    public static Message decapsulate(byte[] segment) {
        int length = segment[0];  
        byte[] data = new byte[length];

        for (int i = 0; i < length; i++) {
            data[i] = segment[i + 1];
        }
        return new Message(data);
    }
}