package edu.westga.cs4985.clinicApp.test.CaregiverViewModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.Caregiver;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;
import edu.westga.cs4985.clinicApp.viewmodel.CaregiverViewModel;

/**
 * Test CaregiverViewModel FilterAppointments
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class TestFilterAppointments {
	
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

	@Test
	public void testFilterAppointment() {
		
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		MedicalPersonnel medicalPersonnel = new MedicalPersonnel("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
				"770-111-222", "email@email.com", "New", "New", "30118");
		Caregiver caregiverDummy = caregiverDummy();
		Patient patient = new Patient("Jimmy", "Bob", "male", "1990-09-09", "new", "new", "nwe", "new", "USA", "New", "new", "new", "new", "new", "new", "new");
		User.setUser(caregiverDummy);
		CaregiverViewModel viewModel = new CaregiverViewModel();
		List<Appointment> appointments = new ArrayList<Appointment>();
		Appointment appointment1 = new Appointment(LocalDateTime.of(2024,10,01,13,00), patient, medicalPersonnel, "TLC", "help");
		Appointment appointment2 = new Appointment(LocalDateTime.of(2021,7,01,13,00), patient, medicalPersonnel, "TLC", "help");
		appointments.add(appointment1);
		appointments.add(appointment2);
		viewModel.filterAppointment(appointments);
		assertEquals(1, viewModel.futureppointmentList().size());
		assertEquals(1, viewModel.pastAppointmentList().size());
	}

}
