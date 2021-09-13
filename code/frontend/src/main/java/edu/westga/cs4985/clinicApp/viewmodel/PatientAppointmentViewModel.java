package edu.westga.cs4985.clinicApp.viewmodel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.westga.cs4985.clinicApp.model.Appointment;
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
public class PatientAppointmentViewModel {
	
	private StringProperty seletedMedicalPersonnel;
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
	public PatientAppointmentViewModel() {
		this.patient = (Patient) User.user;
		this.selectedFutureAppointmentProperty = new SimpleObjectProperty<Appointment>();
		this.selectedPastAppointmentProperty = new SimpleObjectProperty<Appointment>();
		this.selectedAvailabilityProperty = new SimpleObjectProperty<LocalDateTime>();
		this.futureAppointmentListProperty = new SimpleListProperty<Appointment>();
		this.pastAppointmentListProperty = new SimpleListProperty<Appointment>();
		this.availabilityListProperty = new SimpleListProperty<LocalDateTime>();
		this.notesProperty = new SimpleStringProperty("");
		this.seletedMedicalPersonnel = new SimpleStringProperty("");
		this.futureppointmentList = new ArrayList<Appointment>();
		this.pastAppointmentList = new ArrayList<Appointment>();
		
	}
	
	public Patient getPatient() {
		return this.patient;
	}
	
	public Appointment bookAppointment() {
		Appointment appointment = new Appointment(this.selectedAvailabilityProperty.get(), this.patient, this.seletedMedicalPersonnel.get(), "TLC", this.notesProperty.get());
		this.futureppointmentList.add(appointment);
		this.futureAppointmentListProperty.set(FXCollections.observableArrayList(this.futureppointmentList));
		return appointment;
	}
	
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
	
	public boolean isBookedAppointment() {
		for (Appointment appointment : this.futureppointmentList){
			if (appointment.getMedicalPersonnel().equals(this.seletedMedicalPersonnel.get()) &&
					appointment.getDateTime().equals(this.selectedAvailabilityProperty.get())) {
				return true;
			}
		}
		return false;
	}
	
	public Appointment cancelAppointment() {
		Appointment appointment = this.selectedFutureAppointmentProperty.get();
		this.futureppointmentList.remove(appointment);
		this.futureAppointmentListProperty.set(FXCollections.observableArrayList(this.futureppointmentList));
		return appointment;
	}
	
	public StringProperty seletedMedicalPersonnel() {
		return this.seletedMedicalPersonnel;
	}
	
	public ObjectProperty<LocalDateTime> selectedAvailabilityProperty() {
		return this.selectedAvailabilityProperty;
	}
	
	public ListProperty<LocalDateTime> availabilityListProperty() {
		return this.availabilityListProperty;
	}
	
	public ObjectProperty<Appointment> selectedFutureAppointmentProperty() {
		return this.selectedFutureAppointmentProperty;
	}
	
	public ObjectProperty<Appointment> selectedPastAppointmentProperty() {
		return this.selectedPastAppointmentProperty;
	}
	
	public ListProperty<Appointment> futureAppointmentListProperty() {
		return this.futureAppointmentListProperty;
	}
	
	public ListProperty<Appointment> pastAppointmentListProperty() {
		return this.pastAppointmentListProperty;
	}
	
	public StringProperty notesProperty() {
		return this.notesProperty;
	}
	
	public List<Appointment> futureppointmentList() {
		return this.futureppointmentList;
	}
	
	public List<Appointment> pastAppointmentList() {
		return this.pastAppointmentList;
	}
	
}
