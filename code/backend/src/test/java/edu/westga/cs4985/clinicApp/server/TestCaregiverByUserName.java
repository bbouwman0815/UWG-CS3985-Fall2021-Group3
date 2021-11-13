package edu.westga.cs4985.clinicApp.server;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class TestCaregiverByUserName {
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetSUer() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("type", "Caregiver");
		json.put("userName", "testc");
		json.put("password", "11111");
		json.put("firstName", "test");
		json.put("lastName", "test");
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
		

		JSONObject json1 = new JSONObject();
		json1.put("caregiver", "testc");
		assertEquals(json.toJSONString(), server.getCaregiverByUserName(json1.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPaseError() throws IOException, ParseException {
		Server server = new Server();
		
		JSONObject json1 = new JSONObject();
		json1.put(null, null);
		assertEquals("ERROR", server.getCaregiverByUserName(json1.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPatientError() throws IOException, ParseException {
		Server server = new Server();
		
		JSONObject json1 = new JSONObject();
		json1.put("patient", "new");
		assertEquals("ERROR", server.getCaregiverByUserName(json1.toJSONString()));
	}

}
