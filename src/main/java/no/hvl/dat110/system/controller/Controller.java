package no.hvl.dat110.system.controller;


import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCClientStopStub;

public class Controller  {
	
	private static int N = 5;
	
	public static void main (String[] args) {
		
		DisplayStub display;
		SensorStub sensor;
		
		RPCClient displayClient,sensorClient;
		
		System.out.println("Controller starting ...");
				
		// create RPC clients for the system
		displayClient = new RPCClient(Common.DISPLAYHOST,Common.DISPLAYPORT);
		sensorClient = new RPCClient(Common.SENSORHOST,Common.SENSORPORT);
		
		// setup stop methods in the RPC middleware
		RPCClientStopStub stopdisplay = new RPCClientStopStub(displayClient);
		RPCClientStopStub stopsensor = new RPCClientStopStub(sensorClient);
			
		display = new DisplayStub(displayClient);
		sensor = new SensorStub(sensorClient);

		displayClient.connect();
		sensorClient.connect();

		// finite loop
		for (int i = 0; i < 10; i++) {
			 int temp = (int) Math.round(sensor.read());
			    display.write(String.valueOf(temp));
		}
		
		stopdisplay.stop();
		stopsensor.stop();
	
		displayClient.disconnect();
		sensorClient.disconnect();
		
		System.out.println("Controller stopping ...");
	}
}