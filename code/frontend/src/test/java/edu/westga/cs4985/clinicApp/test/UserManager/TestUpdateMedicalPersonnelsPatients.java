package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

class TestUpdateMedicalPersonnelsPatients {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request.equals(
					"UPDATE_MEDICAL_PERSONNELS_PATIENTS,{\"patients\":[\"bbouwman0815\",\"jimmy1\"],\"medicalPersonnel\":\"hhgreg\"}")) {
				return "Updated";
			} else {
				return "ERROR";
			}
		}
	}
	
	public MedicalPersonnel medicalpersonneldummy() {
		MedicalPersonnel personnelDummy = new MedicalPersonnel("Greg", "Lane", Gender.SEX[0], "2021-09-15",
				"292 Maple Street", "", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[3], Ethnicity.ETHNICITY[1],
				"6785567432", "hhgreg@yahoo.com", "hhgreg", "123", "30117");
		return personnelDummy;
	}
	
	public MedicalPersonnel medicalpersonneldummy2() {
		MedicalPersonnel personnelDummy = new MedicalPersonnel("Greg", "Lane", Gender.SEX[0], "2021-09-15",
				"292 Maple Street", "", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[3], Ethnicity.ETHNICITY[1],
				"6785567432", "hhgreg@yahoo.com", "gggregh", "123", "30117");
		return personnelDummy;
	}
	
	public Patient patientDummy() {
		Patient patientDummy = new Patient("Greg", "Heath", Gender.SEX[0], "2021-09-15", "292 Maple Street",
				"", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[3], Ethnicity.ETHNICITY[1],
				"678556743", "hhgreg@yahoo.com", "United Healthcare", "bbouwman0815", "123");
		return patientDummy;
	}
	
	public Patient patientDummy2() {
		Patient patientDummy = new Patient("Greg", "Heath", Gender.SEX[0], "2021-09-15", "292 Maple Street",
				"", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[3], Ethnicity.ETHNICITY[1],
				"678556743", "hhgreg@yahoo.com", "United Healthcare", "jimmy1", "123");
		return patientDummy;
	}
	
	@Test
	void testUpdateValidPatient() throws ParseException {
		UserManager userManager = new UserManager(new ServerFake());
		UserManager.setUserManager(userManager);
		List<Patient> patients = new ArrayList<Patient>();
		patients.add(patientDummy());
		patients.add(patientDummy2());
		boolean result = userManager.updateMedicalPersonnelsPatients(medicalpersonneldummy(), patients);
		assertTrue(result);
	}
	
	@Test
	void testUpdateInvalidPatient() throws ParseException {
		UserManager userManager = new UserManager(new ServerFake());
		UserManager.setUserManager(userManager);
		List<Patient> patients = new ArrayList<Patient>();
		patients.add(patientDummy());
		patients.add(patientDummy2());
		boolean result = userManager.updateMedicalPersonnelsPatients(medicalpersonneldummy2(), patients);
		assertFalse(result);
	}
}
