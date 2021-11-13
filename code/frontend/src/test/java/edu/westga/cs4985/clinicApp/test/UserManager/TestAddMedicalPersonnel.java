package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
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
		MedicalPersonnel personnelDummy = new MedicalPersonnel("Greg", "Lane", Gender.SEX[0], "2021-09-15",
				"292 Maple Street", "", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[3], Ethnicity.ETHNICITY[1],
				"6785567432", "hhgreg@yahoo.com", "30117", "hhgreg", "123");
		return personnelDummy;
	}

	public MedicalPersonnel medicalpersonneldummy2() {
		MedicalPersonnel personnelDummy = new MedicalPersonnel("Greg", "Laney", Gender.SEX[0], "2021-09-15",
				"292 Maple Street", "", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[3], Ethnicity.ETHNICITY[1],
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
