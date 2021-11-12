package edu.westga.cs4985.clinicApp.viewmodel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
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
public class PatientViewModel {

	private ObjectProperty<MedicalPersonnel> seletedMedicalPersonnel;
	private ObjectProperty<LocalDateTime> selectedAvailabilityProperty;
	private ListProperty<LocalDateTime> availabilityListProperty;
	private ObjectProperty<Appointment> selectedFutureAppointmentProperty;
	private ObjectProperty<Appointment> selectedPastAppointmentProperty;
	private ListProperty<Appointment> futureAppointmentListProperty;
	private ListProperty<Appointment> pastAppointmentListProperty;
	private List<Appointment> futureppointmentList;
	private StringProperty notesProperty;
	private List<Appointment> pastAppointmentList;
	private Patient patient;

	/**
	 * Create view model for patient general information.
	 * 
	 * @precondition none
	 * 
	 * @postcondition none
	 */
	public PatientViewModel() {
		this.patient = (Patient) User.user;
		this.seletedMedicalPersonnel = new SimpleObjectProperty<MedicalPersonnel>();
		this.selectedFutureAppointmentProperty = new SimpleObjectProperty<Appointment>();
		this.selectedPastAppointmentProperty = new SimpleObjectProperty<Appointment>();
		this.selectedAvailabilityProperty = new SimpleObjectProperty<LocalDateTime>();
		this.futureAppointmentListProperty = new SimpleListProperty<Appointment>();
		this.pastAppointmentListProperty = new SimpleListProperty<Appointment>();
		this.availabilityListProperty = new SimpleListProperty<LocalDateTime>();
		this.notesProperty = new SimpleStringProperty("");
		this.futureppointmentList = new ArrayList<Appointment>();
		this.pastAppointmentList = new ArrayList<Appointment>();

	}

	/**
	 * Get the patient user
	 * 
	 * @return the logged in patient
	 */
	public Patient getPatient() {
		return this.patient;
	}

	/**
	 * Booking an appointment for user
	 * 
	 * @return the booked appointment
	 */
	public Appointment bookAppointment() {
		Appointment appointment = new Appointment(this.selectedAvailabilityProperty.get(), this.patient,
				this.seletedMedicalPersonnel.get(), this.seletedMedicalPersonnel.get().getFullAddress(),
				this.notesProperty.get());
		this.futureppointmentList.add(appointment);
		this.futureAppointmentListProperty.set(FXCollections.observableArrayList(this.futureppointmentList));
		return appointment;
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
			} else {
				this.pastAppointmentList.add(theAppointment);
			}
		}
		this.futureAppointmentListProperty.set(FXCollections.observableArrayList(this.futureppointmentList));
		this.pastAppointmentListProperty.set(FXCollections.observableArrayList(this.pastAppointmentList));

	}

	/**
	 * Check current appointment if is booked
	 * 
	 * @return true if current appointment is booked; otherwise false
	 */
	public boolean isBookedAppointment() {
		for (Appointment appointment : this.futureppointmentList) {
			if (appointment.getMedicalPersonnel().equals(this.seletedMedicalPersonnel.get())
					&& appointment.getDateTime().equals(this.selectedAvailabilityProperty.get())) {
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
	 * Get the selected medical personnel
	 * 
	 * @return the selected medical personnel
	 */
	public ObjectProperty<MedicalPersonnel> seletedMedicalPersonnel() {
		return this.seletedMedicalPersonnel;
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

}
