package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.UserManager;

public class TestUpdateMedicalPersonnelAvaiabilitiesWithDateData {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			String result = "";
			if (request.equals("UPDATE_AVAILABILITY,{\"availabilityList\":[\"2021-10-06T12:00\"],\"medicalPersonnel\":\"new\"}")) {
				result =  "Updated";
			}
			return result;
		}
	}

	@Test
	void testValidUsername() throws ParseException {
		UserManager userManager = new UserManager(new ServerFake());
		MedicalPersonnel medicalPersonnel = new MedicalPersonnel("Jimmy", "Bob", "male", "1990-09-29", "3433 Atlanta Peachway", "", "Carrollton",
				"GA", "USA", "American Indian or Alakska Native", "Not Hispanic or Latino",
				"123456789", "jimmy12334@gmail.com", "new", "11111","30118");
		List<LocalDateTime> list = new ArrayList<LocalDateTime>();
		list.add(LocalDateTime.of(2021, 10, 06, 12, 00));
		assertEquals(true, userManager.updateMedicalPersonnelAvaiabilities(medicalPersonnel,list));
	}
}
