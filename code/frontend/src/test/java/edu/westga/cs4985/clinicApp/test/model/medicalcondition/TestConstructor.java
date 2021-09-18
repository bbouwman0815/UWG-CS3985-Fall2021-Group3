package edu.westga.cs4985.clinicApp.test.model.medicalcondition;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.MedicalCondition;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.Person;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

/**
 * JUnit Test Case for Medical Condition Constructor
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 *
 */
class TestConstructor {

	public Patient patientDummy2() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patientDummy = new Patient("Kyle", "Lang", gender.sex[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
				"770-111-222", "email@email.com", "United Healthcare", "klang123", "123");
		return patientDummy;
	}

	public MedicalCondition medicalCondition() {
		String name = "Lyme Disease";
		String diagnosisDate = "09-08-2012";
		String terminationDate = "N/A";
		String notes = "Tick bite";
		MedicalCondition medicalCondition = new MedicalCondition(patientDummy2(), name, diagnosisDate, terminationDate,
				notes);
		return medicalCondition;
	}

	@Test
	void testCorrectConstructor() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patientDummy = new Patient("Kyle", "Lang", gender.sex[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
				"770-111-222", "email@email.com", "United Healthcare", "klang123", "123");
		String name = "Lyme Disease";
		String diagnosisDate = "09-08-2012";
		String terminationDate = "N/A";
		String notes = "Tick bite";
		MedicalCondition medicalCondition = new MedicalCondition(patientDummy, name, diagnosisDate, terminationDate, notes);
		assertAll(() -> assertEquals("Lyme Disease", medicalCondition.getName()),
				() -> assertEquals("09-08-2012", medicalCondition.getDiagnosisDate()),
				() -> assertEquals("klang123", medicalCondition.getPatient().getUsername()),
				() -> assertEquals("N/A", medicalCondition.getTerminationDate()),
				() -> assertEquals("Tick bite", medicalCondition.getNotes()));
	}

	@Test
	void testNullPatient() {
		assertThrows(IllegalArgumentException.class,
				() -> new MedicalCondition(null, "Lyme Disease", "09-08-2012", "N/A", "Tick bite"));
	}

	@Test
	void testEmptyName() {
		assertThrows(IllegalArgumentException.class,
				() -> new MedicalCondition(patientDummy2(), "", "09-08-2012", "N/A", "Tick bite"));
	}

	@Test
	void testNullName() {
		assertThrows(IllegalArgumentException.class,
				() -> new MedicalCondition(patientDummy2(), null, "09-08-2012", "N/A", "Tick bite"));
	}

	@Test
	void testEmptyDiagnosisDate() {
		assertThrows(IllegalArgumentException.class,
				() -> new MedicalCondition(patientDummy2(), "Lyme Disease", "", "N/A", "Tick bite"));
	}

	@Test
	void testNullDiagnosisDate() {
		assertThrows(IllegalArgumentException.class,
				() -> new MedicalCondition(patientDummy2(), "Lyme Disease", null, "N/A", "Tick bite"));
	}

	@Test
	void testNullTerminationDate() {
		assertThrows(IllegalArgumentException.class,
				() -> new MedicalCondition(patientDummy2(), "Lyme Disease", "09-08-2012", null, "Tick bite"));
	}

	@Test
	void testNullSpecialNotes() {
		assertThrows(IllegalArgumentException.class,
				() -> new MedicalCondition(patientDummy2(), "Lyme Disease", "09-08-2012", "N/A", null));
	}
}
