package server;

import org.zeromq.ZMQ;

public class Main {

	public static void main(String[] args) {
		Server server = new Server();
		Client client = new Client();
		
		server.start();
		client.start();
		
		System.out.println(ZMQ.CHARSET);
	}

}
