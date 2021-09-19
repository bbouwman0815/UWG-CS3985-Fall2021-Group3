package edu.westga.cs4985.clinicApp.server;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TestAddPatient {

	@SuppressWarnings("unchecked")
	@Test
	public void testUpdatePetientGeneralInfo() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("type", "PATIENT");
		json.put("userName", "test");
		json.put("password", "11111");
		json.put("firstName", "Jimmy");
		json.put("lastName", "Daniels");
		json.put("gender", "male");
		json.put("dateOfBirth", "1990-09-29");
		json.put("address1", "3433 Atlanta Peachway");
		json.put("address2", "New");
		json.put("city", "Carrollton");
		json.put("state", "GA");
		json.put("country", "USA");
		json.put("race", "American Indian or Alaska Native");
		json.put("ethnicty", "Not Hispanic or Latino");
		json.put("phoneNumber", "123456789");
		json.put("email", "jimmy12334@gmail.com");
		json.put("insurance", "8888888888");
		json.put("caregiver", "Caregiver C");
		assertEquals("ADDED", server.addPatientUser(json.toJSONString()));
	}
}
