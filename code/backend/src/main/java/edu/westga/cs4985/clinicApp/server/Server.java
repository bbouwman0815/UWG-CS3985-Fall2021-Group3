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

/**
 * The server class
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class Server extends Thread {

	/**
	 * Write booked appointment to json file
	 * 
	 * @param jsonString the appointment's json string
	 * @return "Added" if appointment's json string added to json file
	 * @throws IOException    the IO exception
	 * @throws ParseException the parse exception
	 */
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

	/**
	 * Remove booked appointment from json file
	 * 
	 * @param jsonString the appointment's json string
	 * @return "Removed" if appointment's json string removed from json file
	 * @throws IOException    the IO exception
	 * @throws ParseException the parse exception
	 */
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
			if (parseData.get("medicalPersonnel").equals(data.get("medicalPersonnel"))
					&& parseData.get("patient").equals(data.get("patient"))
					&& parseData.get("date").equals(data.get("date"))) {
				result = parseData;
			}
		}
		jsonObject.remove(result);
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "Removed";
	}

	/**
	 * Update user's general information to json file
	 * 
	 * @param jsonString user's general information json string
	 * @return "Added" if user's general information json string added to json file
	 * @throws IOException    the IO exception
	 * @throws ParseException the parse exception
	 */
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
			if (parseData.get("userName").equals(data.get("userName"))
					&& parseData.get("password").equals(data.get("password"))) {
				result = parseData;
			}
		}
		jsonObject.remove(result);
		jsonObject.add(data);
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "Updated";
	}

	/**
	 * Get user's json string when user login
	 * 
	 * @param jsonString the user's login json string
	 * @return the user's json string if login information is valid; otherwise,
	 *         "EROOR"
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException           the IO exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String userLogin(String jsonString) throws FileNotFoundException, IOException {
		JSONParser parser = new JSONParser();
		try {
			FileReader reader = new FileReader("./jsonFiles/users.json");
			JSONArray jsonObject = (JSONArray) parser.parse(reader);
			JSONObject data = (JSONObject) parser.parse(jsonString);
			for (Object aData : jsonObject) {
				JSONObject parseData = (JSONObject) aData;
				if (parseData.get("userName").equals(data.get("userName"))
						&& parseData.get("password").equals(data.get("password"))) {
					return parseData.toJSONString();
				}
			}
		} catch (ParseException e) {
			return "ERROR";
		}
		return "ERROR";
	}

	/**
	 * Get user json string by given json string as user name
	 * 
	 * @param jsonString the json string that contains the user name
	 * @return the user json string associated with given user name json string
	 * @throws IOException the IO exception
	 */
	public String getUserByUserName(String jsonString) throws IOException {
		JSONParser parser = new JSONParser();
		try {
			FileReader reader = new FileReader("./jsonFiles/users.json");
			JSONArray jsonObject = (JSONArray) parser.parse(reader);
			JSONObject data = (JSONObject) parser.parse(jsonString);
			for (Object aData : jsonObject) {
				JSONObject parseData = (JSONObject) aData;
				if (parseData.get("userName").equals(data.get("patient"))) {
					return parseData.toJSONString();
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "ERROR";
	}

	/**
	 * Get appointment list by given user name json string
	 * 
	 * @param jsonString the json string of user name
	 * @return the appointment list json string associated with the json stirng of
	 *         user name
	 * @throws IOException the IO exception
	 */
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
				if (parseData.get("patient").equals(data.get("patient"))) {
					appointments.add(parseData);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return appointments.toJSONString();
	}

	/**
	 * Adds the patient user.
	 *
	 * @param jsonString the json string
	 * @return the string
	 * @throws IOException    Signals that an I/O exception has occurred.
	 * @throws ParseException
	 */
	@SuppressWarnings({ "unchecked" })
	public String addPatientUser(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/users.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);

		FileWriter writer = new FileWriter("./jsonFiles/users.json");

		JSONObject data = (JSONObject) parser.parse(jsonString);

		jsonObject.add(data);
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "ADDED";
	}

	/*
	 * Run the server
	 */
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
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (reqest.equals("CANCLE_APPOINTMENT")) {
				try {
					result = this.cancleAppointment(data);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
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

			if (reqest.equals("ADD_PATIENT")) {
				try {
					result = this.addPatientUser(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			if (reqest.equals("UPDATE_GENERAL_INFORMATION")) {
				try {
					result = this.updatePatientGeneralInfo(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			socket.send(result);
		}

		socket.close();
		context.term();

	}
}
