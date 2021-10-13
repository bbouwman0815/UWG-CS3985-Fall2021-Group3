package edu.westga.cs4985.clinicApp.viewmodel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.model.UserManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * The view model for patient general information.
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class MedicalPersonnelViewModel {
	
	private ObjectProperty<LocalDateTime> selectedAvailabilityProperty;
	private ListProperty<LocalDateTime> availabilityListProperty;
	private ObjectProperty<Appointment> selectedFutureAppointmentProperty;
	private ObjectProperty<Appointment> selectedPastAppointmentProperty;
	private ListProperty<Appointment> futureAppointmentListProperty;
	private ListProperty<Appointment> pastAppointmentListProperty;
	private List<Appointment> futureppointmentList;
	private StringProperty notesProperty;
	private List<Appointment> pastAppointmentList;
	private List<LocalDateTime> availabilityList;
	private MedicalPersonnel medicalePersonnel;
	
	private ObjectProperty<LocalDateTime> selectedDayProperty;
	private ObjectProperty<String> selectedTimeProperty;

	private ListProperty<Patient> patientsListProperty;
	private ListProperty<Patient> allPatientsListProperty;

	private ObjectProperty<Patient> selectedPatientProperty;

	private List<Patient> patients;
	private List<Patient> allPatients;
	
	/**
	 * Create view model for patient general information.
	 * 
	 * @precondition none
	 * 
	 * @postcondition none
	 */
	public MedicalPersonnelViewModel() {
		this.medicalePersonnel = (MedicalPersonnel) User.user;
		this.selectedFutureAppointmentProperty = new SimpleObjectProperty<Appointment>();
		this.selectedPastAppointmentProperty = new SimpleObjectProperty<Appointment>();
		this.selectedAvailabilityProperty = new SimpleObjectProperty<LocalDateTime>();
		this.futureAppointmentListProperty = new SimpleListProperty<Appointment>();
		this.pastAppointmentListProperty = new SimpleListProperty<Appointment>();
		this.availabilityListProperty = new SimpleListProperty<LocalDateTime>();
		
		this.selectedDayProperty = new SimpleObjectProperty<LocalDateTime>();
		this.selectedTimeProperty = new SimpleObjectProperty<String>();
		this.notesProperty = new SimpleStringProperty("");
		this.futureppointmentList = new ArrayList<Appointment>();
		this.pastAppointmentList = new ArrayList<Appointment>();
		this.availabilityList = new ArrayList<LocalDateTime>();
		
		this.patients = new ArrayList<Patient>();
		this.allPatients = new ArrayList<Patient>();
		this.patientsListProperty = new SimpleListProperty<Patient>(FXCollections.observableArrayList(this.patients));
		this.allPatientsListProperty = new SimpleListProperty<Patient>(FXCollections.observableArrayList(this.patients));
		this.selectedPatientProperty = new SimpleObjectProperty<Patient>();
	}

	/**
	 * Gets the patients.
	 *
	 * @return the patients
	 */
	public ListProperty<Patient> patientsListProperty() {
		return this.patientsListProperty;
	}
	
	/**
	 * Gets the patients.
	 *
	 * @return the patients
	 */
	public ListProperty<Patient> allPatientsListProperty() {
		return this.allPatientsListProperty;
	}

	/**
	 * Sets the patients.
	 *
	 * @param patients the new patients
	 */
	public void setPatients(ListProperty<Patient> patients) {
		this.patientsListProperty = patients;
	}

	/**
	 * Gets the selected patient.
	 *
	 * @return the selected patient
	 */
	public ObjectProperty<Patient> selectedPatientProperty() {
		return this.selectedPatientProperty;
	}
	
	/**
	 * Gets the selected patient.
	 *
	 * @return the selected patient
	 */
	public Patient selectedPatient() {
		return this.selectedPatientProperty.getValue();
	}

	/**
	 * Sets the selected patient.
	 *
	 * @param selectedPatient the new selected patient
	 */
	public void setSelectedPatient(ObjectProperty<Patient> selectedPatient) {
		this.selectedPatientProperty = selectedPatient;
	}

	/**
	 * Gets the patients.
	 *
	 * @return the patients
	 */
	public List<Patient> getPatients() {
		return this.patients;
	}

	/**
	 * Sets the patients.
	 *
	 * @param patients the new patients
	 */
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	

	/**
	 * Gets all of the patients.
	 *
	 * @return all the patients
	 */
	public List<Patient> getAllPatients() {
		return this.allPatients;
	}

	public void loadPatients() {
		List<Patient> patients;
		try {
			patients = UserManager.userManager.getPatientsForMedicalPersonnel(User.user.getUsername());
			this.patients = patients;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.patientsListProperty.set(FXCollections.observableArrayList(this.patients));

	}
	
	public void loadAllPatients() {
		List<Patient> patients;
		try {
			patients = UserManager.userManager.getAllPatients();
			this.allPatients = patients;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.patientsListProperty.set(FXCollections.observableArrayList(this.allPatients));
	}
	
	/**
	 * Get the medicalePersonnel user
	 * 
	 * @return the logged in medicalePersonnel
	 */
	public MedicalPersonnel getMedicalePersonnel() {
		return this.medicalePersonnel;
	}
	
	
	/**
	 * Add an availability for user
	 * 
	 */
	public void addAvailability(String dateTime) throws ParseException{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime availability = LocalDateTime.parse(dateTime, formatter);
		this.availabilityList.add(availability);
		this.availabilityListProperty.set(FXCollections.observableArrayList(this.availabilityList));
	}
	
	/**
	 * Delete an availability for user
	 * 
	 * @return the added availability
	 */
	public void deleteAvailability() throws ParseException{
		LocalDateTime availability = this.selectedAvailabilityProperty.get();
		this.availabilityList.remove(availability);
		this.availabilityListProperty.set(FXCollections.observableArrayList(this.availabilityList));
	}
	
	/**
	 * Filter appointment list to future and past sections
	 * 
	 * @param appointments the appointment list used to filter
	 */
	public void filterAppointment(List<Appointment> appointments) {
		for (Appointment theAppointment : appointments) {
			if (!theAppointment.hasPassed()) {
				this.futureppointmentList.add(theAppointment);
			}
			else {
				this.pastAppointmentList.add(theAppointment);
			}
		}
		this.futureAppointmentListProperty.set(FXCollections.observableArrayList(this.futureppointmentList));
		this.pastAppointmentListProperty.set(FXCollections.observableArrayList(this.pastAppointmentList));

	}
	
	/**
	 * Set up the availability List
	 * @param dayTimes the value of vailability List
	 */
	public void setAvailabilityList(List<LocalDateTime> dayTimes) {
		for (LocalDateTime dateTime : dayTimes) {
			this.availabilityList.add(dateTime);
		}
		this.availabilityListProperty.set(FXCollections.observableArrayList(this.availabilityList));
	}
	
	/**
	 * Check current availability if is added
	 * 
	 * @return true if current availability is added; otherwise false
	 */
	public boolean isAddedAvailability(String dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime currentAvailability = LocalDateTime.parse(dateTime, formatter);
		for (LocalDateTime availability : this.availabilityList){
			if (availability.equals(currentAvailability)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Cancel an appointment for user
	 * 
	 * @return the canceled appointment
	 */
	public Appointment cancelAppointment() {
		Appointment appointment = this.selectedFutureAppointmentProperty.get();
		this.futureppointmentList.remove(appointment);
		this.futureAppointmentListProperty.set(FXCollections.observableArrayList(this.futureppointmentList));
		return appointment;
	}
	
	/**
	 * Delete patient.
	 *
	 * @return the patient
	 */
	public Patient removePatientFromCare() {
		Patient patient = this.selectedPatientProperty.get();
		this.patients.remove(this.selectedPatientProperty.getValue());
		//this.patientsListProperty.set(FXCollections.observableArrayList(this.patients));
		return patient;
	}

	/**
	 * Adds the patient to care.
	 *
	 * @return the patient
	 */
	public Patient addPatientToCare() {
		Patient patient = this.selectedPatientProperty.get();
		this.patients.add(this.selectedPatientProperty.getValue());
		//this.patientsListProperty.set(FXCollections.observableArrayList(this.patients));
		return patient;
	}
	
	/**
	 * Checks if the selected patient is under the care of the logged medical personnel
	 *
	 * @return true if under care, false otherwise
	 */
	public boolean checkPatientUnderCare() {
		Patient patient = this.selectedPatientProperty.get();
		if (this.patients.contains(patient)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Get the selected time
	 * 
	 * @return the selected time
	 */
	public ObjectProperty<String> seletedTimeProperty() {
		return this.selectedTimeProperty;
	}
	
	/**
	 * Get the selected availability property
	 * 
	 * @return the selected availability property
	 */
	public ObjectProperty<LocalDateTime> selectedDayProperty() {
		return this.selectedDayProperty;
	}
	
	/**
	 * Get the selected availability property
	 * 
	 * @return the selected availability property
	 */
	public ObjectProperty<LocalDateTime> selectedAvailabilityProperty() {
		return this.selectedAvailabilityProperty;
	}
	
	/**
	 * Get the availability list property
	 * 
	 * @return the availability list property
	 */
	public ListProperty<LocalDateTime> availabilityListProperty() {
		return this.availabilityListProperty;
	}
	
	/**
	 * Get the selected future appointment property
	 * 
	 * @return the selected future appointment property
	 */
	public ObjectProperty<Appointment> selectedFutureAppointmentProperty() {
		return this.selectedFutureAppointmentProperty;
	}
	
	/**
	 * Get the selected past appointment property
	 * 
	 * @return the selected past appointment property
	 */
	public ObjectProperty<Appointment> selectedPastAppointmentProperty() {
		return this.selectedPastAppointmentProperty;
	}
	
	/**
	 * Get the future appointment list property
	 *  
	 * @return the future appointment list property
	 */
	public ListProperty<Appointment> futureAppointmentListProperty() {
		return this.futureAppointmentListProperty;
	}
	
	/**
	 * Get the past appointment list property
	 *  
	 * @return the past appointment list property
	 */
	public ListProperty<Appointment> pastAppointmentListProperty() {
		return this.pastAppointmentListProperty;
	}
	
	/**
	 * Get the notes property
	 *  
	 * @return the notes property
	 */
	public StringProperty notesProperty() {
		return this.notesProperty;
	}
	
	/**
	 * Get the future appointment list
	 *  
	 * @return the future appointment list
	 */
	public List<Appointment> futureppointmentList() {
		return this.futureppointmentList;
	}
	
	/**
	 * Get the past appointment list
	 *  
	 * @return the past appointment list
	 */
	public List<Appointment> pastAppointmentList() {
		return this.pastAppointmentList;
	}
	
	/**
	 * Get the availability list
	 *  
	 * @return the availability list
	 */
	public List<LocalDateTime> availabilityList() {
		return this.availabilityList;
	}

}
