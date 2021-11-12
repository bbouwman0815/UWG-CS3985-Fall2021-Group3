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
		HashPassword hash = new HashPassword();
		String requestData = DataWriter.getUserLoginInfo(username, hash.generateHash(password));
		String reply = this.communicator.request(RequestType.USER_LOGIN, requestData);
		if (reply.equals("ERROR")) {
			return null;
		}
		return this.convertToUser(reply);
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
		return this.convertToUser(reply);
	}
	
	/**
	 * Get the user by user name
	 * 
	 * @param userName the user's user name
	 * @return the user associated with the user name
	 * @throws ParseException
	 */
	public User getCaregiverByUserName(String userName) throws ParseException {
		String request = DataWriter.getCaregiverUserName(userName);
		String reply = this.communicator.request(RequestType.GET_CAREGIVER_BY_USER_NAME, request);
		if (reply.equals("ERROR")) {
			return null;
		}
		return this.convertToUser(reply);
	}
	
	/**
	 * Get the user by user name
	 * 
	 * @param userName the user's user name
	 * @return the user associated with the user name
	 * @throws ParseException 
	 */
	public User getUserByMedicalPersonnelUserName(String userName) throws ParseException {
		String request = DataWriter.getUserByMedicalPersonnelName(userName);
		String reply = this.communicator.request(RequestType.GET_USER_BY_MEDICAL_PERSONNEL_USERNAME, request);
		if (reply.equals("ERROR")) {
			return null;
		}
		return this.convertToUser(reply);
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
	 * Get the appointment list associated with the given user name
	 * 
	 * @param userName the user name
	 * @return the appointment list associated with the given user name
	 * @throws ParseException 
	 */
	public List<Appointment> getAppointmentsForMedicalPersonnel(String userName) throws ParseException {
		String request = DataWriter.getUserByMedicalPersonnelName(userName);
		String reply = this.communicator.request(RequestType.GET_APPOINTMENTS_FOR_MEDICAL_PEROSNNEL, request);
		if (reply.equals("ERROR")) {
			return new ArrayList<Appointment>();
		}
		return this.convertToAppointments(reply);
	}
	
	/**
	 * Get the appointment list associated with the given user name
	 * 
	 * @param userName the user name
	 * @return the appointment list associated with the given user name
	 * @throws ParseException 
	 */
	public List<LocalDateTime> getAvailabilities(String userName) throws ParseException {
		String request = DataWriter.getUserByMedicalPersonnelName(userName);
		String reply = this.communicator.request(RequestType.GET_AVAILABILITIES, request);
		if (reply.equals("ERROR")) {
			return new ArrayList<LocalDateTime>();
		}
		return this.convertToAvailabilities(reply);
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
	 * Update an appointment
	 * 
	 * @param appointment the appointment to Update
	 * @return true if appointment is updated successful; otherwise false
	 */
	public boolean updateAppointment(Appointment appointment) {
		String requestData = DataWriter.writeAppointmentInfo(appointment);
		String reply = this.communicator.request(RequestType.UPDATE_APPOINTMENT, requestData);
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
	
	public boolean addMedicalPersonnel(MedicalPersonnel medicalPersonnel) {
		String requestData = DataWriter.writeMedicalPersonnelInfo(medicalPersonnel);
		String reply = this.communicator.request(RequestType.ADD_MEDICAL_PERSONNEL, requestData);
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
	 * Gets the medical conditions.
	 *
	 * @param userName the user name
	 * @return the medical conditions
	 * @throws ParseException the parse exception
	 */
	public List<MedicalCondition> getMedicalConditions(String userName) throws ParseException {
		String request = DataWriter.getUserName(userName);
		String reply = this.communicator.request(RequestType.GET_MEDICAL_CONDITIONS, request);
		if (reply.equals("ERROR")) {
			return new ArrayList<MedicalCondition>();
		}
		return this.convertToMedicalConditions(reply);
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
	 * Update medical personenl's availabilities
	 * 
	 * @param person medical personenl's availabilities
	 * @return true if medical personenl's availabilities is updated successful; otherwise
	 *         false
	 */
	public boolean updateMedicalPersonnelAvaiabilities(MedicalPersonnel person, List<LocalDateTime> availabilityList) {
		String requestData = DataWriter.updateMedicalPersonnelAvailabilities(person, availabilityList);
		String reply = this.communicator.request(RequestType.UPDATE_AVAILABILITY, requestData);
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
	 * Gets the all patients.
	 *
	 * @return the all patients
	 * @throws ParseException the parse exception
	 */
	public List<Patient> getAllPatients() throws ParseException {
		String request = "GET_ALL_PATIENTS";
		String reply = this.communicator.request(RequestType.GET_ALL_PATIENTS, request);
		return this.convertToPatient(reply);
	}
	
	/**
	 * Gets the all caregivers.
	 *
	 * @return the all caregivers
	 * @throws ParseException the parse exception
	 */
	public List<Caregiver> getAllCaregivers() throws ParseException {
		String request = "GET_ALL_CAREGIVERS";
		String reply = this.communicator.request(RequestType.GET_ALL_CAREGIVERS, request);
		return this.convertToCaregiver(reply);
	}
	
	/**
	 * Gets the all MedicalPersonnels.
	 *
	 * @return the all MedicalPersonnels
	 * @throws ParseException the parse exception
	 */
	public List<MedicalPersonnel> getAllMedicalPersonnels(String zipcode) throws ParseException {
		String request = DataWriter.getMedicalPersonnels(zipcode);
		String reply = this.communicator.request(RequestType.GET_ALL_MEDICAL_PERSONNELS, request);
		if (reply.equals("ERROR")) {
			return new ArrayList<MedicalPersonnel>();
		}
		return this.convertToMedicalPersonnel(reply);
	}
	
	/**
	 * Gets the patients for medical personnel.
	 *
	 * @param medicalPersonnel the medical personnel
	 * @return the patients for medical personnel
	 * @throws ParseException the parse exception
	 */
	public List<Patient> getPatientsForMedicalPersonnel(String medicalPersonnel) throws ParseException {
		String request = DataWriter.getUserByMedicalPersonnelName(medicalPersonnel);
		String reply = this.communicator.request(RequestType.GET_MEDICAL_PERSONNELS_PATIENTS, request);
		if (reply.equals("ERROR")) {
			return new ArrayList<Patient>();
		}
		return this.convertToPatients(reply);
	}
	
	/**
	 * Gets the patients for Caregiver.
	 *
	 * @param medicalPersonnel the medical personnel
	 * @return the patients for medical personnel
	 * @throws ParseException the parse exception
	 */
	public List<Patient> getPatientsForCaregiver(String caregiver) throws ParseException {
		String request = DataWriter.getCaregiverUserName(caregiver);
		String reply = this.communicator.request(RequestType.GET_CAREGIVER_PATIENTS, request);
		if (reply.equals("ERROR")) {
			return new ArrayList<Patient>();
		}
		return this.convertToPatients(reply);
	}
	
	/**
	 * Removes the medical personnels patient.
	 *
	 * @param username the username
	 * @param username2 the username 2
	 * @return true, if successful
	 */
	public boolean updateMedicalPersonnelsPatients(MedicalPersonnel medicalPersonnel,  List<Patient> patients) {
		String request = DataWriter.updateMedicalPersonnelsPatients(medicalPersonnel, patients);
		String reply = this.communicator.request(RequestType.UPDATE_MEDICAL_PERSONNELS_PATIENTS, request);
		if (reply.equals("ERROR")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Removes the caregiver patient.
	 *
	 * @param username the username
	 * @return true, if successful
	 */
	public boolean updateCaregiverPatients(Caregiver caregiver,  List<Patient> patients) {
		String request = DataWriter.updateCaregiverPatients(caregiver, patients);
		String reply = this.communicator.request(RequestType.UPDATE_CAREGIVER_PATIENTS, request);
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
			MedicalPersonnel medicalPersonnel = (MedicalPersonnel) this.getUserByMedicalPersonnelUserName(parseData.get("medicalPersonnel").toString());
			String location = (String) parseData.get("location");

			Appointment appointment = new Appointment(datetime, patient, medicalPersonnel, location, notes);
			appointments.add(appointment);
		}
		return appointments;
	}
	
	/**
	 * Convert json string to list of availabilities
	 * 
	 * @param reply the availabilities json string
	 * 
	 * @return the availabilities list associated with the json string
	 * @throws ParseException 
	 */
	public List<LocalDateTime> convertToAvailabilities(String reply) throws ParseException {
		List<LocalDateTime> availabilities = new ArrayList<LocalDateTime>();
		JSONParser parser = new JSONParser();
		JSONArray data = (JSONArray) parser.parse(reply.toString());
		for (Object aData : data) {
			LocalDateTime datetime = LocalDateTime.parse(aData.toString());
			availabilities.add(datetime);
		}
		return availabilities;
	}
	

	/**
	 * Convert to medical conditions.
	 *
	 * @param reply the reply
	 * @return the list
	 * @throws ParseException the parse exception
	 */
	public List<MedicalCondition> convertToMedicalConditions(String reply) throws ParseException {
		List<MedicalCondition> medicalConditions = new ArrayList<MedicalCondition>();
		JSONParser parser = new JSONParser();
		JSONArray data = (JSONArray) parser.parse(reply.toString());
		for (Object aData : data) {
			JSONObject parseData = (JSONObject) aData;
			String name = (String) parseData.get("name");
			Patient patient = (Patient) this.getUserByUserName(parseData.get("patient").toString());
			String diagnosisDate = (String) parseData.get("diagnosisDate");
			String terminationDate = (String) parseData.get("terminationDate");
			String notes = (String) parseData.get("notes");
			MedicalCondition medicalCondition = new MedicalCondition(patient, name, diagnosisDate, terminationDate,
					notes);
			medicalConditions.add(medicalCondition);
		}
		return medicalConditions;
	}

	private List<Patient> convertToPatient(String reply) throws ParseException {
		List<Patient> patients = new ArrayList<Patient>();
		JSONParser parser = new JSONParser();
		JSONArray data = (JSONArray) parser.parse(reply.toString());
		for (Object aData : data) {
			JSONObject parseData = (JSONObject) aData;
			String firstName = (String) parseData.get("firstName");
			String lastName = (String) parseData.get("lastName");
			String country = (String) parseData.get("country");
			String gender = (String) parseData.get("gender");
			String race = (String) parseData.get("race");
			String address1 = (String) parseData.get("address1");
			String address2 = (String) parseData.get("address2");
			String city = (String) parseData.get("city");
			String dateOfBirth = (String) parseData.get("dateOfBirth");
			String userName = (String) parseData.get("userName");
			String password = (String) parseData.get("password");
			String phoneNumber = (String) parseData.get("phoneNumber");
			String ethnicity = (String) parseData.get("ethnicty");
			String state = (String) parseData.get("state");
			String email = (String) parseData.get("email");
			String insurance = (String) parseData.get("insurance");

			Patient patient = new Patient(firstName, lastName, gender, dateOfBirth, address1, address2, city, state,
					country, race, ethnicity, phoneNumber, email, insurance, userName, password);
			patient.setCaregiver((Caregiver)this.getCaregiverByUserName((String) parseData.get("caregiver")));
			patients.add(patient);

		}
		return patients;
	}
	
	private List<Patient> convertToPatients(String reply) throws ParseException {
		List<Patient> patients = new ArrayList<Patient>();
		JSONParser parser = new JSONParser();
		JSONArray data = (JSONArray) parser.parse(reply.toString());
		for (Object aData : data) {
			Patient patient = (Patient) UserManager.userManager.getUserByUserName((String) aData);
			patients.add(patient);
		}
		return patients;
	}
	
	private List<MedicalPersonnel> convertToMedicalPersonnel(String reply) throws ParseException {
		List<MedicalPersonnel> medicalPersonnels = new ArrayList<MedicalPersonnel>();
		JSONParser parser = new JSONParser();
		JSONArray data = (JSONArray) parser.parse(reply.toString());
		for (Object aData : data) {
			JSONObject parseData = (JSONObject) aData;
			String firstName = (String) parseData.get("firstName");
			String lastName = (String) parseData.get("lastName");
			String country = (String) parseData.get("country");
			String gender = (String) parseData.get("gender");
			String race = (String) parseData.get("race");
			String address1 = (String) parseData.get("address1");
			String address2 = (String) parseData.get("address2");
			String city = (String) parseData.get("city");
			String dateOfBirth = (String) parseData.get("dateOfBirth");
			String userName = (String) parseData.get("userName");
			String password = (String) parseData.get("password");
			String phoneNumber = (String) parseData.get("phoneNumber");
			String ethnicity = (String) parseData.get("ethnicty");
			String state = (String) parseData.get("state");
			String email = (String) parseData.get("email");
			String zipcode = (String) parseData.get("zipcode");

			MedicalPersonnel medicalPersonnel = new MedicalPersonnel(firstName, lastName, gender, dateOfBirth, address1, address2, city, state,
					country, race, ethnicity, phoneNumber, email, userName, password, zipcode);
			medicalPersonnels.add(medicalPersonnel);

		}
		return medicalPersonnels;
	}
	
	private List<Caregiver> convertToCaregiver(String reply) throws ParseException {
		List<Caregiver> caregivers = new ArrayList<Caregiver>();
		JSONParser parser = new JSONParser();
		JSONArray data = (JSONArray) parser.parse(reply.toString());
		for (Object aData : data) {
			JSONObject parseData = (JSONObject) aData;
			String firstName = (String) parseData.get("firstName");
			String lastName = (String) parseData.get("lastName");
			String country = (String) parseData.get("country");
			String gender = (String) parseData.get("gender");
			String race = (String) parseData.get("race");
			String address1 = (String) parseData.get("address1");
			String address2 = (String) parseData.get("address2");
			String city = (String) parseData.get("city");
			String dateOfBirth = (String) parseData.get("dateOfBirth");
			String userName = (String) parseData.get("userName");
			String password = (String) parseData.get("password");
			String phoneNumber = (String) parseData.get("phoneNumber");
			String ethnicity = (String) parseData.get("ethnicty");
			String state = (String) parseData.get("state");
			String email = (String) parseData.get("email");

			Caregiver caregiver = new Caregiver(firstName, lastName, gender, dateOfBirth, address1, address2, city, state,
					country, race, ethnicity, phoneNumber, email, userName, password);
			caregivers.add(caregiver);

		}
		return caregivers;
	}
	
	/**
	 * Convert json string to user
	 * 
	 * @param reply the user json string
	 * 
	 * @return the user associated with the json string
	 * @throws org.json.simple.parser.ParseException 
	 */
	public User convertToUser(String reply) throws org.json.simple.parser.ParseException {
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
	 * @throws ParseException 
	 */
	public User convertToUser(JSONObject json) throws ParseException {
		User user = null;
		String type = ((String) json.get("type"));
		if (type.equals("PATIENT")) {
			Patient patient = new Patient((String) json.get("firstName"), (String) json.get("lastName"), (String) json.get("gender"), (String) json.get("dateOfBirth"), (String) json.get("address1"),
					(String) json.get("address2"), (String) json.get("city"), (String) json.get("state"), (String) json.get("country"), (String) json.get("race"),(String) json.get("ethnicty"),
					(String) json.get("phoneNumber"), (String) json.get("email"), (String) json.get("insurance"), (String) json.get("userName"), (String) json.get("password"));
			patient.setCaregiver((Caregiver)this.getCaregiverByUserName((String) json.get("caregiver")));
			user = patient;
		}
		if (type.equals("MedicalPersonnel")) {
			MedicalPersonnel medicalPersonnel = new MedicalPersonnel((String) json.get("firstName"), (String) json.get("lastName"), (String) json.get("gender"), (String) json.get("dateOfBirth"), (String) json.get("address1"),
					(String) json.get("address2"), (String) json.get("city"), (String) json.get("state"), (String) json.get("country"), (String) json.get("race"),(String) json.get("ethnicty"),
					(String) json.get("phoneNumber"), (String) json.get("email"), (String) json.get("userName"), (String) json.get("password"), (String) json.get("zipcode"));
			user = medicalPersonnel;
		}
		if (type.equals("Caregiver")) {
			Caregiver caregiver = new Caregiver((String) json.get("firstName"), (String) json.get("lastName"), (String) json.get("gender"), (String) json.get("dateOfBirth"), (String) json.get("address1"),
					(String) json.get("address2"), (String) json.get("city"), (String) json.get("state"), (String) json.get("country"), (String) json.get("race"),(String) json.get("ethnicty"),
					(String) json.get("phoneNumber"), (String) json.get("email"), (String) json.get("userName"), (String) json.get("password"));
			user = caregiver;
		}
		return user;
	}

}
