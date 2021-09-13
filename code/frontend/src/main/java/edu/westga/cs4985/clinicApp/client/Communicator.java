package edu.westga.cs4985.clinicApp.client;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

/**
 * A Communicator class to connect to the server using the REQ socket in the ZMQ library/
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 */
public class Communicator {
	private ZContext context;
	private Socket socket;
	public static final String SEPARATOR = "," ;
	
	/**
	 * Creates the communicator by connect to the server with the address
	 * 
	 * {@code tcp://*:5555}
	 * 
	 * @precondition none
	 * @postcondition connected to the server
	 * 
	 */
	@SuppressWarnings("deprecation")
	public Communicator() {
		this.context = new ZContext(1);
		System.out.println("Connecting to server");
		this.socket = context.createSocket(ZMQ.REQ);
		this.socket.connect("tcp://127.0.0.1:5561");
	}
	
	/**
	 * Requests to the server with the request type and data. Then it will return a reply.
	 * 
	 * @precondition context and socket must be opened
	 * @postcondition none
	 * 
	 * @param requestType the request type
	 * @param data the data to be use by the server to fetch or update what is given.
	 * @return a reply from the server as a String
	 */
	public String request(RequestType requestType, String data) {
		if(this.context == null) {
			throw new IllegalStateException("The context has been closed or never opened");
		}
		if(this.socket == null) {
			throw new IllegalStateException("The socket has been closed or never opened");
		}
		String request = requestType + Communicator.SEPARATOR + data;
		socket.send(request.getBytes(ZMQ.CHARSET), 0);
		
		byte[] reply = socket.recv(0);
		String response = new String(reply, ZMQ.CHARSET);
		return response;
	}
	
	/**
	 * Closes the socket and context if it has not already been close.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public void close() {
		if(this.socket != null) {
			this.socket.close();
			this.socket = null;
		}
		if(this.context != null) {
			this.context.close();
			this.context = null;
		}
	}
	
	@Override
	public void finalize() {
		this.close();
	}

}
