package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.UserManager;

public class TestUpdateMedicalPersonnelAvaiabilities {
	
	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			
			if (request.equals("UPDATE_AVAILABILITY,{\"availabilityList\":[],\"medicalPersonnel\":\"new\"}")) {
				return "Updated";
			} else {
				return "ERROR";
			}
		}
	}

	@Test
	void testValidUsername() throws ParseException {
		UserManager userManager = new UserManager(new ServerFake());
		MedicalPersonnel medicalPersonnel = new MedicalPersonnel("Jimmy", "Bob", "male", "1990-09-29", "3433 Atlanta Peachway", "", "Carrollton",
				"GA", "USA", "American Indian or Alakska Native", "Not Hispanic or Latino",
				"123456789", "jimmy12334@gmail.com", "new", "11111","30118");
		assertEquals(true, userManager.updateMedicalPersonnelAvaiabilities(medicalPersonnel, new ArrayList<LocalDateTime>()));
	}

	@Test
	void testInvalidUsername() throws ParseException {
		UserManager userManager = new UserManager(new ServerFake());
		MedicalPersonnel medicalPersonnel = new MedicalPersonnel("Jimmy", "Bob", "male", "1990-09-29", "3433 Atlanta Peachway", "", "Carrollton",
				"GA", "USA", "American Indian or Alakska Native", "Not Hispanic or Latino",
				"123456789", "jimmy12334@gmail.com", "55", "11111","30118");
		assertEquals(false, userManager.updateMedicalPersonnelAvaiabilities(medicalPersonnel, new ArrayList<LocalDateTime>()));
	}

}
