package edu.westga.cs4985.clinicApp.main;

import org.zeromq.ZMQ;

import edu.westga.cs4985.clinicApp.server.Server;

public class Main {

	public static void main(String[] args) {
		Server server = new Server();
		
		
		server.start();
		
		System.out.println("Server starting " + ZMQ.CHARSET);
	}

}
