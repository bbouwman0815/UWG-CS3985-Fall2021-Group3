package edu.westga.cs4985.clinicApp.test.AppointmentViewModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.viewmodel.PatientAppointmentViewModel;


/**
 * Test AppointmentViewModel getPatient
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class TestGetPaitent {

	@Test
	public void testTetPatient() {
		Patient patient = new Patient("Jimmy", "Bob", "male", "1990-09-09", "new", "new", "nwe", "new", "USA", "New", "new", "new", "new", "new", "new", "new");
		User.setUser(patient);
		PatientAppointmentViewModel viewModel = new PatientAppointmentViewModel();
		assertEquals(true, viewModel.getPatient().equals(patient));
	}
}
