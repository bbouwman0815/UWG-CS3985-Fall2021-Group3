package edu.westga.cs4985.clinicApp.model;

import java.time.LocalDateTime;

/**
 * The Class Appointment.
 * 
 * @author JinxiangZeng
 * @version Fall 2021
 */
public class Appointment {

	private LocalDateTime dateTime;
	private Patient patient;
	private String medicalPersonnel;
	private String location;
	private String notes;

	/**
	 * Instantiates a new appointment.
	 * 
	 * @precondition date != null && patient != null && medicalPersonnel != null
	 * 				 && !location.isEmpty() && location != null && notes != null
	 * @postcondition getDateTime() == dateTime && getPatient() == patient && getMedicalPersonnel() == medicalPersonnel
	 *                && getLocation() == location && getNotes() == notes
	 *
	 * @param dateTime     			the date and time
	 * @param patient     			the patient
	 * @param medicalPersonnel    	the medicalPersonnel
	 * @param location 				the location
	 * @param notes    				the notes
	 */
	public Appointment(LocalDateTime dateTime, Patient patient, String medicalPersonnel, String location, String notes) {
		if (dateTime == null) {
			throw new IllegalArgumentException("dateTime cannnot be null");
		}
		if (patient == null) {
			throw new IllegalArgumentException("Patient cannnot be null");
		}
		if (medicalPersonnel == null) {
			throw new IllegalArgumentException("Medical personnel cannnot be null");
		}
		if (location == null) {
			throw new IllegalArgumentException("Location cannnot be null");
		}
		if (location.isEmpty()) {
			throw new IllegalArgumentException("Location cannnot be empty");
		}
		if (notes == null) {
			throw new IllegalArgumentException("Notes cannnot be null");
		}
		this.dateTime = dateTime;
		this.patient = patient;
		this.medicalPersonnel = medicalPersonnel;
		this.location = location;
		this.notes = notes;
	}

	/**
	 * Gets the dateTime.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the dateTime
	 */
	public LocalDateTime getDateTime() {
		return this.dateTime;
	}

	/**
	 * Sets the dateTime.
	 * 
	 * @precondition date !- null
	 * @postcondition getDateTime() == dateTime
	 *
	 * @param dateTime the new dateTime
	 */
	public void setDateTime(LocalDateTime dateTime) {
		if (dateTime == null) {
			throw new IllegalArgumentException("Date cannot be null");
		}

		this.dateTime = dateTime;
	}

	/**
	 * Gets the patient.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return this.patient;
	}
	
	/**
	 * Gets the medicalPersonnel.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the medicalPersonnel
	 */
	public String getMedicalPersonnel() {
		return this.medicalPersonnel;
	}

	/**
	 * Gets the location.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the location
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Sets the location.
	 * 
	 * @precondition location != null && !location.isEmpty()
	 * @postcondition getLocation == location
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		if (location == null) {
			throw new IllegalArgumentException("Location cannot be null");
		}
		if (location.isEmpty()) {
			throw new IllegalArgumentException("Location cannot be empty");
		}

		this.location = location;
	}

	/**
	 * Gets the notes.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the notes
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * Sets the notes.
	 * 
	 * @precondition notes != null && !notes.isEmpty()
	 * @postcondition getNotes() == notes
	 *
	 * @param notes the new notes
	 */
	public void setNotes(String notes) {
		if (notes == null) {
			throw new IllegalArgumentException("Notes cannot be null");
		}
		if (notes.isEmpty()) {
			throw new IllegalArgumentException("Notes cannot be empty");
		}

		this.notes = notes;
	}

	/**
	 * Checks if the appointment has passed.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return true, if the appointment has passed
	 */
	public boolean hasPassed() {
		boolean hasPassed = false;
		if (this.dateTime.isBefore(LocalDateTime.now())) {
			hasPassed = true;
		}
		return hasPassed;
	}

	@Override
	public String toString() {
		return "On " + this.dateTime.toLocalDate() + " at "  + this.dateTime.getHour() + " : " + this.dateTime.getMinute();
	}

	@Override
	public int hashCode() {
		String hashString = this.getDateTime().toString() + this.getPatient().getFirstName()
				+ this.getPatient().getLastName();
		return hashString.hashCode();
	}

}