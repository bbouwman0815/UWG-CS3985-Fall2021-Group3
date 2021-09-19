package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.MedicalCondition;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

class TestRemoveMedicalCondition {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request.equals(
					"REMOVE_MEDICAL_CONDITION,{\"terminationDate\":\"N\\/A\",\"notes\":\"Tick bite\",\"patient\":\"hhgreg\",\"diagnosisDate\":\"09-08-2012\",\"name\":\"Lyme Disease\"}")) {
				return "ADDED";
			} else {
				return "ERROR";
			}
		}
	}
	
	public static Patient patientDummy() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patientDummy = new Patient("Greg", "Heath", gender.sex[0], "2021-09-15", "292 Maple Street",
				"", "Carrollton", "GA", country.country[0], race.race[3], ethnicity.ethnicity[1],
				"678556743", "hhgreg@yahoo.com", "United Healthcare", "hhgreg", "123");
		return patientDummy;
	}
	
	public static MedicalCondition medicalCondition() {
		Patient patient = patientDummy();
		String name = "Lyme Disease";
		String diagnosisDate = "09-08-2012";
		String terminationDate = "N/A";
		String notes = "Tick bite";
		MedicalCondition medicalCondition = new MedicalCondition(patient, name, diagnosisDate, terminationDate,
				notes);
		return medicalCondition;
	}
	
	public static Patient patientDummy2() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patientDummy = new Patient("Greg", "Heath", gender.sex[0], "2021-09-15", "292 Maple Street",
				"", "Carrollton", "GA", country.country[0], race.race[3], ethnicity.ethnicity[1],
				"678556743", "hhgreg@yahoo.com", "United Healthcare", "hhgre", "123");
		return patientDummy;
	}
	
	public static MedicalCondition medicalCondition2() {
		Patient patient = patientDummy2();
		String name = "Lyme Disease";
		String diagnosisDate = "09-08-2012";
		String terminationDate = "N/A";
		String notes = "Tick bite";
		MedicalCondition medicalCondition = new MedicalCondition(patient, name, diagnosisDate, terminationDate,
				notes);
		return medicalCondition;
	}

	@Test
	void testRemoveValidMedicalCondition() {
		UserManager userManager = new UserManager(new ServerFake());
		boolean removed = userManager.removeMedicalCondition(medicalCondition());
		assertEquals(true, removed);
	}
	
	@Test
	void testAddInvalidMedicalCondition() {
		UserManager userManager = new UserManager(new ServerFake());
		boolean removed = userManager.removeMedicalCondition(medicalCondition());
		assertEquals(true, removed);
		boolean duplicate = userManager.removeMedicalCondition(medicalCondition2());
		assertEquals(false, duplicate);
	}

}
