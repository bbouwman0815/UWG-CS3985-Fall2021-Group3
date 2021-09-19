package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.UserManager;

/**
 * JUnit Test Case for get user by name
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 *
 */
class TestGetUserByName {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request.equals("GET_USER_BY_USERNAME,{\"patient\":\"jimmy1\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"PATIENT\",\"userName\":\"jimmy1\",\"firstName\":\"Jimmy\",\"password\":\"11111\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			} else {
				return "ERROR";
			}
		}
	}

	@Test
	void testValidUsername() throws ParseException {
		String username = "jimmy1";
		UserManager userManager = new UserManager(new ServerFake());
		Patient user = (Patient) userManager.getUserByUserName(username);
		Patient patient = new Patient("Jimmy", "Bob", "male", "1990-09-29", "3433 Atlanta Peachway", "", "Carrollton",
				"GA", "USA", "American Indian or Alakska Native", "Not Hispanic or Latino", "jimmy1", "11111",
				"123456789", "jimmy12334@gmail.com", "8888888888");
		assertEquals(user.getFirstName(), patient.getFirstName());
		assertEquals(user.getLastName(), patient.getLastName());
	}

	@Test
	void testInvalidUsername() throws ParseException {
		String username = "jimm1";
		UserManager userManager = new UserManager(new ServerFake());
		Patient user = (Patient) userManager.getUserByUserName(username);
		assertEquals(null, user);
	}

}
