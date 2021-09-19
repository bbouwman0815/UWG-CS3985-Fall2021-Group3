package edu.westga.cs4985.clinicApp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.utils.DataReader;
import edu.westga.cs4985.clinicApp.utils.DataWriter;

/**
 * The UserManager class.
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class UserManager {

	public static UserManager userManager;
	public Communicator communicator;

	/**
	 * Initialize constructor
	 */
	public UserManager() {
		this.communicator = new Communicator();
	}

	/**
	 * Instantiates a new user manager.
	 *
	 * @param communicator the communicator
	 */
	public UserManager(Communicator communicator) {
		this.communicator = communicator;
	}

	/**
	 * Login the user to system
	 * 
	 * @param username the user's user name
	 * @param password the user's password
	 * @return the verified user
	 * @throws ParseException 
	 */
	public User login(String username, String password) throws ParseException {
		String requestData = DataWriter.getUserLoginInfo(username, password);
		String reply = this.communicator.request(RequestType.USER_LOGIN, requestData);
		if (reply.equals("ERROR")) {
			return null;
		}
		return DataReader.convertToUser(reply);
	}

	/**
	 * Set the user manager as user manager
	 * 
	 * @param userManager the user manager
	 */
	public static void setUserManager(UserManager userManager) {
		UserManager.userManager = userManager;
	}

	/**
	 * Get the user by user name
	 * 
	 * @param userName the user's user name
	 * @return the user associated with the user name
	 * @throws ParseException 
	 */
	public User getUserByUserName(String userName) throws ParseException {
		String request = DataWriter.getUserName(userName);
		String reply = this.communicator.request(RequestType.GET_USER_BY_USERNAME, request);
		if (reply.equals("ERROR")) {
			return null;
		}
		return DataReader.convertToUser(reply);
	}

	/**
	 * Get the appointment list associated with the given user name
	 * 
	 * @param userName the user name
	 * @return the appointment list associated with the given user name
	 * @throws ParseException 
	 */
	public List<Appointment> getAppointments(String userName) throws ParseException {
		String request = DataWriter.getUserName(userName);
		String reply = this.communicator.request(RequestType.GET_APPOINTMENTS, request);
		if (reply.equals("ERROR")) {
			return new ArrayList<Appointment>();
		}
		return this.convertToAppointments(reply);
	}

	/**
	 * Book an appointment
	 * 
	 * @param appointment the appointment to book
	 * @return true if appointment is booked successful; otherwise false
	 */
	public boolean bookAppointment(Appointment appointment) {
		String requestData = DataWriter.writeAppointmentInfo(appointment);
		String reply = this.communicator.request(RequestType.BOOK_APPOINTMENT, requestData);
		if (reply.equals("ERROR")) {
			return false;
		}
		return true;
	}

	/**
	 * Adds the patient.
	 *
	 * @param patient the patient to add
	 * @return true if successful, false otherwise
	 */
	public boolean addPatient(Patient patient) {
		String requestData = DataWriter.updatePatientGeneralInfor(patient);
		String reply = this.communicator.request(RequestType.ADD_PATIENT, requestData);
		if (reply.equals("ERROR")) {
			return false;
		}
		return true;
	}

	/**
	 * Adds the medical condition.
	 *
	 * @param medicalCondition the medical condition
	 * @return true, if successful
	 */
	public boolean addMedicalCondition(MedicalCondition medicalCondition) {
		String requestData = DataWriter.writeMedicalConditionInfo(medicalCondition);
		String reply = this.communicator.request(RequestType.ADD_MEDICAL_CONDITION, requestData);
		if (reply.equals("ERROR")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Removes the medical condition.
	 *
	 * @param medicalCondition the medical condition
	 * @return true, if successful
	 */
	public boolean removeMedicalCondition(MedicalCondition medicalCondition) {
		String requestData = DataWriter.writeMedicalConditionInfo(medicalCondition);
		String reply = this.communicator.request(RequestType.REMOVE_MEDICAL_CONDITION, requestData);
		if (reply.equals("ERROR")) {
			return false;
		}
		return true;
	}

	/**
	 * Update patient's general information
	 * 
	 * @param patient the patient
	 * @return true if patient's general information is booked successful; otherwise
	 *         false
	 */
	public boolean updatePatientGeneralInfo(Patient patient) {
		String requestData = DataWriter.updatePatientGeneralInfor(patient);
		String reply = this.communicator.request(RequestType.UPDATE_GENERAL_INFORMATION, requestData);
		if (reply.equals("ERROR")) {
			return false;
		}
		return true;
	}

	/**
	 * Cancel an appointment
	 * 
	 * @param appointment the appointment to book
	 * @return true if appointment is canceled successful; otherwise false
	 */
	public boolean cancelAppointment(Appointment appointment) {
		String requestData = DataWriter.writeAppointmentInfo(appointment);
		String reply = this.communicator.request(RequestType.CANCLE_APPOINTMENT, requestData);
		if (reply.equals("ERROR")) {
			return false;
		}
		return true;
	}

	/**
	 * Convert json string to list of appointments
	 * 
	 * @param reply the appointments json string
	 * 
	 * @return the appointments list associated with the json string
	 * @throws ParseException 
	 */
	public List<Appointment> convertToAppointments(String reply) throws ParseException {
		List<Appointment> appointments = new ArrayList<Appointment>();
		JSONParser parser = new JSONParser();
		JSONArray data = (JSONArray) parser.parse(reply.toString());
		for (Object aData : data) {

			JSONObject parseData = (JSONObject) aData;
			LocalDateTime datetime = LocalDateTime.parse(parseData.get("date").toString());
			String notes = (String) parseData.get("notes");
			Patient patient = (Patient) this.getUserByUserName(parseData.get("patient").toString());
			String medicalPersonnel = (String) parseData.get("medicalPersonnel");
			String location = (String) parseData.get("location");

			Appointment appointment = new Appointment(datetime, patient, medicalPersonnel, location, notes);
			appointments.add(appointment);
		}
		return appointments;
	}

}
