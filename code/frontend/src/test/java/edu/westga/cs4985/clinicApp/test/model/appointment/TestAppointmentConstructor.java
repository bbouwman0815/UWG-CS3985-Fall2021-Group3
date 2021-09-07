package edu.westga.cs4985.clinicApp.test.model.appointment;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Appointment;
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
	
	public Patient patientDummy() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patientDummy = new Patient("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
				"770-111-222", "email@email.com", "United Healthcare");
		return patientDummy;
	}
	
	@Test
	public void testValidConstructor() {
		Patient patient = this.patientDummy();
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		Appointment appointment = new Appointment(dateTime, patient, "Person A", "TCL", "help");
		assertAll(() -> assertEquals(dateTime, appointment.getDateTime()),
				() -> assertEquals("Person A", appointment.getMedicalPersonnel()),
				() -> assertEquals("TCL", appointment.getLocation()),
				() -> assertEquals(patient, appointment.getPatient()),
				() -> assertEquals("help", appointment.getNotes()));
	}
	
	@Test
	public void testNullPatient() {
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		assertThrows(IllegalArgumentException.class,
				() -> new Appointment(dateTime, null, "Person A", "TCL", "help"));
	}
	
	@Test
	public void testNullDateTime() {
		assertThrows(IllegalArgumentException.class,
				() -> new Appointment(null, this.patientDummy(), "Person A", "TCL", "help"));
	}

	@Test
	public void testNullMedicalPersonnel() {
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		assertThrows(IllegalArgumentException.class,
				() -> new Appointment(dateTime, this.patientDummy(), null, "TCL", "help"));
	}

	@Test
	public void testNullLocation() {
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		assertThrows(IllegalArgumentException.class,
				() -> new Appointment(dateTime, this.patientDummy(), "Person A", null, "help"));
	}
	
	@Test
	public void testEmptyLocation() {
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		assertThrows(IllegalArgumentException.class,
				() -> new Appointment(dateTime, this.patientDummy(), "Person A", "", "help"));
	}
	
	@Test
	public void testNullNotes() {
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		assertThrows(IllegalArgumentException.class,
				() -> new Appointment(dateTime, this.patientDummy(), "Person A", "TCL", null));
	}
}
