package edu.westga.cs4985.clinicApp.test.AppointmentViewModel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.viewmodel.PatientAppointmentViewModel;

/**
 * Test AppointmentViewModel Constructor
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class TestConstructor {
	
	@Test
	public void testConstructor() {
		PatientAppointmentViewModel viewModel = new PatientAppointmentViewModel();
		assertAll(() -> assertEquals(null, viewModel.selectedFutureAppointmentProperty().getValue()),
				() -> assertEquals(null, viewModel.selectedPastAppointmentProperty().getValue()),
				() -> assertEquals(null, viewModel.selectedAvailabilityProperty().getValue()),
				() -> assertEquals(0, viewModel.futureAppointmentListProperty().size()),
				() -> assertEquals(0, viewModel.pastAppointmentListProperty().size()),
				() -> assertEquals(0, viewModel.availabilityListProperty().size()),
				() -> assertEquals("", viewModel.notesProperty().getValue()),
				() -> assertEquals("", viewModel.seletedMedicalPersonnel().getValue()),
				() -> assertEquals(0, viewModel.FutureppointmentList().size()),
				() -> assertEquals(0, viewModel.pastAppointmentList().size()));
	}
}
