package edu.westga.cs4985.clinicApp.server;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TestGetMedicalConditions {

	@SuppressWarnings("unchecked")
	@Test
	public void testGetMedicalCondition() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("patient", "test");
		json.put("name","asdfasdf");
		json.put("diagnosisDate", "2021-09-02");
		json.put("terminationDate", "2021-09-23");
		json.put("notes", "asdfasdf");
		
		JSONArray jsonObject = new JSONArray();
		jsonObject.add(json);
		JSONObject json1 = new JSONObject();
		json1.put("patient", "test");
		assertEquals(jsonObject.toJSONString(), server.getMedicalConditions(json1.toJSONString()));
	}
}
