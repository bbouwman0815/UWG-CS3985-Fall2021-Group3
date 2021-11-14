package edu.westga.cs4985.clinicApp.test.model.appointment;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

/**
 * Test Appointment Constructor
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class TestAppointmentConstructor {
	
	public MedicalPersonnel medicalPersonnelDummy() {
		MedicalPersonnel patientDummy = new MedicalPersonnel("Xavier", "Jameson", Gender.SEX[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[1], Ethnicity.ETHNICITY[1],
				"770-111-222", "email@email.com", "New", "New", "30118");
		return patientDummy;
	}
	
	public Patient patientDummy() {
		Patient patientDummy = new Patient("Xavier", "Jameson", Gender.SEX[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[1], Ethnicity.ETHNICITY[1],
				"770-111-222", "email@email.com", "United Healthcare", "New", "New");
		return patientDummy;
	}
	
	@Test
	public void testValidConstructor() {
		Patient patient = this.patientDummy();
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		Appointment appointment = new Appointment(dateTime, patient, medicalPersonnel, "TCL", "help");
		assertAll(() -> assertEquals(dateTime, appointment.getDateTime()),
				() -> assertEquals(medicalPersonnel, appointment.getMedicalPersonnel()),
				() -> assertEquals("TCL", appointment.getLocation()),
				() -> assertEquals(patient, appointment.getPatient()),
				() -> assertEquals("help", appointment.getNotes()));
	}
	
	@Test
	public void testNullPatient() {
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		assertThrows(IllegalArgumentException.class,
				() -> new Appointment(dateTime, null, medicalPersonnel, "TCL", "help"));
	}
	
	@Test
	public void testNullDateTime() {
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		assertThrows(IllegalArgumentException.class,
				() -> new Appointment(null, this.patientDummy(), medicalPersonnel, "TCL", "help"));
	}

	@Test
	public void testNullMedicalPersonnel() {
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		assertThrows(IllegalArgumentException.class,
				() -> new Appointment(dateTime, this.patientDummy(), null, "TCL", "help"));
	}

	@Test
	public void testNullLocation() {
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		assertThrows(IllegalArgumentException.class,
				() -> new Appointment(dateTime, this.patientDummy(), medicalPersonnel, null, "help"));
	}
	
	@Test
	public void testEmptyLocation() {
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		assertThrows(IllegalArgumentException.class,
				() -> new Appointment(dateTime, this.patientDummy(), medicalPersonnel, "", "help"));
	}
	
	@Test
	public void testNullNotes() {
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		assertThrows(IllegalArgumentException.class,
				() -> new Appointment(dateTime, this.patientDummy(), medicalPersonnel, "TCL", null));
	}
}
