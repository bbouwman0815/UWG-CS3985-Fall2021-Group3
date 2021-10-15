package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.UserManager;

class TestGetPatientsForMedicalPersonnel {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request.equals("GET_USER_BY_USERNAME,{\"patient\":\"jimmy1\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"PATIENT\",\"userName\":\"jimmy1\",\"firstName\":\"Jimmy\",\"password\":\"11111\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			}
			if (request.equals("GET_USER_BY_USERNAME,{\"patient\":\"bob2\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"PATIENT\",\"userName\":\"jimmy1\",\"firstName\":\"Jimmy\",\"password\":\"11111\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			}
			if (request.equals("GET_MEDICAL_PERSONNELS_PATIENTS,{\"medicalPersonnel\":\"jdoe\"}")) {
				return "[\"jimmy1\",\"bob2\"]";
			} else {
				return "ERROR";
			}
		}
	}

	@Test
	void testValidMedicalPersonnel() throws ParseException {
		String username = "jdoe";
		UserManager userManager = new UserManager(new ServerFake());
		UserManager.setUserManager(userManager);
		List<Patient> list = userManager.getPatientsForMedicalPersonnel(username);
		assertEquals(2, list.size());
	}
	
	@Test
	void testInValidMedicalPersonnel() throws ParseException {
		String username = "suzanna1";
		UserManager userManager = new UserManager(new ServerFake());
		UserManager.setUserManager(userManager);
		List<Patient> list = userManager.getPatientsForMedicalPersonnel(username);
		assertEquals(0, list.size());
	}
}
