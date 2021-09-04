package edu.westga.cs4985.clinicApp.test.model.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Person;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

/**
 * JUnit Test Case for User Constructor
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 *
 */
class TestUserConstructor {

	@Test
	void testEmptyUsername() {
		assertThrows(IllegalArgumentException.class, () -> new User("", "123"));
	}

	@Test
	void testNullUsername() {
		assertThrows(IllegalArgumentException.class, () -> new User(null, "123"));
	}

	@Test
	void testEmptyPassword() {
		assertThrows(IllegalArgumentException.class, () -> new User("Admin", ""));
	}

	@Test
	void testNullPassword() {
		assertThrows(IllegalArgumentException.class, () -> new User("Admin", null));
	}

}
