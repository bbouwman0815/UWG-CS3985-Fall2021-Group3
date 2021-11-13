package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.Caregiver;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.UserManager;

class TestGetPatientsForCaregiver {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;
			if (request.equals("GET_CAREGIVER_PATIENTS,{\"caregiver\":\"timi1\"}")) {
				return "[\"jimmy1\",\"bbouwman0815\",\"bob2\"]";
			}
			if (request.equals("GET_USER_BY_USERNAME,{\"patient\":\"jimmy1\"}")) {
				return "{\"insurance\":\"234\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"white\",\"address2\":\"\",\"city\":\"Atlanta\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-01\",\"type\":\"PATIENT\",\"userName\":\"jimmy1\",\"firstName\":\"Jimmy\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Hispanic or Latino\",\"caregiver\":\"timi1\",\"state\":\"GA\",\"email\":\"jimmy@gmail.com\"}";
			}
			if (request.equals("GET_USER_BY_USERNAME,{\"patient\":\"bbouwman0815\"}")) {
				return "{\"insurance\":\"United Healthcare\",\"lastName\":\"Bouwman\",\"country\":\"United States\",\"gender\":\"Male\",\"race\":\"White\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"912 Lovvorn Road\",\"dateOfBirth\":\"2021-09-15\",\"type\":\"PATIENT\",\"userName\":\"bbouwman0815\",\"firstName\":\"Brian\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"6665554444\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"timi1\",\"state\":\"GA\",\"email\":\"bbouwman0815@yahoo.com\"}";
			}
			if (request.equals("GET_USER_BY_USERNAME,{\"patient\":\"bob2\"}")) {
				return "{\"insurance\":\"23232\",\"lastName\":\"Good see you\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"white\",\"address2\":\"\",\"city\":\"Atlanta\",\"address1\":\"4544 Atlanta Peachway\",\"dateOfBirth\":\"1995-10-01\",\"type\":\"PATIENT\",\"userName\":\"bob2\",\"firstName\":\"I'am new\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"987654321\",\"ethnicty\":\"Hispanic or Latino\",\"caregiver\":\"timi1\",\"state\":\"GA\",\"email\":\"bob@gmail.com\"}";
			}
			if (request.equals("GET_CAREGIVER_BY_USER_NAME,{\"caregiver\":\"timi1\"}")) {
				return "{\"lastName\":\"liu\",\"country\":\"Bhutan\",\"gender\":\"Male\",\"race\":\"Asian\",\"address2\":\"123\",\"city\":\"Carronllton\",\"address1\":\"1601 Maple St\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"Caregiver\",\"userName\":\"timi1\",\"firstName\":\"timi\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"4444444444\",\"ethnicty\":\"Hispanic or Latino\",\"state\":\"GA\",\"email\":\"temail@email\"}";
			}
			if (request.equals(
					"UPDATE_CAREGIVER_PATIENTS,{\"caregiver\":\"timi1\",\"patients\":[\"jimmy1\",\"bbouwman0815\",\"bob2\"]}")) {
				return "Updated";
			}
			if (request.equals(
					"GET_ALL_CAREGIVERS,GET_ALL_CAREGIVERS")) {
				return "[{\"lastName\":\"liu\",\"country\":\"Bhutan\",\"gender\":\"Male\",\"race\":\"Asian\",\"address2\":\"123\",\"city\":\"Carronllton\",\"address1\":\"1601 Maple St\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"Caregiver\",\"userName\":\"timi1\",\"firstName\":\"timi\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"4444444444\",\"ethnicty\":\"Hispanic or Latino\",\"state\":\"GA\",\"email\":\"temail@email\"}]";
			} else {
				return "ERROR";
			}
		}
	}

	@Test
	void testGetPatientsForCaregiver() throws ParseException {
		UserManager userManager = new UserManager(new ServerFake());
		userManager.userManager = userManager;
		Patient patient = (Patient) userManager.getUserByUserName("bbouwman0815");
		assertEquals(patient.getFirstName(), "Brian");
		Caregiver caregiver = (Caregiver) userManager.getCaregiverByUserName("timi1");
		assertEquals(caregiver.getFirstName(), "timi");
		List<Patient> patients = userManager.getPatientsForCaregiver("timi1");
		assertEquals(3, patients.size());
		userManager.updateCaregiverPatients(caregiver, patients);
		assertEquals(3, patients.size());
	}

	@Test
	void testGetAllCaregivers() throws ParseException {
		UserManager userManager = new UserManager(new ServerFake());
		userManager.userManager = userManager;
		List<Caregiver> caregivers = userManager.getAllCaregivers();
		assertEquals(1, caregivers.size());
	}
}
