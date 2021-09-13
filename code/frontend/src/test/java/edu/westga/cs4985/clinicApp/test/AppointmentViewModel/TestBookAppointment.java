package edu.westga.cs4985.clinicApp.test.AppointmentViewModel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.viewmodel.PatientAppointmentViewModel;

/**
 * Test AppointmentViewModel bookAppointment
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class TestBookAppointment {
	
	@Test
	public void testBookAppointment() {
		Patient patient = new Patient("Jimmy", "Bob", "male", "1990-09-09", "new", "new", "nwe", "new", "USA", "New", "new", "new", "new", "new", "new", "new");
		User.setUser(patient);
		PatientAppointmentViewModel viewModel = new PatientAppointmentViewModel();
		viewModel.selectedAvailabilityProperty().set(LocalDateTime.of(2021, 9,9,14,0));
		viewModel.seletedMedicalPersonnel().set("PersonA");
		viewModel.notesProperty().set("Help");
		viewModel.bookAppointment();
		assertAll(() -> assertEquals(1, viewModel.futureAppointmentListProperty().size()),
				() -> assertEquals(1, viewModel.FutureppointmentList().size()));
	}

}
