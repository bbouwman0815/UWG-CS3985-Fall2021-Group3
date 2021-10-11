package edu.westga.cs4985.clinicApp.server;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class TestGetAvailabilities {

	@SuppressWarnings("unchecked")
	@Test
	public void testGetAvailabilities() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		
		JSONArray availability = new JSONArray();
		availability.add(LocalDateTime.of(2024,9,01,14,00).toString());
		
		json.put("availabilityList", availability);
		JSONObject json1 = new JSONObject();
		json1.put("medicalPersonnel", "testm1");
		assertEquals(json.get("availabilityList").toString(), server.getAvailabilities(json1.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testNullGetAvailabilities() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		
		JSONArray availability = new JSONArray();
		availability.add(LocalDateTime.of(2024,9,01,14,00).toString());
		
		json.put("availabilityList", availability);
		JSONObject json1 = new JSONObject();
		json1.put("test", "testm");
		assertEquals("ERROR", server.getAvailabilities(json1.toJSONString()));
	}
}
