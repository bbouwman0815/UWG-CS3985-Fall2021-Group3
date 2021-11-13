package edu.westga.cs4985.clinicApp.test.model.patient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.Person;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

/**
 * JUnit Test Case for Patient Constructor
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 *
 */
class TestPatientConstructor {

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
	void testCorrectConstructor() {
		Patient patient = patientDummy();
		assertAll(() -> assertEquals("Xavier", patient.getFirstName()),
				() -> assertEquals("Jameson", patient.getLastName()),
				() -> assertEquals(new Gender().SEX[0], patient.getGender()),
				() -> assertEquals("08-08-2008", patient.getDateOfBirth()),
				() -> assertEquals("912 Maple Street", patient.getAddress1()),
				() -> assertEquals("East Maple Building 2B", patient.getAddress2()),
				() -> assertEquals("Carrollton", patient.getCity()), () -> assertEquals("GA", patient.getState()),
				() -> assertEquals(new Country().COUNTRY[0], patient.getCountry()),
				() -> assertEquals(new Race().RACE[1], patient.getRace()),
				() -> assertEquals(new Ethnicity().ETHNICITY[1], patient.getEthnicity()),
				() -> assertEquals("770-111-222", patient.getPhoneNumber()),
				() -> assertEquals("email@email.com", patient.getEmail()),
				() -> assertEquals("United Healthcare", patient.getInsurance()),
				() -> assertEquals("New", patient.getUsername()),
				() -> assertEquals("New", patient.getPassword()));
	}

	@Test
	void testEmptyPhoneNumber() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Patient("Xavier", "Jameson", gender.SEX[0], "08-08-2008", "912 Maple Street", "",
						"Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "",
						"email@email.com", "United Healthcare", "New", "New"));
	}
	
	@Test
	void testEquals() {
		Patient patient = patientDummy();
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patient1 = new Patient("Xavier", "Jameson", gender.SEX[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.COUNTRY[0], race.RACE[1], ethnicity.ETHNICITY[1],
				"770-111-222", "email@email.com", "United Healthcare", "New", "New");
		assertEquals(true, patient.equals(patient1));
	}
	
	@Test
	void testNotEquals() {
		Patient patient = patientDummy();
		String patient1 = new String();
		assertEquals(false, patient.equals(patient1));
	}
	
	@Test
	void testNotPatientEquals() {
		Patient patient = patientDummy();
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patient1 = new Patient("Xavier", "Jameson", gender.SEX[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.COUNTRY[0], race.RACE[1], ethnicity.ETHNICITY[1],
				"770-111-222", "email@email.com", "United Healthcare", "lk", "123");
		assertEquals(false, patient.equals(patient1));
	}
	
	@Test
	void testEmptyUername() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Patient("Xavier", "Jameson", gender.SEX[0], "08-08-2008", "912 Maple Street", "",
						"Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "12344",
						"email@email.com", "United Healthcare", "", "New"));
	}
	
	@Test
	void testEmptyPasswoad() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Patient("Xavier", "Jameson", gender.SEX[0], "08-08-2008", "912 Maple Street", "",
						"Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "12344",
						"email@email.com", "United Healthcare", "New", ""));
	}
	
	@Test
	void testNullUername() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Patient("Xavier", "Jameson", gender.SEX[0], "08-08-2008", "912 Maple Street", "",
						"Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "12344",
						"email@email.com", "United Healthcare", null, "New"));
	}
	
	@Test
	void testNullPasswoad() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Patient("Xavier", "Jameson", gender.SEX[0], "08-08-2008", "912 Maple Street", "",
						"Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "12344",
						"email@email.com", "United Healthcare", "New", null));
	}

	@Test
	void testNullPhoneNumber() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Patient("Xavier", "Jameson", gender.SEX[0], "08-08-2008", "912 Maple Street", "",
						"Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], null,
						"email@email.com", "United Healthcare", "New", "New"));
	}

	@Test
	void testEmptyEmail() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Patient("Xavier", "Jameson", gender.SEX[0], "08-08-2008", "912 Maple Street", "",
						"Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "770-111-222", "",
						"United Healthcare", "New", "New"));
	}

	@Test
	void testNullEmail() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Patient("Xavier", "Jameson", gender.SEX[0], "08-08-2008", "912 Maple Street", "",
						"Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "770-111-222",
						null, "United Healthcare", "New", "New"));
	}

	@Test
	void testEmptyInsurance() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Patient("Xavier", "Jameson", gender.SEX[0], "08-08-2008", "912 Maple Street", "",
						"Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "770-111-222",
						"email@email.com", "", "New", "New"));
	}

	@Test
	void testNullInsurance() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Patient("Xavier", "Jameson", gender.SEX[0], "08-08-2008", "912 Maple Street", "",
						"Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "770-111-222",
						"email@email.com", null, "New", "New"));
	}
	
	@Test
	void testGetFullName() {
		Patient patient = patientDummy();
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertEquals("Xavier Jameson", patient.getFullName());
	}
}
