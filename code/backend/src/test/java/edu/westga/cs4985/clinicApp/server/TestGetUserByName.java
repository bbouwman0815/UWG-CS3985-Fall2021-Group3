package edu.westga.cs4985.clinicApp.server;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class TestGetUserByName {

	@SuppressWarnings("unchecked")
	@Test
	public void testGetSUer() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("type", "PATIENT");
		json.put("userName", "testp");
		json.put("password", "11111");
		json.put("firstName", "Patient");
		json.put("lastName", "Test");
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
		

		JSONObject json1 = new JSONObject();
		json1.put("patient", "testp");
		assertEquals(json.toJSONString(), server.getUserByUserName(json1.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPaseError() throws IOException, ParseException {
		Server server = new Server();
		
		JSONObject json1 = new JSONObject();
		json1.put(null, null);
		assertEquals("ERROR", server.getUserByUserName(json1.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPatientError() throws IOException, ParseException {
		Server server = new Server();
		
		JSONObject json1 = new JSONObject();
		json1.put("patient", "new");
		assertEquals("ERROR", server.getUserByUserName(json1.toJSONString()));
	}
	
}
