package edu.westga.cs4985.clinicApp.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.UserManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

/**
 * The Class MedicalPersonnelViewModel.
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 * 
 */
public class MedicalPersonnelViewModel {

	private ListProperty<Patient> patientsListProperty;

	private ObjectProperty<Patient> selectedPatientProperty;

	private List<Patient> patients;
	

	// private MedicalPersonnel medicalPersonnel;

	/**
	 * Instantiates a new medical personnel view model.
	 */
	public MedicalPersonnelViewModel() {
		this.patients = new ArrayList<Patient>();
		this.patientsListProperty = new SimpleListProperty<Patient>(FXCollections.observableArrayList(this.patients));
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
	 * Patient list.
	 *
	 * @return the list
	 */
	public List<Patient> patientList() {
		return this.patients;
	}

	public void loadPatients() {
		List<Patient> patients;
		try {
			patients = UserManager.userManager.getAllPatients();
			this.patients = patients;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.patientsListProperty.set(FXCollections.observableArrayList(this.patients));

	}

	/**
	 * Get the medical personnel user
	 * 
	 * @return the logged in medical personnel
	 */
//	public MedicalPersonnel getMedicalPersonnel() {
//		return this.patient;
//	}
}
