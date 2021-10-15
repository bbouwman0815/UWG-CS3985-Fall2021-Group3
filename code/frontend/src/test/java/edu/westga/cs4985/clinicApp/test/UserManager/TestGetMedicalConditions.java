package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.MedicalCondition;
import edu.westga.cs4985.clinicApp.model.UserManager;

class TestGetMedicalConditions {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request.equals("GET_MEDICAL_CONDITIONS,{\"patient\":\"jimmy1\"}")) {
				return "[{\"terminationDate\":\"2021-09-22\",\"notes\":\"medicalCondition 1 notes\",\"patient\":\"jimmy1\",\"diagnosisDate\":\"2021-09-01\",\"name\":\"medicalCondition1\"},{\"terminationDate\":\"2021-09-22\",\"notes\":\"medicalCondition 1 notes\",\"patient\":\"jimmy1\",\"diagnosisDate\":\"2021-09-01\",\"name\":\"medicalCondition2\"},{\"terminationDate\":\"2021-09-22\",\"notes\":\"medicalCondition 1 notes\",\"patient\":\"jimmy1\",\"diagnosisDate\":\"2021-09-01\",\"name\":\"medicalCondition3\"}]";
			}
			if (request.equals("GET_USER_BY_USERNAME,{\"patient\":\"jimmy1\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"PATIENT\",\"userName\":\"jimmy1\",\"firstName\":\"Jimmy\",\"password\":\"11111\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			} else {
				return "ERROR";
			}
		}
	}

	@Test
	void testGetValidMedicalConditions() throws ParseException {
		String username = "jimmy1";
		// UserManager userManager = new UserManager();
		UserManager userManager = new UserManager(new ServerFake());
		List<MedicalCondition> medicalConditions = userManager.getMedicalConditions(username);
		assertEquals(medicalConditions.size(), 3);
	}

	@Test
	void testGetInvalidMedicalConditions() throws ParseException {
		String username = "jimm1";
		UserManager userManager = new UserManager(new ServerFake());
		List<MedicalCondition> medicalConditions = userManager.getMedicalConditions(username);
		assertEquals(0, medicalConditions.size());
	}

}
