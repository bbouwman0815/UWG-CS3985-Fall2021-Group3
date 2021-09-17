package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

/**
 * JUnit Test Case for Cancel Appointment
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 *
 */
class TestCancelAppointment {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;
			if (request.equals(
					"CANCLE_APPOINTMENT,{\"date\":\"2021-09-01T14:00\",\"notes\":\"help\",\"patient\":\"New\",\"location\":\"TCL\",\"medicalPersonnel\":\"Person A\"}")) {
				return "Removed";
			} else {
				return "ERROR";
			}
		}
	}

	public Patient patientDummy() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patientDummy = new Patient("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
				"770-111-222", "email@email.com", "United Healthcare", "New", "New");
		return patientDummy;
	}

	@Test
	void testCancelValidAppointment() {
		LocalDateTime dateTime = LocalDateTime.of(2021, 9, 01, 14, 00);
		Patient patient = this.patientDummy();
		Appointment appointment = new Appointment(dateTime, patient, "Person A", "TCL", "help");
		UserManager userManager = new UserManager(new ServerFake());
		boolean removed = userManager.cancelAppointment(appointment);
		assertEquals(true, removed);
	}

	@Test
	void testCancelInvalidAppointment() {
		LocalDateTime dateTime = LocalDateTime.of(2021, 9, 01, 14, 00);
		Patient patient = this.patientDummy();
		Appointment appointment = new Appointment(dateTime, patient, "Person B", "TCL", "help");
		UserManager userManager = new UserManager(new ServerFake());
		boolean removed = userManager.cancelAppointment(appointment);
		assertEquals(false, removed);
	}

}
