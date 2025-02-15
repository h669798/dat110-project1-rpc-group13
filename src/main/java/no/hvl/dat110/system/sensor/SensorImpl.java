package no.hvl.dat110.system.sensor;

import no.hvl.dat110.rpc.RPCRemoteImpl;
import no.hvl.dat110.rpc.RPCUtils;
import no.hvl.dat110.rpc.RPCServer;

public class SensorImpl extends RPCRemoteImpl {

	static final int RANGE = 20;
	public SensorImpl(byte rpcid, RPCServer rpcserver) {
		super(rpcid,rpcserver);
	}

	// Implementation of the RPC method
	public int read() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println("Could not wait between readings. " + e.getMessage());
		}
		long seconds = System.currentTimeMillis();
		double temp = RANGE * Math.sin((double) seconds / 1000);
		System.out.println("--------------------");
		System.out.println("READ: " + String.format("%.2f", temp) + " °C");
		return (int) Math.round(temp);
	}

	// Called by RPC server on rpc identifier corresponding to read
	public byte[] invoke(byte[] param) {
		RPCUtils.unmarshallVoid(param);
		int temp = read();
        return RPCUtils.marshallInteger(temp);
	}
}
