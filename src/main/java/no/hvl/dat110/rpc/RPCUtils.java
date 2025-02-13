package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;


public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		
		byte[] rpcmsg = null;
		
		// TODO - START
		
		// Encapsulate the rpcid and payload in a byte array according to the RPC message syntax / format
		int a = payload != null ? payload.length: 0; //må sjekke om payload har innhold og lengded ikke er null
		rpcmsg = new byte[a+1];
		rpcmsg[0] = rpcid;

		for (int i = 0; i < a ; i++) {

			rpcmsg[i+1] = payload[i];
		}

		// TODO - END
		
		return rpcmsg;
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {

		byte[] payload = null;
		
		// TODO - START
		
		// Decapsulate the rpcid and payload in a byte array according to the RPC message syntax
		int len = rpcmsg.length-1;
		payload = new byte[len];

		for (int i = 0; i < len; i++) {

			payload[i] = rpcmsg[i+1];
		}

		// TODO - END
		
		return payload;
		
	}

	// convert String to byte array
	public static byte[] marshallString(String str) {
		
		byte[] encoded = new byte[2]; //new byte[2]

		// TODO - START 

		encoded = str.getBytes();

		// TODO - END
		
		return encoded;
	}

	// convert byte array to a String
	public static String unmarshallString(byte[] data) {
		
		String decoded = null;

		// TODO - START

		decoded = new String(data); //new String(data)

		// TODO - END
		
		return decoded;
	}
	
	public static byte[] marshallVoid() {

		byte[] encoded = null;

		// TODO - START

		//encoded = new byte[0];

		// TODO - END
		
		//return encoded;
		return null;
	}
	
	
	public static void unmarshallVoid(byte[] data) {
		
		// TODO
		

		
	}

	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}

	// convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
		
	}

	// integer to byte array representation
	public static byte[] marshallInteger(int x) {
		
		byte[] encoded = null;

		
		// TODO - START

		ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES);
		byteBuffer.putInt(x);
		encoded = byteBuffer.array();
		

		// TODO - END
		
		return encoded;
	}
	
	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {
		
		int decoded = 0;
		
		// TODO - START

		ByteBuffer intBuffer = ByteBuffer.wrap(data);

		decoded = intBuffer.getInt();
		

		// TODO - END
		
		return decoded;
		
	}
}