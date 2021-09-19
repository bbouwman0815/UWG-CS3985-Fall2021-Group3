package edu.westga.cs4985.clinicApp.test.model.person;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Person;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

public class TestSetters {
	public Person personDummy() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Person personDummy = new Person("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1], "New", "New");
		return personDummy;
	}

	@Test
	void testSetFirstName() {
		Person person = personDummy();
		person.setFirstName("new");
		assertEquals("new", person.getFirstName());
	}
	
	@Test
	void testSetLastName() {
		Person person = personDummy();
		person.setLastName("new");
		assertEquals("new", person.getLastName());
	}
	
	@Test
	void testSetGender() {
		Person person = personDummy();
		person.setGender("female");
		assertEquals("female", person.getGender());
	}
	
	@Test
	void testSetBirth() {
		Person person = personDummy();
		person.setDateOfBirth("19900909");
		assertEquals("19900909", person.getDateOfBirth());
	}
	
	@Test
	void testSetAddress1() {
		Person person = personDummy();
		person.setAddress1("new");
		assertEquals("new", person.getAddress1());
	}
	
	@Test
	void testSetAddress2() {
		Person person = personDummy();
		person.setAddress2("new");
		assertEquals("new", person.getAddress2());
	}
	
	@Test
	void testSetCity() {
		Person person = personDummy();
		person.setCity("newyork");
		assertEquals("newyork", person.getCity());
	}
	
	@Test
	void testSetCountry() {
		Person person = personDummy();
		person.setCountry("English");
		assertEquals("English", person.getCountry());
	}
	
	@Test
	void testSetState() {
		Person person = personDummy();
		person.setState("Alabama");
		assertEquals("Alabama", person.getState());
	}
	
	@Test
	void testSetRace() {
		Person person = personDummy();
		person.setRace("new");
		assertEquals("new", person.getRace());
	}
	
	@Test
	void testSetEthnicity() {
		Person person = personDummy();
		person.setEthnicity("new");
		assertEquals("new", person.getEthnicity());
	}
	
	@Test
	void testSetUsername() {
		Person person = personDummy();
		person.setUsername("new");
		assertEquals("new", person.getUsername());
	}
	
	@Test
	void testSetPassword() {
		Person person = personDummy();
		person.setPassword("new");
		assertEquals("new", person.getPassword());
	}
}
