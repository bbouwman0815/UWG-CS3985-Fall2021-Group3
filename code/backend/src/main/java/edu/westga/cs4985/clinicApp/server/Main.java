package edu.westga.cs4985.clinicApp.server;

import org.zeromq.ZMQ;

public class Main {

	public static void main(String[] args) {
		Server server = new Server();
		
		
		server.start();
		
		System.out.println("Server starting " + ZMQ.CHARSET);
	}

}
