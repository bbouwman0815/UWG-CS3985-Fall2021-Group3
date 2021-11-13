package edu.westga.cs4985.clinicApp.test.model.person;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Person;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

/**
 * JUnit Test Case for Person Constructor
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 *
 */
class TestPersonConstructor {

	public Person personDummy() {
		Person personDummy = new Person("Xavier", "Jameson", Gender.SEX[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[1], Ethnicity.ETHNICITY[1], "New", "New");
		return personDummy;
	}

	@Test
	void testCorrectConstructor() {
		Person person = personDummy();
		new Gender();
		new Country();
		new Race();
		new Ethnicity();
		assertAll(() -> assertEquals("Xavier", person.getFirstName()),
				() -> assertEquals("Jameson", person.getLastName()),
				() -> assertEquals(Gender.SEX[0], person.getGender()),
				() -> assertEquals("08-08-2008", person.getDateOfBirth()),
				() -> assertEquals("912 Maple Street", person.getAddress1()),
				() -> assertEquals("East Maple Building 2B", person.getAddress2()),
				() -> assertEquals("Carrollton", person.getCity()), () -> assertEquals("GA", person.getState()),
				() -> assertEquals(Country.COUNTRY[0], person.getCountry()),
				() -> assertEquals(Race.RACE[1], person.getRace()),
				() -> assertEquals(Ethnicity.ETHNICITY[1], person.getEthnicity()));
	}

	@Test
	void testEmptyFirstName() {
		assertThrows(IllegalArgumentException.class, () -> new Person("", "Jameson", Gender.SEX[0], "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullFirstName() {
		assertThrows(IllegalArgumentException.class, () -> new Person(null, "Jameson", Gender.SEX[0], "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyLastName() {
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "", Gender.SEX[0], "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullLastName() {
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", null, Gender.SEX[0], "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyGender() {
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", "", "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullGender() {
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", null, "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyDateOfBirth() {
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", Gender.SEX[0], "",
				"912 Maple Street", "", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullDateOfBirth() {
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", Gender.SEX[0], null,
				"912 Maple Street", "", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyAddress1() {
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", Gender.SEX[0], "08-08-08", "",
				"", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullAddress1() {
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", Gender.SEX[0], "08-08-08",
				null, "", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullAddress2() {
		assertThrows(IllegalArgumentException.class,
				() -> new Person("Jameson", "John", Gender.SEX[0], "08-08-08", "912 Maple Street", null, "Carrollton",
						"GA", Country.COUNTRY[0], Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyCity() {
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", Gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "", "GA", Country.COUNTRY[0], Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullCity() {
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", Gender.SEX[0], "08-08-08",
				"912 Maple Street", "", null, "GA", Country.COUNTRY[0], Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyState() {
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", Gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "", Country.COUNTRY[0], Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullState() {
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", Gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", null, Country.COUNTRY[0], Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyCountry() {
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", Gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", "", Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullCountry() {
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", Gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", null, Race.RACE[2], Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyRace() {
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", Gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", Country.COUNTRY[0], "", Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullRace() {
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", Gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", Country.COUNTRY[0], null, Ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyEthnicity() {
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", Gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[2], "", "New", "New"));
	}

	@Test
	void testNullEthnicity() {
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", Gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[2], null, "New", "New"));
	}

}
