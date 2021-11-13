package edu.westga.cs4985.clinicApp.test.CaregiverViewModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Caregiver;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;
import edu.westga.cs4985.clinicApp.viewmodel.CaregiverViewModel;

public class TestIsBookedAppointment {
	
	public Caregiver caregiverDummy() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Caregiver caregiver = new Caregiver("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
				"770-111-222", "email@email.com", "New", "New");
		return caregiver;
	}
	
	public MedicalPersonnel medicalPersonnelDummy() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		MedicalPersonnel patientDummy = new MedicalPersonnel("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
				"770-111-222", "email@email.com", "New", "New", "30118");
		return patientDummy;
	}
	
	public MedicalPersonnel medicalPersonnelDummy2() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		MedicalPersonnel patientDummy = new MedicalPersonnel("new", "new", gender.sex[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
				"770-111-222", "new@email.com", "New", "New", "30118");
		return patientDummy;
	}

	@Test
	public void testIsBookAppointment() {
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		Patient patient = new Patient("Jimmy", "Bob", "male", "1990-09-09", "new", "new", "nwe", "new", "USA", "New", "new", "new", "new", "new", "new", "new");
		User.setUser(caregiverDummy());
		CaregiverViewModel viewModel = new CaregiverViewModel();
		viewModel.selectedAvailabilityProperty().set(LocalDateTime.of(2021, 9,9,14,0));
		viewModel.seletedMedicalPersonnel().set(medicalPersonnel);
		viewModel.selectedPatientProperty().set(patient);
		viewModel.notesProperty().set("Help");
		viewModel.bookAppointment();
		assertEquals(true, viewModel.isBookedAppointment());
	}
	
	@Test
	public void testIsNotBookAppointment() {
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		MedicalPersonnel medicalPersonnel2 = medicalPersonnelDummy2();
		Patient patient = new Patient("Jimmy", "Bob", "male", "1990-09-09", "new", "new", "nwe", "new", "USA", "New", "new", "new", "new", "new", "new", "new");
		User.setUser(caregiverDummy());
		CaregiverViewModel viewModel = new CaregiverViewModel();
		viewModel.selectedAvailabilityProperty().set(LocalDateTime.of(2021, 9,9,14,0));
		viewModel.seletedMedicalPersonnel().set(medicalPersonnel);
		viewModel.selectedPatientProperty().set(patient);
		viewModel.notesProperty().set("Help");
		viewModel.bookAppointment();
		viewModel.selectedAvailabilityProperty().set(LocalDateTime.of(2021, 9,10,14,0));
		viewModel.seletedMedicalPersonnel().set(medicalPersonnel2);
		viewModel.selectedPatientProperty().set(patient);
		viewModel.notesProperty().set("Help");
		assertEquals(false, viewModel.isBookedAppointment());
	}

}
