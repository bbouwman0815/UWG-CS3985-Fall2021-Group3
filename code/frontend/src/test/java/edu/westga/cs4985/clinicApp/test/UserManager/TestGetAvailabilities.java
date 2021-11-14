package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.UserManager;

public class TestGetAvailabilities {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			
			if (request.equals("GET_AVAILABILITIES,{\"medicalPersonnel\":\"new\"}")) {
				return "[\"2021-10-28T17:00\"]";
			} else {
				return "ERROR";
			}
		}
	}

	@Test
	void testValidUsername() throws ParseException {
		String username = "new";
		UserManager userManager = new UserManager(new ServerFake());
		 List<LocalDateTime> list = userManager.getAvailabilities(username);
		assertEquals(1, list.size());
	}

	@Test
	void testInvalidUsername() throws ParseException {
		String username = "jimm1";
		UserManager userManager = new UserManager(new ServerFake());
		 List<LocalDateTime> list = userManager.getAvailabilities(username);
		assertEquals(0, list.size());
	}
}
