package edu.westga.cs4985.clinicApp.test.MedicalPersonnelViewModel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.viewmodel.MedicalPersonnelViewModel;

public class TestConstructor {
	
	@Test
	public void testConstructor() {
		MedicalPersonnel medicalPersonnel = new MedicalPersonnel("Xavier", "Jameson", "male", "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", "USA", "new", "new",
				"770-111-222", "email@email.com", "New", "New", "30118");
		User.setUser(medicalPersonnel);
		MedicalPersonnelViewModel viewModel = new MedicalPersonnelViewModel();
		assertAll(() -> assertEquals(null, viewModel.selectedFutureAppointmentProperty().getValue()),
				() -> assertEquals(null, viewModel.selectedPastAppointmentProperty().getValue()),
				() -> assertEquals(null, viewModel.selectedAvailabilityProperty().getValue()),
				() -> assertEquals(0, viewModel.futureAppointmentListProperty().size()),
				() -> assertEquals(0, viewModel.pastAppointmentListProperty().size()),
				() -> assertEquals(0, viewModel.availabilityListProperty().size()),
				() -> assertEquals("", viewModel.notesProperty().getValue()),
				() -> assertEquals(null, viewModel.selectedDayProperty().getValue()),
				() -> assertEquals(0, viewModel.futureppointmentList().size()),
				() -> assertEquals(0, viewModel.pastAppointmentList().size()),
				() -> assertEquals(0, viewModel.availabilityList().size()),
				() -> assertEquals(null, viewModel.seletedTimeProperty().getValue()),
				() -> assertEquals(medicalPersonnel, viewModel.getMedicalePersonnel()),
				() -> assertEquals(0, viewModel.getPatients().size()),
				() -> assertEquals(null, viewModel.selectedPatient()),
				() -> assertEquals(null, viewModel.selectedPatientProperty().getValue()),
				() -> assertEquals(0, viewModel.patientsListProperty().size()));
	}

}
