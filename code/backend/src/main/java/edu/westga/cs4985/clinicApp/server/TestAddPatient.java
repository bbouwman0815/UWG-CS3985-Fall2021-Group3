package edu.westga.cs4985.clinicApp.server;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TestAddPatient {

	@SuppressWarnings("unchecked")
	@Test
	public void testUpdatePetientGeneralInfo() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("type", "PATIENT");
		json.put("userName", "newuser");
		json.put("password", "11111");
		json.put("firstName", "newuser");
		json.put("lastName", "newuser");
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
		
		
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/users.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);

		FileWriter writer = new FileWriter("./jsonFiles/users.json");

		JSONObject result = null;
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("userName").equals(json.get("userName"))
					&& parseData.get("password").equals(json.get("password"))) {
				result = parseData;
			}
		}
		if(result != null) {
			jsonObject.remove(result);
		}
		
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
	}
}
