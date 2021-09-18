package edu.westga.cs4985.clinicApp.model;

import edu.westga.cs4985.clinicApp.resources.UI;

/*
 * @author Brian Bouwman
 * @version Fall 2021
 */
public class MedicalCondition {

	private Patient patient;

	private String name;

	private String diagnosisDate;

	private String terminationDate;

	private String notes;

	/**
	 * Instantiates a new medical condition.
	 *
	 * @param patient         the patient
	 * @param name            the name
	 * @param diagnosisDate   the diagnosis date
	 * @param terminationDate the termination date
	 * @param notes           the notes
	 * @precondition patient != null && name != null && !name.isEmpty() &&
	 *               diagnosisDate != null && !diagnosisDate.isEmpty() &&
	 *               terminationDate != null && notes != null
	 */
	public MedicalCondition(Patient patient, String name, String diagnosisDate, String terminationDate, String notes) {
		if (patient == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_PATIENT);
		}
		if (name == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_MEDCON_NAME);
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_MEDCON_NAME);
		}
		if (diagnosisDate == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_DIAGNOSIS_DATE);
		}
		if (diagnosisDate.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_DIAGNOSIS_DATE);
		}
		if (terminationDate == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_TERMINATION_DATE);
		}
		if (notes == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_MEDCON_SPECIAL_NOTES);
		}
		this.name = name;
		this.diagnosisDate = diagnosisDate;
		this.terminationDate = terminationDate;
		this.notes = notes;
		this.patient = patient;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the diagnosis date.
	 *
	 * @return the diagnosis date
	 */
	public String getDiagnosisDate() {
		return diagnosisDate;
	}

	/**
	 * Sets the diagnosis date.
	 *
	 * @param diagnosisDate the new diagnosis date
	 */
	public void setDiagnosisDate(String diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}

	/**
	 * Gets the termination date.
	 *
	 * @return the termination date
	 */
	public String getTerminationDate() {
		return terminationDate;
	}

	/**
	 * Sets the termination date.
	 *
	 * @param terminationDate the new termination date
	 */
	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}

	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * Sets the special notes.
	 *
	 * @param notes the new notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * Gets the patient.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Sets the patient.
	 *
	 * @param patient the new patient
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
