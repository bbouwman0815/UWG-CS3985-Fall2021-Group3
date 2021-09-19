package edu.westga.cs4985.clinicApp.test.model.medication;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Medication;

/**
 * JUnit tests case for medication constructor
 * 
 * @author DavidWalker
 *
 */
class TestMedicationConstruction {
	
	static Date testDate = new Date();

	@Test
	void testNullBrand( ) {
		assertThrows(IllegalArgumentException.class, 
				() -> new Medication(null, "asdf", "asdf", "asdf", testDate, "asdf", 0));
	}
	
	@Test
	void testEmptyBrand() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Medication("", "asdf", "asdf", "asdf", testDate, "asdf", 0));
	}
	
	@Test
	void testNullForm() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Medication("asdf", null, "asdf", "asdf", testDate, "asdf", 0));
	}
	
	@Test
	void testEmptyForm() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Medication("asdf", "", "asdf", "asdf", testDate, "asdf", 0));
	}
	
	@Test
	void testNullDosage() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Medication("asdf", "asdf", null, "asdf", testDate, "asdf", 0));
	}

	@Test
	void testEmptyDosage() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Medication("asdf", "asdf", "", "asdf", testDate, "asdf", 0));
	}
	
	@Test
	void testNullFrequency() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Medication("asdf", "asdf", "asdf", null, testDate, "asdf", 0));
	}
	
	@Test
	void testEmptyFrequency() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Medication("asdf", "asdf", "asdf", "", testDate, "asdf", 0));
	}
	
	@Test
	void testNullRefilDate() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Medication("asdf", "asdf", "asdf", "asdf", null, "asdf", 0));
	}
	
	@Test
	void testNullInstructions() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Medication("asdf", "asdf", "asdf", "asdf", testDate, null, 0));
	}
	
	@Test
	void testEmptyInstructions() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Medication("asdf", "asdf", "asdf", "asdf", testDate, "", 0));
	}
	
	@Test
	void testNegativeRefils() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Medication("asdf", "asdf", "asdf", "asdf", testDate, "asdf", -1));
	}
	
	@Test
	void testValidConstruction() {
		Medication test = new Medication("asdf", "asdf", "asdf", "asdf", testDate, "asdf", 0);
		
		assertFalse(test == null);
	}
}
