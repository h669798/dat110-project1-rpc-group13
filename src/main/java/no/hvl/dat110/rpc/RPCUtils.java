package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;

public class RPCUtils {

	// Encapsulate RPC-ID and payload:
	public static byte[] encapsulate(byte rpcid, byte[] payload) {

		int a = payload != null ? payload.length: 0;
		byte[] rpcMsg = new byte[a+1];
		rpcMsg[0] = rpcid;

	    if (payload != null)
		  System.arraycopy(payload, 0, rpcMsg, 1, a);
	    return rpcMsg;
	}

	// Decapsulate message:
	public static byte[] decapsulate(byte[] rpcmsg) {
		byte[] payload = new byte[rpcmsg.length-1];
        System.arraycopy(rpcmsg, 1, payload, 0, payload.length);
		return payload;
	}

	// Convert String to byte array
	public static byte[] marshallString(String str) {
		return str.getBytes();
	}

	// Convert byte array to a String
	public static String unmarshallString(byte[] data) {
		return new String(data);
	}

	// Convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
		byte[] encoded = new byte[1];
		if (b)
			encoded[0] = 1;
		else
			encoded[0] = 0;
		return encoded;
	}

	// Convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {
		return data[0] != 0;
	}

	// Integer to byte array representation
	public static byte[] marshallInteger(int x) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES);
		byteBuffer.putInt(x);
        return byteBuffer.array();
	}
	
	// Byte array representation to integer
	public static int unmarshallInteger(byte[] data) {
		ByteBuffer intBuffer = ByteBuffer.wrap(data);
        return intBuffer.getInt();
	}

	public static void unmarshallVoid(byte[] param) {
	}

	public static byte[] marshallVoid() {
        return new byte[0];
    }
}