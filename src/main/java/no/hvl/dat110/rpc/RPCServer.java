package no.hvl.dat110.rpc;

import java.util.HashMap;

import no.hvl.dat110.messaging.MessageConnection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;

public class RPCServer {

	private MessagingServer msgserver;
	private MessageConnection connection;
	
	// hashmap to register RPC methods which are required to extend RPCRemoteImpl
	// the key in the hashmap is the RPC identifier of the method
	private HashMap<Byte,RPCRemoteImpl> services;
	
	public RPCServer(int port) {
		this.msgserver = new MessagingServer(port);
		this.services = new HashMap<Byte,RPCRemoteImpl>();
	}
	
	public void run() {
		// the stop RPC method is built into the server
		RPCRemoteImpl rpcstop = new RPCServerStopImpl(RPCCommon.RPIDSTOP,this);
		System.out.println("RPC SERVER RUN - Services: " + services.size());
		connection = msgserver.accept();
		System.out.println("RPC SERVER ACCEPTED");
		boolean stop = false;
		
		while (!stop) {
		   byte rpcid = 0;
		   Message requestmsg, replymsg;

		   try {
               // Receive an RPC request message
               requestmsg = connection.receive();
               byte[] requestData = requestmsg.getData();
               
               // Extract RPC ID and parameters
               rpcid = requestData[0];
               byte[] params = RPCUtils.decapsulate(requestData);
               
               // Look up the method to be invoked
               RPCRemoteImpl method = services.get(rpcid);
               
               if (method != null) {
                   // Invoke the method and get the return value
                   byte[] returnVal = method.invoke(params);
                   
                   // Encapsulate the return value
                   byte[] replyData = RPCUtils.encapsulate(rpcid, returnVal);
                   replymsg = new Message(replyData);
                   
                   // Send back the reply message
                   connection.send(replymsg);
               } else {
                   System.out.println("Unknown RPC ID: " + rpcid);
               }
			   if (rpcid == RPCCommon.RPIDSTOP) {
				   stop = true;
			   }
		   } catch (Exception e) {
			   System.out.println("RPC run error: " + e.getMessage());
			}
	
		}
	}
	
	// used by server side method implementations to register themselves in the RPC server
	public void register(byte rpcid, RPCRemoteImpl impl) {
		services.put(rpcid, impl);
	}
	
	public void stop() {

		if (connection != null) {
			connection.close();
		} else {
			System.out.println("RPCServer.stop - connection was null");
		}
		
		if (msgserver != null) {
			msgserver.stop();
		} else {
			System.out.println("RPCServer.stop - msgserver was null");
		}
		
	}
}