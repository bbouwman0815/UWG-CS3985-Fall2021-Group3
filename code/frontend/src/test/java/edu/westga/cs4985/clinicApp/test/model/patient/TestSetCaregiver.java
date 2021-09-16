package edu.westga.cs4985.clinicApp.test.model.patient;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

public class TestSetCaregiver {
	public Patient patientDummy() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patientDummy = new Patient("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
				"770-111-222", "email@email.com", "United Healthcare", "New", "New");
		return patientDummy;
	}

	@Test
	void testSetCaregiver() {
		Patient patient = patientDummy();
		patient.setCaregiver("caregiver");
		assertEquals("caregiver", patient.getCaregiver());
	}
	
	@Test
	void testSetNullCaregiver() {
		Patient patient = patientDummy();
		
		assertThrows(IllegalArgumentException.class,
				() -> patient.setCaregiver(null));
	}
}
