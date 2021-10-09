package edu.westga.cs4985.clinicApp.server;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class TesGetUserByMedicalPersonnelUserName {

	@SuppressWarnings("unchecked")
	@Test
	public void testGetUserByMedicalPersonnelUserName() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("type", "MedicalPersonnel");
		json.put("userName", "test001");
		json.put("password", "123");
		json.put("firstName", "test");
		json.put("lastName", "test");
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
		

		JSONObject json1 = new JSONObject();
		json1.put("medicalPersonnel", "test001");
		assertEquals(json.toJSONString(), server.getUserByMedicalPersonnelUserName(json1.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testErrorGetUserByMedicalPersonnelUserName() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("type", "MedicalPersonnel");
		json.put("userName", "test001");
		json.put("password", "123");
		json.put("firstName", "test");
		json.put("lastName", "test");
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
		

		JSONObject json1 = new JSONObject();
		json1.put("MedicalPersonnel", "test001");
		assertEquals("ERROR", server.getUserByMedicalPersonnelUserName(json1.toJSONString()));
	}
}
