package edu.westga.cs4985.clinicApp.test.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

/**
 * Test hasPassed
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class TestHasPass {
	
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
	public void testPass() {
		Patient patient = this.patientDummy();
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		Appointment appointment = new Appointment(dateTime, patient, "Person A", "TCL", "help");
		assertEquals(true, appointment.hasPassed());
	}
	
	@Test
	public void testNotPass() {
		Patient patient = this.patientDummy();
		LocalDateTime dateTime = LocalDateTime.of(2022,9,9,14,00);
		Appointment appointment = new Appointment(dateTime, patient, "Person A", "TCL", "help");
		assertEquals(false, appointment.hasPassed());
	}

}
