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
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Person personDummy = new Person("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street", "",
				"Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1]);
		return personDummy;
	}

	@Test
	void testEmptyFirstName() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("", "Jameson", gender.sex[0], "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testNullFirstName() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person(null, "Jameson", gender.sex[0], "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testEmptyLastName() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "", gender.sex[0], "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testNullLastName() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", null, gender.sex[0], "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testEmptyGender() {
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", "", "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testNullGender() {
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", "", "08-08-2008",
				"912 Maple Street", "", "Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testEmptyDateOfBirth() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", gender.sex[0], "", "912 Maple Street",
				"", "Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testNullDateOfBirth() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", gender.sex[0], null,
				"912 Maple Street", "", "Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testEmptyAddress1() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", gender.sex[0], "08-08-08", "", "",
				"Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testNullAddress1() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", gender.sex[0], "08-08-08", null, "",
				"Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testNullAddress2() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Person("Jameson", "John", gender.sex[0], "08-08-08", "912 Maple Street", null, "Carrollton", "GA",
						country.country[0], race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testEmptyCity() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", gender.sex[0], "08-08-08",
				"912 Maple Street", "", "", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testNullCity() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", gender.sex[0], "08-08-08",
				"912 Maple Street", "", null, "GA", country.country[0], race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testEmptyState() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", gender.sex[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "", country.country[0], race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testNullState() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", gender.sex[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", null, country.country[0], race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testEmptyCountry() {
		Gender gender = new Gender();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", gender.sex[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", "", race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testNullCountry() {
		Gender gender = new Gender();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", gender.sex[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", null, race.race[2], ethnicity.ethnicity[1]));
	}

	@Test
	void testEmptyRace() {
		Gender gender = new Gender();
		Country country = new Country();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", gender.sex[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", country.country[0], "", ethnicity.ethnicity[1]));
	}
	
	@Test
	void testNullRace() {
		Gender gender = new Gender();
		Country country = new Country();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class, () -> new Person("John", "Jameson", gender.sex[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", country.country[0], null, ethnicity.ethnicity[1]));
	}

	@Test
	void testEmptyEthnicity() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John",gender.sex[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", country.country[0], race.race[2], ""));
	}

	@Test
	void testNullEthnicity() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		assertThrows(IllegalArgumentException.class, () -> new Person("Jameson", "John", gender.sex[0], "08-08-08",
				"912 Maple Street", "", "Carrollton", "GA", country.country[0], race.race[2], null));
	}

}
