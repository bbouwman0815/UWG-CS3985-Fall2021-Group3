package edu.westga.cs4985.clinicApp.test.CaregiverViewModel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Caregiver;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.viewmodel.CaregiverViewModel;

/**
 * Test CaregiverViewModel Constructor
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class TestConstructor {
	
	@Test
	public void testConstructor() {
		Caregiver caregiver = new Caregiver("Jimmy", "Bob", "male", "1990-09-09", "new", "new", "nwe", "new", "USA", "New", "new", "new", "new", "new", "new");
		User.setUser(caregiver);
		CaregiverViewModel viewModel = new CaregiverViewModel();
		assertAll(() -> assertEquals(null, viewModel.selectedFutureAppointmentProperty().getValue()),
				() -> assertEquals(null, viewModel.selectedPastAppointmentProperty().getValue()),
				() -> assertEquals(null, viewModel.selectedAvailabilityProperty().getValue()),
				() -> assertEquals(0, viewModel.futureAppointmentListProperty().size()),
				() -> assertEquals(0, viewModel.pastAppointmentListProperty().size()),
				() -> assertEquals("", viewModel.notesProperty().getValue()),
				() -> assertEquals(null, viewModel.seletedMedicalPersonnel().getValue()),
				() -> assertEquals(0, viewModel.futureppointmentList().size()),
				() -> assertEquals(0, viewModel.pastAppointmentList().size()));
	}

}
