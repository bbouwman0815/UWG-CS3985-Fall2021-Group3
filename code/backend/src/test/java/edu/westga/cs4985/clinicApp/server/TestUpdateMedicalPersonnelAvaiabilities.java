package edu.westga.cs4985.clinicApp.server;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class TestUpdateMedicalPersonnelAvaiabilities {

	@SuppressWarnings("unchecked")
	@Test
	public void testUpdateMedicalPersonnelAvaiabilities() throws IOException, ParseException {
		List<LocalDateTime> dayTimes = new ArrayList<LocalDateTime>();
		dayTimes.add(LocalDateTime.of(2024,9,01,14,00));
		dayTimes.add(LocalDateTime.of(2024,10,01,14,00));
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "test");
		JSONArray availability = new JSONArray();
		for (LocalDateTime dayTime : dayTimes) {
			availability.add(dayTime.toString());
		}
		json.put("availabilityList", availability);
		
		assertEquals("Updated", server.updateMedicalPersonnelAvaiabilities(json.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdateExistMedicalPersonnelAvaiabilities() throws IOException, ParseException {
		List<LocalDateTime> dayTimes = new ArrayList<LocalDateTime>();
		dayTimes.add(LocalDateTime.of(2024,9,01,14,00));
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "test");
		JSONArray availability = new JSONArray();
		for (LocalDateTime dayTime : dayTimes) {
			availability.add(dayTime.toString());
		}
		json.put("availabilityList", availability);
		
		assertEquals("Updated", server.updateMedicalPersonnelAvaiabilities(json.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdateNullMedicalPersonnelAvaiabilities() throws IOException, ParseException {
		List<LocalDateTime> dayTimes = new ArrayList<LocalDateTime>();
		dayTimes.add(LocalDateTime.of(2024,9,01,14,00));
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "test1");
		JSONArray availability = new JSONArray();
		for (LocalDateTime dayTime : dayTimes) {
			availability.add(dayTime.toString());
		}
		json.put("availabilityList", availability);
		
		assertEquals("Updated", server.updateMedicalPersonnelAvaiabilities(json.toJSONString()));
	}
	
	@Test
	public void removeTest() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/availabilities.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);

		FileWriter writer = new FileWriter("./jsonFiles/availabilities.json");
		JSONObject result = null;
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("medicalPersonnel").equals("test1")) {
				result = parseData;
			}
		}
		jsonObject.remove(result);
		
		
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
	}
}
