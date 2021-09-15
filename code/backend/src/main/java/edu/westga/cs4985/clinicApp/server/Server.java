package edu.westga.cs4985.clinicApp.server;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Server extends Thread {
	
	@SuppressWarnings("unchecked")
	public String bookAppointment(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/appointments.json");
    	JSONArray jsonObject = (JSONArray) parser.parse(reader);
        
		FileWriter writer = new FileWriter("./jsonFiles/appointments.json");
		
		JSONObject data = (JSONObject) parser.parse(jsonString);
		
		jsonObject.add(data);
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "ADDED";
	}
	
	@SuppressWarnings("unchecked")
	public String cancleAppointment(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/appointments.json");
    	JSONArray jsonObject = (JSONArray) parser.parse(reader);
        
		FileWriter writer = new FileWriter("./jsonFiles/appointments.json");
		
		JSONObject data = (JSONObject) parser.parse(jsonString);
		JSONObject result = null;
		for (Object aData : jsonObject) {
        	JSONObject parseData = (JSONObject) aData;
        	if (parseData.get("medicalPersonnel").equals(data.get("medicalPersonnel")) && parseData.get("patient").equals(data.get("patient"))
        			&& parseData.get("date").equals(data.get("date"))){
        		result = parseData;
        	}
		}
		jsonObject.remove(result);
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "ADDED";
	}
	
	@SuppressWarnings("unchecked")
	public String updatePatientGeneralInfo(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/users.json");
    	JSONArray jsonObject = (JSONArray) parser.parse(reader);
        
		FileWriter writer = new FileWriter("./jsonFiles/users.json");
		
		JSONObject data = (JSONObject) parser.parse(jsonString);
		JSONObject result = null;
		for (Object aData : jsonObject) {
        	JSONObject parseData = (JSONObject) aData;
        	if (parseData.get("userName").equals(data.get("userName")) && parseData.get("password").equals(data.get("password"))){
        		result = parseData;
        	}
		}
		jsonObject.remove(result);
		jsonObject.add(data);
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "ADDED";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String userLogin(String jsonString) throws FileNotFoundException, IOException {
		JSONParser parser = new JSONParser();
        try {
        	FileReader reader = new FileReader("./jsonFiles/users.json");
        	JSONArray jsonObject = (JSONArray) parser.parse(reader);
            JSONObject data = (JSONObject) parser.parse(jsonString);
            for (Object aData : jsonObject) {
            	JSONObject parseData = (JSONObject) aData;
            	if (parseData.get("userName").equals(data.get("userName")) && parseData.get("password").equals(data.get("password"))){
            		return parseData.toJSONString();
            	}
            }
        } catch (ParseException e) {
        	return "ERROR";
        }
        return "ERROR";
	}
	
	public String getUserByUserName(String jsonString) throws IOException {
		JSONParser parser = new JSONParser();
        try {
        	FileReader reader = new FileReader("./jsonFiles/users.json");
        	JSONArray jsonObject = (JSONArray) parser.parse(reader);
            JSONObject data = (JSONObject) parser.parse(jsonString);
            for (Object aData : jsonObject) {
            	JSONObject parseData = (JSONObject) aData;
            	if (parseData.get("userName").equals(data.get("patient"))){
            		return parseData.toJSONString();
            	}
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "ERROR";
	}
	
	@SuppressWarnings("unchecked")
	public String getAppointments(String jsonString) throws IOException {
		JSONParser parser = new JSONParser();
		JSONArray appointments = new JSONArray();
        try {
        	FileReader reader = new FileReader("./jsonFiles/appointments.json");
        	JSONArray jsonObject = (JSONArray) parser.parse(reader);
            JSONObject data = (JSONObject) parser.parse(jsonString);
            for (Object aData : jsonObject) {
            	JSONObject parseData = (JSONObject) aData;
            	if (parseData.get("patient").equals(data.get("patient"))){
            		appointments.add(parseData);
            	}
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return appointments.toJSONString();
	}

	@SuppressWarnings("deprecation")
	public void run() {
		Context context = ZMQ.context(1);
		Socket socket = context.socket(ZMQ.REP);
		socket.bind("tcp://127.0.0.1:5562");
		
        while (!Thread.currentThread().isInterrupted()) {

            String reply = socket.recvStr(0);
            String[] message = reply.split(",", 2);
            
            String reqest = message[0];
            
            System.out.println("Server is running for " + reqest);
            String data = message[1];
            String result = "";
            if (reqest.equals("BOOK_APPOINTMENT")) {
            	try {
					result = this.bookAppointment(data);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            if (reqest.equals("CANCLE_APPOINTMENT")) {
            	try {
					result = this.cancleAppointment(data);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            if (reqest.equals("USER_LOGIN")) {
				try {
					result = this.userLogin(data);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
            }
            if (reqest.equals("GET_APPOINTMENTS")) {
            	try {
            		result = this.getAppointments(data);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            if (reqest.equals("GET_USER_BY_USERNAME")) {
            	try {
            		result = this.getUserByUserName(data);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            
            if (reqest.equals("UPDATE_GENERAL_INFORMATION")) {
            	try {
            		result = this.updatePatientGeneralInfo(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
            socket.send(result);
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
