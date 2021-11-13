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
 * Test set location
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class TestSetLocation {
	
	public MedicalPersonnel medicalPersonnelDummy() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		MedicalPersonnel patientDummy = new MedicalPersonnel("Xavier", "Jameson", gender.SEX[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.COUNTRY[0], race.RACE[1], ethnicity.ETHNICITY[1],
				"770-111-222", "email@email.com", "New", "New", "30118");
		return patientDummy;
	}

	public Patient patientDummy() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patientDummy = new Patient("Xavier", "Jameson", gender.SEX[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.COUNTRY[0], race.RACE[1], ethnicity.ETHNICITY[1],
				"770-111-222", "email@email.com", "United Healthcare", "New", "New");
		return patientDummy;
	}
	
	@Test
	public void testSetValidLocation() {
		Patient patient = this.patientDummy();
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		Appointment appointment = new Appointment(dateTime, patient, medicalPersonnel, "TCL", "help");
		appointment.setLocation("NewTCL");
		assertEquals("NewTCL", appointment.getLocation());
	}
	
	@Test
	public void testSetNullLocation() {
		Patient patient = this.patientDummy();
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		Appointment appointment = new Appointment(dateTime, patient, medicalPersonnel, "TCL", "help");
		assertThrows(IllegalArgumentException.class,
				() -> appointment.setLocation(null));
	}
	
	@Test
	public void testSetEmptyLocation() {
		Patient patient = this.patientDummy();
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		Appointment appointment = new Appointment(dateTime, patient, medicalPersonnel, "TCL", "help");
		assertThrows(IllegalArgumentException.class,
				() -> appointment.setLocation(""));
	}
}
