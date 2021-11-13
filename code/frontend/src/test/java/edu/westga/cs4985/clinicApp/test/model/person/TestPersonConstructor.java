package edu.westga.cs4985.clinicApp.test.model.person;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Patient;
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
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Person personDummy = new Person("Xavier", "Jameson", gender.SEX[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.COUNTRY[0], race.RACE[1], ethnicity.ETHNICITY[1], "New", "New");
		return personDummy;
	}

	@Test
	void testCorrectConstructor() {
		Person person = personDummy();
		assertAll(() -> assertEquals("Xavier", person.getFirstName()),
				() -> assertEquals("Jameson", person.getLastName()),
				() -> assertEquals(new Gender().SEX[0], person.getGender()),
				() -> assertEquals("08-08-2008", person.getDateOfBirth()),
				() -> assertEquals("912 Maple Street", person.getAddress1()),
				() -> assertEquals("East Maple Building 2B", person.getAddress2()),
				() -> assertEquals("Carrollton", person.getCity()), () -> assertEquals("GA", person.getState()),
				() -> assertEquals(new Country().COUNTRY[0], person.getCountry()),
				() -> assertEquals(new Race().RACE[1], person.getRace()),
				() -> assertEquals(new Ethnicity().ETHNICITY[1], person.getEthnicity()));
	}

	@Test
	void testEmptyFirstName() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("", "Jameson", gender.SEX[0], "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullFirstName() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person(null, "Jameson", gender.SEX[0], "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyLastName() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "", gender.SEX[0], "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullLastName() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", null, gender.SEX[0], "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyGender() {
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", "", "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullGender() {
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", null, "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyDateOfBirth() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", gender.SEX[0], "",
				"912 Maple Street", "", "Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullDateOfBirth() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", gender.SEX[0], null,
				"912 Maple Street", "", "Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyAddress1() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", gender.SEX[0], "08-08-08", "",
				"", "Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullAddress1() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", gender.SEX[0], "08-08-08",
				null, "", "Carrollton", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullAddress2() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Person("Jameson", "John", gender.SEX[0], "08-08-08", "912 Maple Street", null, "Carrollton",
						"GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyCity() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "", "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullCity() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", gender.SEX[0], "08-08-08",
				"912 Maple Street", "", null, "GA", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyState() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "", country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullState() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", null, country.COUNTRY[0], race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyCountry() {
		Gender gender = new Gender();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", "", race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullCountry() {
		Gender gender = new Gender();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", null, race.RACE[2], ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyRace() {
		Gender gender = new Gender();
		Country country = new Country();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", country.COUNTRY[0], "", ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testNullRace() {
		Gender gender = new Gender();
		Country country = new Country();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", country.COUNTRY[0], null, ethnicity.ETHNICITY[1], "New", "New"));
	}

	@Test
	void testEmptyEthnicity() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", country.COUNTRY[0], race.RACE[2], "", "New", "New"));
	}

	@Test
	void testNullEthnicity() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", gender.SEX[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", country.COUNTRY[0], race.RACE[2], null, "New", "New"));
	}

}
