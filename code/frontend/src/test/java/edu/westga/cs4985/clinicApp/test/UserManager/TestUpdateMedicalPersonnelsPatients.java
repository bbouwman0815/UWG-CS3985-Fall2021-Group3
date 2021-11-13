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
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		MedicalPersonnel personnelDummy = new MedicalPersonnel("Greg", "Lane", gender.SEX[0], "2021-09-15",
				"292 Maple Street", "", "Carrollton", "GA", country.COUNTRY[0], race.RACE[3], ethnicity.ETHNICITY[1],
				"6785567432", "hhgreg@yahoo.com", "hhgreg", "123", "30117");
		return personnelDummy;
	}
	
	public MedicalPersonnel medicalpersonneldummy2() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		MedicalPersonnel personnelDummy = new MedicalPersonnel("Greg", "Lane", gender.SEX[0], "2021-09-15",
				"292 Maple Street", "", "Carrollton", "GA", country.COUNTRY[0], race.RACE[3], ethnicity.ETHNICITY[1],
				"6785567432", "hhgreg@yahoo.com", "gggregh", "123", "30117");
		return personnelDummy;
	}
	
	public Patient patientDummy() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patientDummy = new Patient("Greg", "Heath", gender.SEX[0], "2021-09-15", "292 Maple Street",
				"", "Carrollton", "GA", country.COUNTRY[0], race.RACE[3], ethnicity.ETHNICITY[1],
				"678556743", "hhgreg@yahoo.com", "United Healthcare", "bbouwman0815", "123");
		return patientDummy;
	}
	
	public Patient patientDummy2() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patientDummy = new Patient("Greg", "Heath", gender.SEX[0], "2021-09-15", "292 Maple Street",
				"", "Carrollton", "GA", country.COUNTRY[0], race.RACE[3], ethnicity.ETHNICITY[1],
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
