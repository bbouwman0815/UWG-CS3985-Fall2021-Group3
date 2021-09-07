package edu.westga.cs4985.clinicApp.test.AppointmentViewModel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.viewmodel.PatientAppointmentViewModel;

/**
 * Test AppointmentViewModel isBookAppointment
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class TestIsBookedAppointment {

	@Test
	public void testIsBookAppointment() {
		PatientAppointmentViewModel viewModel = new PatientAppointmentViewModel();
		viewModel.selectedAvailabilityProperty().set(LocalDateTime.of(2021, 9,9,14,0));
		viewModel.seletedMedicalPersonnel().set("PersonA");
		viewModel.notesProperty().set("Help");
		viewModel.bookAppointment();
		assertEquals(true, viewModel.isBookedAppointment());
	}
	
	@Test
	public void testIsNotBookAppointment() {
		PatientAppointmentViewModel viewModel = new PatientAppointmentViewModel();
		viewModel.selectedAvailabilityProperty().set(LocalDateTime.of(2021, 9,9,14,0));
		viewModel.seletedMedicalPersonnel().set("PersonA");
		viewModel.notesProperty().set("Help");
		viewModel.bookAppointment();
		viewModel.selectedAvailabilityProperty().set(LocalDateTime.of(2021, 9,10,14,0));
		viewModel.seletedMedicalPersonnel().set("PersonB");
		viewModel.notesProperty().set("Help");
		assertEquals(false, viewModel.isBookedAppointment());
	}
}
