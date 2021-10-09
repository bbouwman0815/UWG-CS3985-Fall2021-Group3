package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.UserManager;

public class TestGetAppointmentsForMedicalPersonnel {
	
	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request.equals("GET_USER_BY_USERNAME,{\"patient\":\"jimmy1\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"PATIENT\",\"userName\":\"jimmy1\",\"firstName\":\"Jimmy\",\"password\":\"11111\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			}
			if (request.equals("GET_USER_BY_MEDICAL_PERSONNEL_USERNAME,{\"medicalPersonnel\":\"new\"}")) {
				return "{\"zipcode\":\"30118\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"MedicalPersonnel\",\"userName\":\"new\",\"firstName\":\"liu\",\"password\":\"11111\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			} 
			if (request.equals("GET_APPOINTMENTS_FOR_MEDICAL_PEROSNNEL,{\"medicalPersonnel\":\"new\"}")) {
				return "[{\"date\":\"2021-09-01T14:00\",\"notes\":\"help\",\"patient\":\"jimmy1\",\"location\":\"TCL\",\"medicalPersonnel\":\"new\"}]";
			} else {
				return "ERROR";
			}
		}
	}

	@Test
	void testValidUsername() throws ParseException {
		String username = "new";
		UserManager userManager = new UserManager(new ServerFake());
		List<Appointment> list = userManager.getAppointmentsForMedicalPersonnel(username);
		assertEquals(1, list.size());
	}

	@Test
	void testInvalidUsername() throws ParseException {
		String username = "jimm1";
		UserManager userManager = new UserManager(new ServerFake());
		List<Appointment> list = userManager.getAppointmentsForMedicalPersonnel(username);
		assertEquals(0, list.size());
	}


}
