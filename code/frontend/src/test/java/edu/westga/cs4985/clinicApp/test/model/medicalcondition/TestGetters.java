package edu.westga.cs4985.clinicApp.test.model.medicalcondition;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.MedicalCondition;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

class TestGetters {
	
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
	
	public Patient patientDummy2() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patientDummy = new Patient("Kyle", "Lang", gender.SEX[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.COUNTRY[0], race.RACE[1], ethnicity.ETHNICITY[1],
				"770-111-222", "email@email.com", "United Healthcare", "klang123", "123");
		return patientDummy;
	}
	
	public MedicalCondition medicalCondition() {
		Patient patient = patientDummy();
		String name = "Lyme Disease";
		String diagnosisDate = "09-08-2012";
		String terminationDate = "N/A";
		String notes = "Tick bite";
		MedicalCondition medicalCondition = new MedicalCondition(patient, name, diagnosisDate, terminationDate, notes);
		return medicalCondition;
	}
	
	@Test
	void testSetPatient() {
		MedicalCondition medicalCondition = medicalCondition();
		Patient patientDummy = patientDummy2();
		medicalCondition.setPatient(patientDummy);
		assertEquals(patientDummy, medicalCondition.getPatient());
	}

	@Test
	void testSetFirstName() {
		MedicalCondition medicalCondition = medicalCondition();
		medicalCondition.setName("Epilepsy");
		assertEquals("Epilepsy", medicalCondition.getName());
	}
	
	@Test
	void testSetDiagnosisDate() {
		MedicalCondition medicalCondition = medicalCondition();
		medicalCondition.setDiagnosisDate("09-09-2012");
		assertEquals("09-09-2012", medicalCondition.getDiagnosisDate());
	}
	
	@Test
	void testSetTerminationDate() {
		MedicalCondition medicalCondition = medicalCondition();
		medicalCondition.setTerminationDate("08-08-08");
		assertEquals("08-08-08", medicalCondition.getTerminationDate());
	}
	
	@Test
	void testSetSpecialNotes() {
		MedicalCondition medicalCondition = medicalCondition();
		medicalCondition.setNotes("Used to be treated with X");
		assertEquals("Used to be treated with X", medicalCondition.getNotes());
	}
	
	

}
