package edu.westga.cs4985.clinicApp.utils;

import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.MedicalCondition;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;

/**
 * Write data to json file
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class DataWriter {
	
	/**
	 * Write the user's name user name and password as json 
	 * 
	 * @param username the user's user name
	 * @param password the user's password
	 * @return the json that contains the user's user name and password
	 */
	@SuppressWarnings("unchecked")
	public static String getUserLoginInfo(String username, String password) {
		JSONObject json = new JSONObject();
		json.put("userName", username);
		json.put("password", password);
		return json.toJSONString();
	}
	
	/**
	 * Write the user's name user name as json 
	 * 
	 * @param username the user's user name
	 * @return the json that contains the user's user name 
	 */
	@SuppressWarnings("unchecked")
	public static String getUserName(String username) {
		JSONObject json = new JSONObject();
		json.put("patient", username);
		return json.toJSONString();
	}
	
	/**
	 * Write the user's information as json
	 * 
	 * @param user the user
	 * @return the json that contains the user's information
	 */
	@SuppressWarnings("unchecked")
	public static String getUserGeneralInfo(User user) {
		JSONObject json = new JSONObject();
		json.put("userName", user.getUsername());
		json.put("password", user.getPassword());
		return json.toJSONString();
	}
	
	
	/**
	 * Write the patient's general information as json
	 *  
	 * @param patient the patient
	 * @return the json that contains patient's general information
	 */
	@SuppressWarnings("unchecked")
	public static String updatePatientGeneralInfor(Patient patient) {
		JSONObject json = new JSONObject();
		json.put("type", "PATIENT");
		json.put("userName", patient.getUsername());
		json.put("password", patient.getPassword());
		json.put("firstName", patient.getFirstName());
		json.put("lastName", patient.getLastName());
		json.put("gender", patient.getGender());
		json.put("dateOfBirth", patient.getDateOfBirth());
		json.put("address1", patient.getAddress1());
		json.put("address2", patient.getAddress2());
		json.put("city", patient.getCity());
		json.put("state", patient.getState());
		json.put("country", patient.getCountry());
		json.put("race", patient.getRace());
		json.put("ethnicty", patient.getEthnicity());
		json.put("phoneNumber", patient.getPhoneNumber());
		json.put("email", patient.getEmail());
		json.put("insurance", patient.getInsurance());
		json.put("caregiver", patient.getCaregiver());
		return json.toJSONString();
	}

	/**
	 * Write the appointment as json
	 *  
	 * @param appointment the appointment
	 * @return the json that contains appointment information
	 */
	@SuppressWarnings("unchecked")
	public static String writeAppointmentInfo(Appointment appointment) {
		if (appointment == null) {
			return "Error";
		}
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", appointment.getMedicalPersonnel());
		json.put("patient", appointment.getPatient().getUsername());
		json.put("date", appointment.getDateTime().toString());
		json.put("location", appointment.getLocation());
		json.put("notes", appointment.getNotes());
		return json.toJSONString();
		
	}
	
	/**
	 * Write medical condition info.
	 *
	 * @param medicalCondition the medical condition
	 * @return the string
	 */
	@SuppressWarnings("unchecked")
	public static String writeMedicalConditionInfo(MedicalCondition medicalCondition) {
		if (medicalCondition == null) {
			return "Error";
		}
		JSONObject json = new JSONObject();
		json.put("patient", medicalCondition.getPatient().getUsername());
		json.put("name", medicalCondition.getName());
		json.put("diagnosisDate", medicalCondition.getDiagnosisDate());
		json.put("terminationDate", medicalCondition.getTerminationDate());
		json.put("notes", medicalCondition.getNotes());
		return json.toJSONString();
		
	}
}
