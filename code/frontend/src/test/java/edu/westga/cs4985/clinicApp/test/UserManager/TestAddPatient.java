package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

/**
 * JUnit Test Case for Add Patient
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 *
 */
public class TestAddPatient {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request.equals(
					"ADD_PATIENT,{\"insurance\":\"United Healthcare\",\"lastName\":\"Heath\",\"country\":\"United States\",\"gender\":\"Male\",\"race\":\"Native Hawaiian or Other Pacific Islander\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"292 Maple Street\",\"dateOfBirth\":\"2021-09-15\",\"type\":\"PATIENT\",\"userName\":\"hhgreg\",\"firstName\":\"Greg\",\"password\":\"123\",\"phoneNumber\":\"678556743\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"\",\"state\":\"GA\",\"email\":\"hhgreg@yahoo.com\"}")) {
				return "ADDED";
			} else {
				return "ERROR";
			}
		}
	}

	public Patient patientDummy() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patientDummy = new Patient("Greg", "Heath", gender.SEX[0], "2021-09-15", "292 Maple Street",
				"", "Carrollton", "GA", country.COUNTRY[0], race.RACE[3], ethnicity.ETHNICITY[1],
				"678556743", "hhgreg@yahoo.com", "United Healthcare", "hhgreg", "123");
		return patientDummy;
	}
	
	public Patient patientDummy2() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patientDummy = new Patient("Greg", "Heath", gender.SEX[0], "2021-09-15", "292 Maple Street",
				"", "Carrollton", "GA", country.COUNTRY[0], race.RACE[3], ethnicity.ETHNICITY[1],
				"678556743", "hhgreg@yahoo.com", "United Healthcare", "hhgre", "123");
		return patientDummy;
	}

	@Test
	void testAddValidPatient() {
		UserManager userManager = new UserManager(new ServerFake());
		boolean added = userManager.addPatient(patientDummy());
		assertEquals(true, added);
	}
	
	//Need to add conditions to server that check for duplicate and if found, return error. This placeholds duplicate found 
	//invalid patient
	@Test
	void testAddInvalidPatient() {
		UserManager userManager = new UserManager(new ServerFake());
		boolean added = userManager.addPatient(patientDummy());
		assertEquals(true, added);
		boolean duplicate = userManager.addPatient(patientDummy2());
		assertEquals(false, duplicate);
	}
}
