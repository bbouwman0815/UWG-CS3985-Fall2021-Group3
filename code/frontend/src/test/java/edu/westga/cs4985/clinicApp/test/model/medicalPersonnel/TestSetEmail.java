package edu.westga.cs4985.clinicApp.test.model.medicalPersonnel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

public class TestSetEmail {
	
	public MedicalPersonnel medicalPersonnelDummy() {
		MedicalPersonnel patientDummy = new MedicalPersonnel("Xavier", "Jameson", Gender.SEX[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[1], Ethnicity.ETHNICITY[1],
				"770-111-222", "email@email.com", "New", "New", "30118");
		return patientDummy;
	}
	
	@Test
	void testSetEmial() {
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		medicalPersonnel.setEmail("3@gmail.com");
		assertEquals("3@gmail.com", medicalPersonnel.getEmail());
	}

}
