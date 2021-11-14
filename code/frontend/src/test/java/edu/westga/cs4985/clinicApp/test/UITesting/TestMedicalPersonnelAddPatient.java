package edu.westga.cs4985.clinicApp.test.UITesting;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import edu.westga.cs4985.clinicApp.ClinicApp;
import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.UserManager;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class TestMedicalPersonnelAddPatient extends ApplicationTest {

	@Override
	public void start(Stage stage) throws IOException {
		ClinicApp app = new ClinicApp();
		UserManager user = new UserManager(new ServerFake());
		app.setUser(user);
		app.start(stage);
	}

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request
					.equals("USER_LOGIN,{\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"userName\":\"admin\"}")) {
				return "{\"lastName\":\"asdfasdf\",\"country\":\"Bhutan\",\"gender\":\"Female\",\"race\":\"Asian\",\"address2\":\"123\",\"city\":\"Carronllton\",\"address1\":\"1601 Maple St\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"MedicalPersonnel\",\"userName\":\"admin\",\"zipcode\":\"30118\",\"firstName\":\"asdfasdf\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"4444444444\",\"ethnicty\":\"Hispanic or Latino\",\"caregiver\":\"\",\"state\":\"GA\",\"email\":\"temail@email\"}";
			}
			if (request.equals("GET_ALL_PATIENTS,GET_ALL_PATIENTS")) {
				return "[{\"insurance\":\"United Healthcare\",\"lastName\":\"Bouwman\",\"country\":\"United States\",\"gender\":\"Male\",\"race\":\"White\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"912 Lovvorn Road\",\"dateOfBirth\":\"2021-09-15\",\"type\":\"PATIENT\",\"userName\":\"bbouwman0815\",\"firstName\":\"Brian\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"6665554444\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"\",\"state\":\"GA\",\"email\":\"bbouwman0815@yahoo.com\"},{\"insurance\":\"234\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"white\",\"address2\":\"\",\"city\":\"Atlanta\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-01\",\"type\":\"PATIENT\",\"userName\":\"jimmy1\",\"firstName\":\"Jimmy\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Hispanic or Latino\",\"caregiver\":\"jdoe\",\"state\":\"GA\",\"email\":\"jimmy@gmail.com\"},{\"insurance\":\"23232\",\"lastName\":\"Good see you\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"white\",\"address2\":\"\",\"city\":\"Atlanta\",\"address1\":\"4544 Atlanta Peachway\",\"dateOfBirth\":\"1995-10-01\",\"type\":\"PATIENT\",\"userName\":\"bob2\",\"firstName\":\"I'am new\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"987654321\",\"ethnicty\":\"Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"bob@gmail.com\"},{\"insurance\":\"8888888888\",\"lastName\":\"test\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"New\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"PATIENT\",\"userName\":\"testp\",\"firstName\":\"test\",\"password\":\"11111\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}]";
			}
			if (request.equals("GET_MEDICAL_PERSONNELS_PATIENTS,{\"medicalPersonnel\":\"admin\"}")) {
				return "[\"testp\"]";
			}
			if (request.equals("GET_USER_BY_USERNAME,{\"patient\":\"testp\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"test\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"New\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"PATIENT\",\"userName\":\"testp\",\"firstName\":\"test\",\"password\":\"11111\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			}
			if (request.equals("UPDATE_GENERAL_INFORMATION,{\"patient\":\"testp\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"test\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"New\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"PATIENT\",\"userName\":\"testp\",\"firstName\":\"test\",\"password\":\"11111\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"timi liu\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			}
			if (request.equals(
					"UPDATE_MEDICAL_PERSONNELS_PATIENTS,{\"patients\":[\"bbouwman0815\",\"jimmy1\"],\"medicalPersonnel\":\"admin\"}")) {
				return "Updated";
			}
			if (request.equals("GET_ALL_CAREGIVERS,GET_ALL_CAREGIVERS")) {
				return "[{\"lastName\":\"liu\",\"country\":\"Bhutan\",\"gender\":\"Male\",\"race\":\"Asian\",\"address2\":\"123\",\"city\":\"Carronllton\",\"address1\":\"1601 Maple St\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"Caregiver\",\"userName\":\"timi1\",\"firstName\":\"timi\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"4444444444\",\"ethnicty\":\"Hispanic or Latino\",\"state\":\"GA\",\"email\":\"temail@email\"}]";
			}
			if (request.equals("GET_USER_BY_USERNAME,{\"patient\":\"bbouwman0815\"}")) {
				return "{\"insurance\":\"United Healthcare\",\"lastName\":\"Bouwman\",\"country\":\"United States\",\"gender\":\"Male\",\"race\":\"White\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"912 Lovvorn Road\",\"dateOfBirth\":\"2021-09-15\",\"type\":\"PATIENT\",\"userName\":\"bbouwman0815\",\"firstName\":\"Brian\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"6665554444\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"timi1\",\"state\":\"GA\",\"email\":\"bbouwman0815@yahoo.com\"}";
			} else {
				return "ERROR";
			}
		}
	}

	@Test
	public void testAddDuplicatePatient() throws InterruptedException {
		this.clickOn("#usernameTextField");
		this.type(KeyCode.A);
		this.type(KeyCode.D);
		this.type(KeyCode.M);
		this.type(KeyCode.I);
		this.type(KeyCode.N);
		this.clickOn("#passwordTextField");
		this.type(KeyCode.P);
		this.type(KeyCode.A);
		this.type(KeyCode.S);
		this.type(KeyCode.S);
		this.type(KeyCode.W);
		this.type(KeyCode.O);
		this.type(KeyCode.R);
		this.type(KeyCode.D);
		this.clickOn("#loginButton");

		this.clickOn("#showAllPatientsRadioButton");
		this.clickOn("#patientListView");
		this.type(KeyCode.PAGE_DOWN);
		this.clickOn("#addPatientButton");
		this.type(KeyCode.ENTER);
	}
}
