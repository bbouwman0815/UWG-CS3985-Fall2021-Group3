package edu.westga.cs4985.clinicApp.test.AppointmentViewModel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.viewmodel.PatientAppointmentViewModel;

/**
 * Test AppointmentViewModel togglePastAppointment
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class TestTogglePastAppointment {

	@Test
	public void testBookAppointment() {
		PatientAppointmentViewModel viewModel = new PatientAppointmentViewModel();
		viewModel.selectedAvailabilityProperty().set(LocalDateTime.of(2021, 9,1,14,0));
		viewModel.seletedMedicalPersonnel().set("PersonA");
		viewModel.notesProperty().set("Help");
		viewModel.bookAppointment();
		viewModel.togglePastAppointment(viewModel.FutureppointmentList().get(0));
		assertAll(() -> assertEquals(0, viewModel.futureAppointmentListProperty().size()),
				() -> assertEquals(0, viewModel.FutureppointmentList().size()),
				() -> assertEquals(1, viewModel.pastAppointmentListProperty().size()),
				() -> assertEquals(1, viewModel.pastAppointmentList().size()));
	}
}
