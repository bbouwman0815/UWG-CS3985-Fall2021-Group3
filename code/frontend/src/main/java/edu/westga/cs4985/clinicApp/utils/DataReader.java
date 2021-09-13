package edu.westga.cs4985.clinicApp.utils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.model.UserManager;

/**
 * Read data from json file
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class DataReader {
	
	public static User convertToUser(String reply) {
		JSONObject json = null;
		try {
			json = (JSONObject) (new JSONParser()).parse(reply);
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		return convertToUser(json);
	}
	
	public static List<Appointment> convertToAppointments(String reply) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		List<Appointment> appointments = new ArrayList<Appointment>();
		JSONParser parser = new JSONParser();
		try {
			
			JSONArray data = (JSONArray) parser.parse(reply.toString());
            for (Object aData : data) {
            	
            	JSONObject parseData = (JSONObject) aData;
            	LocalDateTime datetime = LocalDateTime.parse(parseData.get("date").toString());
            	String notes = (String) parseData.get("notes");
            	Patient patient = (Patient) UserManager.userManager.getUserByUserName(parseData.get("patient").toString());
            	String medicalPersonnel = (String) parseData.get("medicalPersonnel");
            	String location = (String) parseData.get("location");
            	
            	Appointment  appointment = new Appointment(datetime, patient, medicalPersonnel, location, notes);
            	appointments.add(appointment);
            }
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		return appointments;
	}
	
	public static User convertToUser(JSONObject json) {
		User user = null;
		String type = ((String) json.get("type"));
		if (type.equals("PATIENT")) {
			Patient patient = new Patient((String) json.get("firstName"), (String) json.get("lastName"), (String) json.get("gender"), (String) json.get("dateOfBirth"), (String) json.get("address1"),
					(String) json.get("address2"), (String) json.get("city"), (String) json.get("state"), (String) json.get("country"), (String) json.get("race"),(String) json.get("ethnicty"),
					(String) json.get("phoneNumber"), (String) json.get("phoneNumber"), (String) json.get("insurance"), (String) json.get("userName"), (String) json.get("password"));
			user = patient;
		}
		return user;
	}

}
