package edu.westga.cs4985.clinicApp.test.model.appointment;

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
 * Test set notes
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class TestSetNotes {
	
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
	public void testSetValidNotes() {
		Patient patient = this.patientDummy();
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		Appointment appointment = new Appointment(dateTime, patient, medicalPersonnel, "TCL", "help");
		appointment.setNotes("NewHelp");
		assertEquals("NewHelp", appointment.getNotes());
	}
	
	@Test
	public void testSetNullNotes() {
		Patient patient = this.patientDummy();
		LocalDateTime dateTime = LocalDateTime.of(2021,9,01,14,00);
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		Appointment appointment = new Appointment(dateTime, patient, medicalPersonnel, "TCL", "help");
		assertThrows(IllegalArgumentException.class,
				() -> appointment.setNotes(null));
	}
}
