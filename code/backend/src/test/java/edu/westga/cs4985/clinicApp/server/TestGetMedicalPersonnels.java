package edu.westga.cs4985.clinicApp.server;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class TestGetMedicalPersonnels {

	@SuppressWarnings("unchecked")
	@Test
	public void testGetUserByMedicalPersonnelUserName() throws IOException, ParseException {
		Server server = new Server();
		JSONArray medicalPersonnel = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("type", "MedicalPersonnel");
		json.put("userName", "testm");
		json.put("password", "123");
		json.put("firstName", "Test");
		json.put("lastName", "MedicalPersonnel");
		json.put("gender", "Female");
		json.put("dateOfBirth", "1990-09-29");
		json.put("address1", "1601 Maple St");
		json.put("address2", "123");
		json.put("city", "Carronllton");
		json.put("state", "GA");
		json.put("country", "Bhutan");
		json.put("race", "Asian");
		json.put("ethnicty", "Hispanic or Latino");
		json.put("phoneNumber", "4444444444");
		json.put("email", "temail@email");
		json.put("zipcode", "11111");
		medicalPersonnel.add(json);

		JSONObject json1 = new JSONObject();
		json1.put("zipcode", "11111");
		assertEquals(medicalPersonnel.toJSONString(), server.getMedicalPersonnels(json1.toJSONString()));
	}
	
}
