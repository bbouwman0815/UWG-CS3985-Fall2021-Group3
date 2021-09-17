package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.UserManager;

class testUpdateGeneralInfo {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request.equals("UPDATE_GENERAL_INFORMATION,{\"insurance\":\"123456789\",\"lastName\":\"Daniels\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alakska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"PATIENT\",\"userName\":\"jimmy12334@gmail.com\",\"firstName\":\"Jimmy\",\"password\":\"8888888888\",\"phoneNumber\":\"jimmy1\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"\",\"state\":\"GA\",\"email\":\"11111\"}")) {
				return "UPDATED";
			}
			else {
				return "ERROR";
			}
		}
	}

	@Test
	void testUpdateGeneralInfoSuccess() {
		UserManager userManager = new UserManager(new ServerFake());
		Patient patient = new Patient("Jimmy", "Daniels", "male", "1990-09-29", "3433 Atlanta Peachway", "", "Carrollton",
				"GA", "USA", "American Indian or Alakska Native", "Not Hispanic or Latino", "jimmy1", "11111",
				"123456789", "jimmy12334@gmail.com", "8888888888");
		boolean updated = userManager.updatePatientGeneralInfo(patient);
		assertEquals(true, updated);
	}
	
	@Test
	void testUpdateGeneralInfoFail() {
		UserManager userManager = new UserManager(new ServerFake());
		Patient patient = new Patient("Jimmy", "Daniels", "male", "1990-09-29", "3433 Atlanta Peachway", "", "Carrollton",
				"GA", "USA", "American Indian or Alakska Native", "Not Hispanic or Latino", "jimmy1", "11123",
				"123456789", "jimmy12334@gmail.com", "8888888888");
		boolean updated = userManager.updatePatientGeneralInfo(patient);
		assertEquals(false, updated);
	}

}
