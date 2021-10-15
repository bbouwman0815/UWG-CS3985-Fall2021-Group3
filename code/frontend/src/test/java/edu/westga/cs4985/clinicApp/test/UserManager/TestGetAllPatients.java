package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.UserManager;

class TestGetAllPatients {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request.equals("GET_ALL_PATIENTS,GET_ALL_PATIENTS")) {
				return "[{\"insurance\":\"234\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"white\",\"address2\":\"\",\"city\":\"Atlanta\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-01\",\"userName\":\"jimmy1\",\"type\":\"PATIENT\",\"firstName\":\"Jimmy\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Hispanic or Latino\",\"caregiver\":\"jdoe\",\"state\":\"GA\",\"email\":\"jimmy@gmail.com\"},{\"insurance\":\"23232\",\"lastName\":\"Good see you\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"white\",\"address2\":\"\",\"city\":\"Atlanta\",\"address1\":\"4544 Atlanta Peachway\",\"dateOfBirth\":\"1995-10-01\",\"userName\":\"bob2\",\"type\":\"PATIENT\",\"firstName\":\"I'am new\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"987654321\",\"ethnicty\":\"Hispanic or Latino\",\"caregiver\":\"\",\"state\":\"GA\",\"email\":\"bob@gmail.com\"},{\"insurance\":\"United Healthcare\",\"lastName\":\"Bouwman\",\"country\":\"United States\",\"gender\":\"Male\",\"race\":\"White\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"912 Lovvorn Road\",\"dateOfBirth\":\"2021-09-15\",\"type\":\"PATIENT\",\"userName\":\"bbouwman0815\",\"firstName\":\"Brian\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"6665554444\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"\",\"state\":\"GA\",\"email\":\"bbouwman0815@yahoo.com\"},{\"insurance\":\"8888888888\",\"lastName\":\"test\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"New\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"PATIENT\",\"userName\":\"testp\",\"firstName\":\"test\",\"password\":\"11111\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}]";
			} else {
				return "ERROR";
			}
		}
	}

	@Test
	void testGetPatientsSuccess() throws ParseException {
		UserManager userManager = new UserManager(new ServerFake());
		List<Patient> list = userManager.getAllPatients();
		assertEquals(4, list.size());
	}
}
