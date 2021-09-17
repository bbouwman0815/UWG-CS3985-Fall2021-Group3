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
 * JUnit Test Case for Book Valid Appointment
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 *
 */
class TestBookAppointment {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request.equals(
					"BOOK_APPOINTMENT,{\"date\":\"2021-09-01T14:00\",\"notes\":\"help\",\"patient\":\"New\",\"location\":\"TCL\",\"medicalPersonnel\":\"Person A\"}")) {
				return "ADDED";
			}
			if (request.equals("GET_USER_BY_USERNAME,{\"patient\":\"jimmy1\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"PATIENT\",\"userName\":\"jimmy1\",\"firstName\":\"Jimmy\",\"password\":\"11111\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
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
	void testBookValidAppointment() {
		LocalDateTime dateTime = LocalDateTime.of(2021, 9, 01, 14, 00);
		Patient patient = this.patientDummy();
		Appointment appointment = new Appointment(dateTime, patient, "Person A", "TCL", "help");
		UserManager userManager = new UserManager(new ServerFake());
		boolean added = userManager.bookAppointment(appointment);
		assertEquals(true, added);
	}

	@Test
	void testBookInvalidAppointment() {
		Appointment appointment = null;
		UserManager userManager = new UserManager(new ServerFake());
		boolean added = userManager.bookAppointment(appointment);
		assertEquals(false, added);
	}

}
