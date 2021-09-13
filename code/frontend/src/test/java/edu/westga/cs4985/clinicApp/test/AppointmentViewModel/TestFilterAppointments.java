package edu.westga.cs4985.clinicApp.test.AppointmentViewModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.viewmodel.PatientAppointmentViewModel;


/**
 * Test AppointmentViewModel filterAppointment
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class TestFilterAppointments {

	@Test
	public void testFilterAppointment() {
		Patient patient = new Patient("Jimmy", "Bob", "male", "1990-09-09", "new", "new", "nwe", "new", "USA", "New", "new", "new", "new", "new", "new", "new");
		User.setUser(patient);
		PatientAppointmentViewModel viewModel = new PatientAppointmentViewModel();
		List<Appointment> appointments = new ArrayList<Appointment>();
		Appointment appointment1 = new Appointment(LocalDateTime.of(2021,10,01,13,00), patient, "Person A", "TLC", "help");
		Appointment appointment2 = new Appointment(LocalDateTime.of(2021,7,01,13,00), patient, "Person A", "TLC", "help");
		appointments.add(appointment1);
		appointments.add(appointment2);
		viewModel.filterAppointment(appointments);
		assertEquals(1, viewModel.futureppointmentList().size());
		assertEquals(1, viewModel.pastAppointmentList().size());
	}
}
