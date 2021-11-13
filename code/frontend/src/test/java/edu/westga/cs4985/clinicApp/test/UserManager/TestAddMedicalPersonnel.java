package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.*;

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

class TestAddMedicalPersonnel {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request.equals(
					"ADD_MEDICAL_PERSONNEL,{\"lastName\":\"Lane\",\"country\":\"United States\",\"gender\":\"Male\",\"race\":\"Native Hawaiian or Other Pacific Islander\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"292 Maple Street\",\"dateOfBirth\":\"2021-09-15\",\"type\":\"MedicalPersonnel\",\"userName\":\"30117\",\"zipcode\":\"123\",\"firstName\":\"Greg\",\"password\":\"hhgreg\",\"phoneNumber\":\"6785567432\",\"ethnicty\":\"Not Hispanic or Latino\",\"state\":\"GA\",\"email\":\"hhgreg@yahoo.com\"}")) {
				return "ADDED";
			}
			if (request.equals(
					"ADD_MEDICAL_PERSONNEL,{\"lastName\":\"Laney\",\"country\":\"United States\",\"gender\":\"Male\",\"race\":\"Native Hawaiian or Other Pacific Islander\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"292 Maple Street\",\"dateOfBirth\":\"2021-09-15\",\"type\":\"MedicalPersonnel\",\"userName\":\"30117\",\"zipcode\":\"123\",\"firstName\":\"Greg\",\"password\":\"hhgreg\",\"phoneNumber\":\"6785567432\",\"ethnicty\":\"Not Hispanic or Latino\",\"state\":\"GA\",\"email\":\"hhgreg@yahoo.com\"}")) {
				return "ERROR";
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
				"6785567432", "hhgreg@yahoo.com", "30117", "hhgreg", "123");
		return personnelDummy;
	}

	public MedicalPersonnel medicalpersonneldummy2() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		MedicalPersonnel personnelDummy = new MedicalPersonnel("Greg", "Laney", gender.SEX[0], "2021-09-15",
				"292 Maple Street", "", "Carrollton", "GA", country.COUNTRY[0], race.RACE[3], ethnicity.ETHNICITY[1],
				"6785567432", "hhgreg@yahoo.com", "30117", "hhgreg", "123");
		return personnelDummy;
	}

	@Test
	void testAddValidMedicalPersonnel() throws ParseException {
		UserManager userManager = new UserManager(new ServerFake());
		boolean result = userManager.addMedicalPersonnel(this.medicalpersonneldummy());
		assertEquals(result, true);
	}

	@Test
	void testAddInvalidMedicalPersonnel() throws ParseException {
		UserManager userManager = new UserManager(new ServerFake());
		boolean result = userManager.addMedicalPersonnel(this.medicalpersonneldummy2());
		assertFalse(result);
	}

}
