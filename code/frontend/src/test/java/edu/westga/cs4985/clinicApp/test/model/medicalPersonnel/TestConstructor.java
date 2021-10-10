package edu.westga.cs4985.clinicApp.test.model.medicalPersonnel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

public class TestConstructor {
	
	public MedicalPersonnel medicalPersonnelDummy() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		MedicalPersonnel medicalPersonnel = new MedicalPersonnel("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
				"770-111-222", "email@email.com", "New", "New", "30118");
		return medicalPersonnel;
	}
	
	@Test
	void testCorrectConstructor() {
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		assertAll(() -> assertEquals("Xavier", medicalPersonnel.getFirstName()),
				() -> assertEquals("Jameson", medicalPersonnel.getLastName()),
				() -> assertEquals(new Gender().sex[0], medicalPersonnel.getGender()),
				() -> assertEquals("08-08-2008", medicalPersonnel.getDateOfBirth()),
				() -> assertEquals("912 Maple Street", medicalPersonnel.getAddress1()),
				() -> assertEquals("East Maple Building 2B", medicalPersonnel.getAddress2()),
				() -> assertEquals("Carrollton", medicalPersonnel.getCity()), () -> assertEquals("GA", medicalPersonnel.getState()),
				() -> assertEquals(new Country().country[0], medicalPersonnel.getCountry()),
				() -> assertEquals(new Race().race[1], medicalPersonnel.getRace()),
				() -> assertEquals(new Ethnicity().ethnicity[1], medicalPersonnel.getEthnicity()),
				() -> assertEquals("770-111-222", medicalPersonnel.getPhoneNumber()),
				() -> assertEquals("email@email.com", medicalPersonnel.getEmail()),
				() -> assertEquals("30118", medicalPersonnel.getZipCode()),
				() -> assertEquals("New", medicalPersonnel.getUsername()),
				() -> assertEquals("New", medicalPersonnel.getPassword()),
				() -> assertEquals("Xavier Jameson", medicalPersonnel.getFullName()),
				() -> assertEquals("Xavier Jameson", medicalPersonnel.toString()),
				() -> assertEquals("912 Maple Street, Carrollton, GA, United States", medicalPersonnel.getFullAddress()));
	}
	
	@Test
	void testPhoneNull() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new MedicalPersonnel("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street",
						"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
						null, "email@email.com", "New", "New", "30118"));
		
	}
	
	@Test
	void testPhoneEmpty() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new MedicalPersonnel("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street",
						"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
						"", "email@email.com", "New", "New", "30118"));
		
	}
	
	@Test
	void testEmailNull() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new MedicalPersonnel("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street",
						"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
						"770-111-222", null, "New", "New", "30118"));
		
	}
	
	@Test
	void testEmailEmpty() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new MedicalPersonnel("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street",
						"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
						"770-111-222", "", "New", "New", "30118"));
		
	}

}
