package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.UserManager;

public class TestGetAllMedicalPersonnels {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			
			if (request.equals("GET_ALL_MEDICAL_PERSONNELS,{\"zipcode\":\"30118\"}")) {
				return "[{\"lastName\":\"asdfasdf\",\"country\":\"Bhutan\",\"gender\":\"Female\",\"race\":\"Asian\",\"address2\":\"123\",\"city\":\"Carronllton\",\"address1\":\"1601 Maple St\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"MedicalPersonnel\",\"userName\":\"admin\",\"zipcode\":\"30118\",\"firstName\":\"asdfasdf\",\"password\":\"123\",\"phoneNumber\":\"4444444444\",\"ethnicty\":\"Hispanic or Latino\",\"caregiver\":\"\",\"state\":\"GA\",\"email\":\"temail@email\"}]";
			} else {
				return "ERROR";
			}
		}
	}

	@Test
	void testValidUsername() throws ParseException {
		String username = "30118";
		UserManager userManager = new UserManager(new ServerFake());
		 List<MedicalPersonnel> list = userManager.getAllMedicalPersonnels(username);
		assertEquals(1, list.size());
	}

	@Test
	void testInvalidUsername() throws ParseException {
		String username = "jimm1";
		UserManager userManager = new UserManager(new ServerFake());
		 List<MedicalPersonnel> list = userManager.getAllMedicalPersonnels(username);
		assertEquals(0, list.size());
	}
}
