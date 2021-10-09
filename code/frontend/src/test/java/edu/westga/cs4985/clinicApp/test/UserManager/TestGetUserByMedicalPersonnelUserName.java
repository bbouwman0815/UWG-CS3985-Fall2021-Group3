package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.UserManager;

public class TestGetUserByMedicalPersonnelUserName {
	
	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request.equals("GET_USER_BY_MEDICAL_PERSONNEL_USERNAME,{\"medicalPersonnel\":\"new\"}")) {
				return "{\"zipcode\":\"30118\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"MedicalPersonnel\",\"userName\":\"new\",\"firstName\":\"Jimmy\",\"password\":\"11111\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			} else {
				return "ERROR";
			}
		}
	}

	@Test
	void testValidUsername() throws ParseException {
		String username = "new";
		UserManager userManager = new UserManager(new ServerFake());
		MedicalPersonnel user = (MedicalPersonnel) userManager.getUserByMedicalPersonnelUserName(username);
		MedicalPersonnel medicalPersonnel = new MedicalPersonnel("Jimmy", "Bob", "male", "1990-09-29", "3433 Atlanta Peachway", "", "Carrollton",
				"GA", "USA", "American Indian or Alakska Native", "Not Hispanic or Latino",
				"123456789", "jimmy12334@gmail.com", "new", "11111","30118");
		assertEquals(user.getFirstName(), medicalPersonnel.getFirstName());
		assertEquals(user.getLastName(), medicalPersonnel.getLastName());
	}

	@Test
	void testInvalidUsername() throws ParseException {
		String username = "jimm1";
		UserManager userManager = new UserManager(new ServerFake());
		MedicalPersonnel user = (MedicalPersonnel) userManager.getUserByMedicalPersonnelUserName(username);
		assertEquals(null, user);
	}
}
