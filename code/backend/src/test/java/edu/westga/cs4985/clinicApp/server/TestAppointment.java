package edu.westga.cs4985.clinicApp.server;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class TestAppointment {

	@SuppressWarnings("unchecked")
	@Test
	public void testBookAppointment() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "Person A");
		json.put("patient", "jimmy1");
		json.put("date", "2021-11-01T13:00");
		json.put("location", "TCL");
		json.put("notes", "new");
		assertEquals("ADDED", server.bookAppointment(json.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCancelAppointmentExist() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "Person A");
		json.put("patient", "jimmy1");
		json.put("date", "2021-11-01T13:00");
		json.put("location", "TCL");
		json.put("notes", "new");
		assertEquals("Removed", server.cancleAppointment(json.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCancelAppointmentDateDonotMatch() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "Person A");
		json.put("patient", "jimmy1");
		json.put("date", "2021-10-01T13:00");
		json.put("location", "TCL");
		json.put("notes", "new");
		assertEquals("Removed", server.cancleAppointment(json.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCancelAppointmentMedicalPersonnelDonotMatch() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "A");
		json.put("patient", "jimmy1");
		json.put("date", "2021-11-01T13:00");
		json.put("location", "TCL");
		json.put("notes", "new");
		assertEquals("Removed", server.cancleAppointment(json.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCancelAppointmentDateMatch() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "Person A");
		json.put("patient", "jimmy");
		json.put("date", "2021-11-01T13:00");
		json.put("location", "TCL");
		json.put("notes", "new");
		assertEquals("Removed", server.cancleAppointment(json.toJSONString()));
	}
}
