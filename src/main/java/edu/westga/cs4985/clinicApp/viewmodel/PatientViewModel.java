package edu.westga.cs4985.clinicApp.viewmodel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;
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
	
	private StringProperty seletedMedicalPersonnel;
	private ObjectProperty<LocalDateTime> selectedAvailabilityProperty;
	private ListProperty<LocalDateTime> availabilityListProperty;
	private ObjectProperty<Appointment> selectedAppointmentProperty;
	private ListProperty<Appointment> appointmentListProperty;
	private StringProperty notesProperty;
	
	/**
	 * Create view model for patient general information.
	 * 
	 * @precondition none
	 * 
	 * @postcondition none
	 */
	public PatientViewModel() {
		
		this.selectedAppointmentProperty = new SimpleObjectProperty<Appointment>();
		this.selectedAvailabilityProperty = new SimpleObjectProperty<LocalDateTime>();
		this.appointmentListProperty = new SimpleListProperty<Appointment>();
		this.availabilityListProperty = new SimpleListProperty<LocalDateTime>();
		this.notesProperty = new SimpleStringProperty("");
		this.seletedMedicalPersonnel = new SimpleStringProperty("");
		
	}
	
	public void bookAppointment() {
		Patient patient = new Patient("Jimmy", "Bob", new Gender(), "1990-09-09", "new", "new", "nwe", "new", new Country(), new Race(), new Ethnicity(), "new", "new", "new");
		Appointment appointment = new Appointment(this.selectedAvailabilityProperty.get(), patient, this.seletedMedicalPersonnel.get(), "TLC", this.notesProperty.get());
		
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
	
	public ObjectProperty<Appointment> selectedAppointmentProperty() {
		return this.selectedAppointmentProperty;
	}
	
	public ListProperty<Appointment> appointmentListProperty() {
		return this.appointmentListProperty;
	}
	
	public StringProperty notesProperty() {
		return this.notesProperty;
	}
}
