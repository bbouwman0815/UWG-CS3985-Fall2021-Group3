package edu.westga.cs4985.clinicApp.test.AppointmentViewModel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.viewmodel.PatientAppointmentViewModel;

/**
 * Test AppointmentViewModel cancelAppointment
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class TestCancelAppointment {

	@Test
	public void tesCancelAppointment() {
		PatientAppointmentViewModel viewModel = new PatientAppointmentViewModel();
		viewModel.selectedAvailabilityProperty().set(LocalDateTime.of(2021, 9,9,14,0));
		viewModel.seletedMedicalPersonnel().set("PersonA");
		viewModel.notesProperty().set("Help");
		viewModel.bookAppointment();
		viewModel.selectedFutureAppointmentProperty().set(viewModel.FutureppointmentList().get(0));
		viewModel.cancelAppointment();
		assertAll(() -> assertEquals(0, viewModel.futureAppointmentListProperty().size()),
				() -> assertEquals(0, viewModel.FutureppointmentList().size()));
	}
}
