package edu.westga.cs4985.clinicApp.server;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class TestUpdateAppointment {

	@SuppressWarnings("unchecked")
	@Test
	public void testUpdateAppointment() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "testm");
		json.put("patient", "testp");
		json.put("date", "2021-11-01T13:00");
		json.put("location", "TCL");
		json.put("notes", "");
		assertEquals("Updated", server.updateAppointment(json.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdateAppointmentDateDonotMatch() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "testm");
		json.put("patient", "test");
		json.put("date", "2021-12-01T13:00");
		json.put("location", "TCL");
		json.put("notes", "new");
		assertEquals("Updated", server.updateAppointment(json.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdateAppointmentPatientDateDonotMatch() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "test");
		json.put("patient", "testp");
		json.put("date", "2021-12-01T13:00");
		json.put("location", "TCL");
		json.put("notes", "new");
		assertEquals("Updated", server.updateAppointment(json.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdateAppointmentMedicalPersonnelDonotMatch() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "test");
		json.put("patient", "test");
		json.put("date", "2021-11-01T13:00");
		json.put("location", "TCL");
		json.put("notes", "new");
		assertEquals("Updated", server.updateAppointment(json.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdateAppointmentDateMatch() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "testm");
		json.put("patient", "testp");
		json.put("date", "2021-12-01T13:00");
		json.put("location", "TCL");
		json.put("notes", "new");
		assertEquals("Updated", server.updateAppointment(json.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdateAppointmentDatenoMatch() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "testm");
		json.put("patient", "test");
		json.put("date", "2021-11-01T13:00");
		json.put("location", "TCL");
		json.put("notes", "new");
		assertEquals("Updated", server.updateAppointment(json.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdateAppointmentDatenMatch() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "test");
		json.put("patient", "testp");
		json.put("date", "2021-11-01T13:00");
		json.put("location", "TCL");
		json.put("notes", "new");
		assertEquals("Updated", server.updateAppointment(json.toJSONString()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdateAppointmentAllNoMatch() throws IOException, ParseException {
		Server server = new Server();
		JSONObject json = new JSONObject();
		json.put("medicalPersonnel", "test");
		json.put("patient", "test");
		json.put("date", "2021-12-01T13:00");
		json.put("location", "TCL");
		json.put("notes", "new");
		assertEquals("Updated", server.updateAppointment(json.toJSONString()));
	}	
	

}
