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
	
	/**
	 * Convert json string to user
	 * 
	 * @param reply the user json string
	 * 
	 * @return the user associated with the json string
	 * @throws org.json.simple.parser.ParseException 
	 */
	public static User convertToUser(String reply) throws org.json.simple.parser.ParseException {
		JSONObject json = null;
		json = (JSONObject) (new JSONParser()).parse(reply);
		return convertToUser(json);
	}
	
	/**
	 * Convert json string to user
	 * 
	 * @param reply the user json string
	 * 
	 * @return the user associated with the json string
	 */
	public static User convertToUser(JSONObject json) {
		User user = null;
		String type = ((String) json.get("type"));
		if (type.equals("PATIENT")) {
			Patient patient = new Patient((String) json.get("firstName"), (String) json.get("lastName"), (String) json.get("gender"), (String) json.get("dateOfBirth"), (String) json.get("address1"),
					(String) json.get("address2"), (String) json.get("city"), (String) json.get("state"), (String) json.get("country"), (String) json.get("race"),(String) json.get("ethnicty"),
					(String) json.get("phoneNumber"), (String) json.get("email"), (String) json.get("insurance"), (String) json.get("userName"), (String) json.get("password"));
			patient.setCaregiver((String) json.get("caregiver"));
			user = patient;
		}
		return user;
	}

}
