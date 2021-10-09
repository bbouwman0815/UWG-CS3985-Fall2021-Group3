package edu.westga.cs4985.clinicApp.server;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class TestGetAppointmentsForMedicalPersonnel {

	@SuppressWarnings("unchecked")
	@Test
	public void testGetAppointments() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "test");
		json.put("patient", "new");
		json.put("date", "2021-11-01T13:00");
		json.put("location", "TCL");
		json.put("notes", "new");
		
		JSONArray list = new JSONArray();
		list.add(json);
		JSONObject json1 = new JSONObject();
		json1.put("medicalPersonnel", "test");
		server.bookAppointment(json.toJSONString());
		assertEquals(list.toJSONString(), server.getAppointmentsForMedicalPersonnel(json1.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void tesCancelAppointment() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "test");
		json.put("patient", "new");
		json.put("date", "2021-11-01T13:00");
		json.put("location", "TCL");
		json.put("notes", "new");
		server.cancleAppointment(json.toJSONString());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPatientError() throws IOException, ParseException {
		Server server = new Server();

		JSONObject json1 = new JSONObject();
		json1.put("medicalPersonnel", "newnew");
		assertEquals("[]", server.getAppointmentsForMedicalPersonnel(json1.toJSONString()));
	}
}
