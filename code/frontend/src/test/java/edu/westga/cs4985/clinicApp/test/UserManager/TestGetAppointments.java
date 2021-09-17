package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.UserManager;

/**
 * JUnit Test Case for Get Appointments
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 *
 */
class TestGetAppointments {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request.equals("GET_APPOINTMENTS,{\"patient\":\"jimmy1\"}")) {
				return "[{\"date\":\"2021-09-02T14:00\",\"notes\":\"Help\",\"patient\":\"jimmy1\",\"location\":\"TLC\",\"medicalPersonnel\":\"Person B\"},{\"date\":\"2021-09-03T13:00\",\"notes\":\"Help\",\"patient\":\"jimmy1\",\"location\":\"TLC\",\"medicalPersonnel\":\"Person A\"},{\"date\":\"2021-10-01T13:00\",\"notes\":\"sssss\",\"patient\":\"jimmy1\",\"location\":\"TLC\",\"medicalPersonnel\":\"Person A\"}]";
			}
			if (request.equals("GET_USER_BY_USERNAME,{\"patient\":\"jimmy1\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"PATIENT\",\"userName\":\"jimmy1\",\"firstName\":\"Jimmy\",\"password\":\"11111\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			} else {
				return "ERROR";
			}
		}
	}

	@Test
	void testValidGetAppointments() {
		String username = "jimmy1";
		UserManager userManager = new UserManager(new ServerFake());
		List<Appointment> appointments = userManager.getAppointments(username);
		assertEquals(appointments.size(), 3);
	}

	@Test
	void testInvalidGetAppointments() {
		String username = "jimm1";
		UserManager userManager = new UserManager(new ServerFake());
		List<Appointment> appointments = userManager.getAppointments(username);
		assertEquals(0, appointments.size());
	}

}
