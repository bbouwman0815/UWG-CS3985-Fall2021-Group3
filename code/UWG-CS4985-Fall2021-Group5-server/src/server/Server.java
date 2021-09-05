package server;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Server extends Thread {

	public void run() {
		Context context = ZMQ.context(1);
		Socket socket = context.socket(ZMQ.REP);
		socket.bind("tcp://127.0.0.1:5555");
		
        while (!Thread.currentThread().isInterrupted()) {

            byte[] reply = socket.recv(0);
            String message = new String(reply, ZMQ.CHARSET);
			System.out.println("Server - Received " + ": [" + message + "]");
            if (message.equals("exit")) {
            	System.out.println("Server - exiting");
                socket.close();
                context.term();
            	return;
            }
            this.delay(); 
            //  Create a "Hello" message.
            String request = "world";
            // Send the message
            
            socket.send(request.getBytes(ZMQ.CHARSET), 0);

        }

        socket.close();
        context.term();
		
	}

	private void delay() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
