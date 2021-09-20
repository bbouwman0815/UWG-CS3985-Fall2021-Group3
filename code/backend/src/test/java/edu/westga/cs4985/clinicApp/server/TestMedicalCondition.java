package edu.westga.cs4985.clinicApp.server;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TestMedicalCondition {
	
	@SuppressWarnings("unchecked")
	@Test
	public void testAddMedicalCondition() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("patient", "test1");
		json.put("name","new");
		json.put("diagnosisDate", "2021-09-16");
		json.put("terminationDate", "2021-08-16");
		json.put("notes", "new");
		assertEquals("ADDED", server.addMedicalCondition(json.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testACancelMedicalCondition() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("patient", "test1");
		json.put("name","new");
		json.put("diagnosisDate", "2021-09-16");
		json.put("terminationDate", "2021-08-16");
		json.put("notes", "new");
		assertEquals("Removed", server.removeMedicalCondition(json.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testMedicalConditionNullPatient() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("patient", "test1111");
		json.put("name","78");
		json.put("diagnosisDate", "2021-09-16");
		json.put("terminationDate", "2021-08-16");
		json.put("notes", "new");
		assertEquals("Removed", server.removeMedicalCondition(json.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testMedicalConditionNullName() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("patient", "tes0t1");
		json.put("name","new111");
		json.put("diagnosisDate", "2021-09-16");
		json.put("terminationDate", "2021-08-16");
		json.put("notes", "new");
		assertEquals("Removed", server.removeMedicalCondition(json.toJSONString()));
	}
	
	
	
}
