package edu.westga.cs4985.clinicApp.test.model.medication;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Medication;

class TestGetters {
	
	static Date testDate = new Date();
	static Medication test = new Medication("asdf", "asdf", "asdf", "asdf", testDate, "asdf", 0);

	@Test
	void testGetBrand() {
		assertEquals("asdf", test.getBrand());
	}

	@Test
	void testGetForm() {
		assertEquals("asdf", test.getForm());
	}
	
	@Test
	void testGetDosage() {
		assertEquals("asdf", test.getDosage());
	}
	
	@Test
	void testGetFrequency() {
		assertEquals("asdf", test.getFrequency());
	}
	
	@Test
	void testGetRefileDate() {
		assertEquals(testDate, test.getRefilDate());
	}
	
	@Test
	void testGetSpecialInstructionString() {
		assertEquals("asdf", test.getSpecialInstructionString());
	}
	
	@Test
	void testGetRefills() {
		assertEquals(0, test.getRefills());
	}
}
