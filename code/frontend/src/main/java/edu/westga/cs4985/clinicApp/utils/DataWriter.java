package edu.westga.cs4985.clinicApp.utils;

import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.Caregiver;
import edu.westga.cs4985.clinicApp.model.MedicalCondition;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.Patient;
import java.time.LocalDateTime;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Write data to json file
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class DataWriter {

	private static final String ERROR = "Error";

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
	 * Write the user's name user name as json
	 * 
	 * @param username the user's user name
	 * @return the json that contains the user's user name
	 */
	@SuppressWarnings("unchecked")
	public static String getCaregiverUserName(String username) {
		JSONObject json = new JSONObject();
		json.put("caregiver", username);
		return json.toJSONString();
	}
	
	/**
	 * Write the user's name user name as json 
	 * 
	 * @param username the user's user name
	 * @return the json that contains the user's user name 
	 */
	@SuppressWarnings("unchecked")
	public static String getUserByMedicalPersonnelName(String username) {
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", username);
		return json.toJSONString();
	}

	/**
	 * Write the medical personnel & patients name as json.
	 *
	 * @param mpusername the medical personnels name
	 * @param pusername  the patients name
	 * 
	 * @return the json that contains the user's user names
	 */
	@SuppressWarnings("unchecked")
	public static String getUsersByMedicalPersonnelAndPatient(String mpusername, String pusername) {
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", mpusername);
		json.put("patient", pusername);
		return json.toJSONString();
	}

	/**
	 * Write the user's zipcode as json
	 * 
	 * @param zipcode the user's uzipcode
	 * @return the json that contains the user's zipcode
	 */
	@SuppressWarnings("unchecked")
	public static String getMedicalPersonnels(String zipcode) {
		JSONObject json = new JSONObject();
		json.put("zipcode", zipcode);
		return json.toJSONString();
	}

	/**
	 * Write the patient's general information as json
	 * 
	 * @param person           the person
	 * @param availabilityList the availability of the medical person
	 * @return the json that contains patient's general information
	 */
	@SuppressWarnings("unchecked")
	public static String updateMedicalPersonnelAvailabilities(MedicalPersonnel person,
			List<LocalDateTime> availabilityList) {
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", person.getUsername());
		JSONArray availability = new JSONArray();
		for (LocalDateTime dayTime : availabilityList) {
			availability.add(dayTime.toString());
		}
		json.put("availabilityList", availability);
		return json.toJSONString();
	}

	/**
	 * Update medical personnels patients.
	 *
	 * @param person      the person
	 * @param patientList the patient list
	 * @return the string
	 */
	@SuppressWarnings("unchecked")
	public static String updateMedicalPersonnelsPatients(MedicalPersonnel person, List<Patient> patientList) {
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", person.getUsername());
		JSONArray patients = new JSONArray();
		for (Patient patient : patientList) {
			patients.add(patient.getUsername());
		}
		json.put("patients", patients);
		return json.toJSONString();
	}

	/**
	 * Update Caregiver patients.
	 *
	 * @param person the person
	 * @param patientList the patient list
	 * @return the string
	 */
	@SuppressWarnings("unchecked")
	public static String updateCaregiverPatients(Caregiver person, List<Patient> patientList) {
		JSONObject json = new JSONObject();
		json.put("caregiver", person.getUsername());
		JSONArray patients = new JSONArray();
		for (Patient patient : patientList) {
			patients.add(patient.getUsername());
		}
		json.put("patients", patients);
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
		json.put("caregiver", patient.getCaregiver() == null ? "" : patient.getCaregiver().getUsername());
		return json.toJSONString();
	}

	@SuppressWarnings("unchecked")
	public static String writeMedicalPersonnelInfo(MedicalPersonnel medicalPersonnel) {
		JSONObject json = new JSONObject();
		json.put("type", "MedicalPersonnel");
		json.put("userName", medicalPersonnel.getUsername());
		json.put("password", medicalPersonnel.getPassword());
		json.put("firstName", medicalPersonnel.getFirstName());
		json.put("lastName", medicalPersonnel.getLastName());
		json.put("gender", medicalPersonnel.getGender());
		json.put("dateOfBirth", medicalPersonnel.getDateOfBirth());
		json.put("address1", medicalPersonnel.getAddress1());
		json.put("address2", medicalPersonnel.getAddress2());
		json.put("city", medicalPersonnel.getCity());
		json.put("state", medicalPersonnel.getState());
		json.put("country", medicalPersonnel.getCountry());
		json.put("race", medicalPersonnel.getRace());
		json.put("ethnicty", medicalPersonnel.getEthnicity());
		json.put("phoneNumber", medicalPersonnel.getPhoneNumber());
		json.put("email", medicalPersonnel.getEmail());
		json.put("zipcode", medicalPersonnel.getZipCode());
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
			return ERROR;
		}
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", appointment.getMedicalPersonnel().getUsername());
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
			return ERROR;
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
