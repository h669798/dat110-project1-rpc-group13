package no.hvl.dat110.system.display;


import no.hvl.dat110.rpc.RPCRemoteImpl;
import no.hvl.dat110.rpc.RPCUtils;
import no.hvl.dat110.rpc.RPCServer;

public class DisplayImpl extends RPCRemoteImpl {

	public DisplayImpl(byte rpcId, RPCServer rpcserver) {
		super(rpcId,rpcserver);
	}

	public void write(String message) {
		System.out.println("DISPLAY:" + message);
	}
	
	public byte[] invoke(byte[] param) {
		byte[] returnVal = null;
		String result = RPCUtils.unmarshallString(param);
		write(result);
		returnVal = RPCUtils.marshallString(result);
		return returnVal;
	}
}