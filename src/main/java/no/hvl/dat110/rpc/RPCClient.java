package no.hvl.dat110.rpc;

import no.hvl.dat110.messaging.*;

import static no.hvl.dat110.rpc.RPCUtils.*;

public class RPCClient {

	// Underlying messaging client used for RPC communication
	private MessagingClient msgclient;

	// Underlying messaging connection used for RPC communication
	private MessageConnection connection;

	// Constructor:
	public RPCClient(String server, int port) {
		msgclient = new MessagingClient(server,port);
	}

	// Method for establishing connection:
	public void connect() {
		    try {
		        connection = msgclient.connect();
		    } catch (Exception e) {
		        System.err.println("Failed to establish a connection." + e.getMessage());
		    }
	}

	// Method for disconnecting:
	public void disconnect() {
		if (connection != null) {
	        connection.close();
	    }
	}

	// Method for calling RPC:
	public byte[] call(byte rpcid, byte[] param) {
		byte[] data = encapsulate(rpcid, param);
		connection.send(new Message(data));
        return RPCUtils.decapsulate(connection.receive().getData());
	}
}