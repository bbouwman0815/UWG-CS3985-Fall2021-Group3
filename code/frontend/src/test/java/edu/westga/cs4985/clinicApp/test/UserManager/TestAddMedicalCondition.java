package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.MedicalCondition;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.DataReader;
import edu.westga.cs4985.clinicApp.utils.DataWriter;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

class TestAddMedicalCondition {
	
	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			UserManager manager = new UserManager();
			DataWriter writer = new DataWriter();
			DataReader reader = new DataReader();
			String request = requestType + "," + data;

			if (request.equals(
					"ADD_MEDICAL_CONDITION,{\"terminationDate\":\"N\\/A\",\"notes\":\"Tick bite\",\"patient\":\"hhgreg\",\"diagnosisDate\":\"09-08-2012\",\"name\":\"Lyme Disease\"}")) {
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
		Patient patientDummy = new Patient("Greg", "Heath", gender.SEX[0], "2021-09-15", "292 Maple Street",
				"", "Carrollton", "GA", country.COUNTRY[0], race.RACE[3], ethnicity.ETHNICITY[1],
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
		Patient patientDummy = new Patient("Greg", "Heath", gender.SEX[0], "2021-09-15", "292 Maple Street",
				"", "Carrollton", "GA", country.COUNTRY[0], race.RACE[3], ethnicity.ETHNICITY[1],
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
	void testAddValidMedicalCondition() {
		UserManager userManager = new UserManager(new ServerFake());
		UserManager.setUserManager(userManager);
		boolean added = UserManager.userManager.addMedicalCondition(medicalCondition());
		assertEquals(true, added);
	}
	
	@Test
	void testAddInvalidMedicalCondition() {
		UserManager userManager = new UserManager(new ServerFake());
		boolean added = userManager.addMedicalCondition(medicalCondition());
		assertEquals(true, added);
		boolean duplicate = userManager.addMedicalCondition(medicalCondition2());
		assertEquals(false, duplicate);
	}
	
	@Test
	void testAddNullMedicalCondition() {
		UserManager userManager = new UserManager(new ServerFake());
		boolean added = userManager.addMedicalCondition(medicalCondition());
		assertEquals(true, added);
		boolean duplicate = userManager.addMedicalCondition(null);
		assertEquals(false, duplicate);
	}

}
