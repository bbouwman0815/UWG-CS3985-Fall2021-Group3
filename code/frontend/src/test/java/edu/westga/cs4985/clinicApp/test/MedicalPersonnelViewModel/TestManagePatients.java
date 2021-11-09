package edu.westga.cs4985.clinicApp.test.MedicalPersonnelViewModel;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;
import edu.westga.cs4985.clinicApp.viewmodel.MedicalPersonnelViewModel;
import javafx.beans.property.SimpleObjectProperty;

public class TestManagePatients {

	public MedicalPersonnel medicalPersonnelDummy() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		MedicalPersonnel medicalPersonnel = new MedicalPersonnel("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
				"770-111-222", "email@email.com", "New", "New", "30118");
		return medicalPersonnel;
	}

	@Test
	public void testAddPatient() {
		
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		Patient patient = new Patient("Jimmy", "Bob", "male", "1990-09-09", "new", "new", "nwe", "new", "USA", "New", "new", "new", "new", "new", "new", "new");
		User.setUser(medicalPersonnel);
		MedicalPersonnelViewModel viewModel = new MedicalPersonnelViewModel();
		viewModel.setSelectedPatient(new SimpleObjectProperty<Patient>(patient));
		viewModel.addPatientToCare();
		assertEquals(1, viewModel.getPatients().size());
	}
	
	@Test
	public void testGetPatients() {
		
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		Patient patient = new Patient("Jimmy", "Bob", "male", "1990-09-09", "new", "new", "nwe", "new", "USA", "New", "new", "new", "new", "new", "new", "new");
		User.setUser(medicalPersonnel);
		MedicalPersonnelViewModel viewModel = new MedicalPersonnelViewModel();
		viewModel.setSelectedPatient(new SimpleObjectProperty<Patient>(patient));
		viewModel.addPatientToCare();
		assertEquals(1, viewModel.getPatients().size());
	}
	
	@Test
	public void testRemovePatient() {
		
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		Patient patient = new Patient("Jimmy", "Bob", "male", "1990-09-09", "new", "new", "nwe", "new", "USA", "New", "new", "new", "new", "new", "new", "new");
		User.setUser(medicalPersonnel);
		MedicalPersonnelViewModel viewModel = new MedicalPersonnelViewModel();
		viewModel.setSelectedPatient(new SimpleObjectProperty<Patient>(patient));
		viewModel.addPatientToCare();
		viewModel.removePatientFromCare();
		assertEquals(0, viewModel.getPatients().size());
	}

}
