package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.model.UserManager;

class testLogin {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request.equals("USER_LOGIN,{\"password\":\"11111\",\"userName\":\"jimmy1\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"PATIENT\",\"userName\":\"jimmy1\",\"firstName\":\"Jimmy\",\"password\":\"11111\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			}
			else {
				return "ERROR";
			}
		}
	}

	@Test
	void testValidLogin() {
		String username = "jimmy1";
		String password = "11111";
		UserManager userManager = new UserManager(new ServerFake());
		Patient user = (Patient) userManager.login(username, password);
		Patient patient = new Patient("Jimmy", "Bob", "male", "1990-09-29", "3433 Atlanta Peachway", "", "Carrollton",
				"GA", "USA", "American Indian or Alakska Native", "Not Hispanic or Latino", "jimmy1", "11111",
				"123456789", "jimmy12334@gmail.com", "8888888888");
		assertEquals(user.getFirstName(), patient.getFirstName());
		assertEquals(user.getLastName(), patient.getLastName());
	}
	
	@Test
	void testInvalidLogin() {
		String username = "jimm1";
		String password = "11111";
		UserManager userManager = new UserManager(new ServerFake());
		Patient user = (Patient) userManager.login(username, password);
		assertEquals(null, user);
	}

}
