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

class TestRegisterCaregiver extends ApplicationTest {

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

			if (request.equals("GET_CAREGIVER_BY_USER_NAME,{\"Caregiver\":\"jimmy123\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"Caregiver\",\"userName\":\"jimmy123\",\"firstName\":\"Jimmy\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			} else {
				return "ERROR";
			}
		}
		
	}
	
	@Test
	public void testRegisterCaregiver() throws InterruptedException {
		
		this.clickOn("#registerButton");
		this.clickOn("#roleSelecter");
		this.type(KeyCode.PAGE_DOWN);
		this.type(KeyCode.PAGE_DOWN);
		this.type(KeyCode.ENTER);
		
		this.clickOn("#goButton");
		
		this.clickOn("#usernameTextField");
		this.type(KeyCode.T);
		this.type(KeyCode.I);
		this.type(KeyCode.M);
		this.type(KeyCode.I);
		this.clickOn("#passwordTextField");
		this.type(KeyCode.P);
		this.type(KeyCode.A);
		this.type(KeyCode.S);
		this.type(KeyCode.S);
		this.type(KeyCode.W);
		this.type(KeyCode.O);
		this.type(KeyCode.R);
		this.type(KeyCode.D);
		this.clickOn("#firstNameInput");
		this.type(KeyCode.T);
		this.type(KeyCode.I);
		this.type(KeyCode.M);
		this.type(KeyCode.I);
		this.clickOn("#lastNameInput");
		this.type(KeyCode.L);
		this.type(KeyCode.I);
		this.type(KeyCode.N);
		this.type(KeyCode.G);
		this.clickOn("#sexChoiceBox");
		this.type(KeyCode.KP_DOWN);
		this.type(KeyCode.ENTER);
		this.clickOn("#ethnicityChoiceBox");
		this.type(KeyCode.KP_DOWN);
		this.type(KeyCode.ENTER);
		this.clickOn("#raceChoiceBox");
		this.type(KeyCode.KP_DOWN);
		this.type(KeyCode.ENTER);
		this.clickOn("#birthdayTextField");
		this.type(KeyCode.DIGIT1);
		this.type(KeyCode.DIGIT9);
		this.type(KeyCode.DIGIT9);
		this.type(KeyCode.DIGIT2);
		this.type(KeyCode.MINUS);
		this.type(KeyCode.DIGIT0);
		this.type(KeyCode.DIGIT9);
		this.type(KeyCode.MINUS);
		this.type(KeyCode.DIGIT0);
		this.type(KeyCode.DIGIT9);
		this.clickOn("#phoneInput");
		this.type(KeyCode.DIGIT6);
		this.type(KeyCode.DIGIT7);
		this.type(KeyCode.DIGIT8);
		this.type(KeyCode.DIGIT0);
		this.type(KeyCode.DIGIT9);
		this.type(KeyCode.DIGIT7);
		this.type(KeyCode.DIGIT7);
		this.type(KeyCode.DIGIT5);
		this.type(KeyCode.DIGIT7);
		this.type(KeyCode.DIGIT5);
		this.clickOn("#emailInput");
		this.type(KeyCode.T);
		this.type(KeyCode.I);
		this.type(KeyCode.M);
		this.type(KeyCode.I);
		this.type(KeyCode.AT);
		this.type(KeyCode.G);
		this.type(KeyCode.M);
		this.type(KeyCode.A);
		this.type(KeyCode.I);
		this.type(KeyCode.L);
		this.type(KeyCode.DECIMAL);
		this.type(KeyCode.C);
		this.type(KeyCode.O);
		this.type(KeyCode.M);
		this.clickOn("#address1Input");
		this.type(KeyCode.A);
		this.type(KeyCode.T);
		this.type(KeyCode.L);
		this.type(KeyCode.A);
		this.type(KeyCode.N);
		this.type(KeyCode.T);
		this.type(KeyCode.A);
		this.type(KeyCode.SPACE);
		this.type(KeyCode.N);
		this.type(KeyCode.E);
		this.type(KeyCode.W);
		this.type(KeyCode.SPACE);
		this.type(KeyCode.W);
		this.type(KeyCode.A);
		this.type(KeyCode.Y);
		this.clickOn("#address2Input");
		this.type(KeyCode.N);
		this.type(KeyCode.O);
		this.type(KeyCode.N);
		this.type(KeyCode.E);
		this.clickOn("#cityInput");
		this.type(KeyCode.A);
		this.type(KeyCode.T);
		this.type(KeyCode.L);
		this.type(KeyCode.A);
		this.type(KeyCode.N);
		this.type(KeyCode.T);
		this.type(KeyCode.A);
		this.clickOn("#stateInput");
		this.type(KeyCode.G);
		this.type(KeyCode.A);
		this.clickOn("#countryChoiceBox");
		this.type(KeyCode.KP_DOWN);
		this.type(KeyCode.ENTER);
		this.clickOn("#addCaregiver");
	}
	
	@Test
	public void testRegisterCaregiverExists() throws InterruptedException {
		
		this.clickOn("#registerButton");
		this.clickOn("#cancelButton");
		
		this.clickOn("#registerButton");
		this.clickOn("#roleSelecter");
		this.type(KeyCode.PAGE_DOWN);
		this.type(KeyCode.PAGE_DOWN);
		this.type(KeyCode.ENTER);
		
		this.clickOn("#goButton");
		this.clickOn("#addCaregiver");
		
		this.clickOn("#usernameTextField");
		this.type(KeyCode.J);
		this.type(KeyCode.I);
		this.type(KeyCode.M);
		this.type(KeyCode.M);
		this.type(KeyCode.Y);
		this.type(KeyCode.DIGIT1);
		this.type(KeyCode.DIGIT2);
		this.type(KeyCode.DIGIT3);
		this.clickOn("#passwordTextField");
		this.type(KeyCode.P);
		this.type(KeyCode.A);
		this.type(KeyCode.S);
		this.type(KeyCode.S);
		this.type(KeyCode.W);
		this.type(KeyCode.O);
		this.type(KeyCode.R);
		this.type(KeyCode.D);
		
		this.clickOn("#firstNameInput");
		this.type(KeyCode.SPACE);
		this.clickOn("#lastNameInput");
		this.type(KeyCode.SPACE);
		this.clickOn("#birthdayTextField");
		this.type(KeyCode.SPACE);
		this.clickOn("#phoneInput");
		this.type(KeyCode.SPACE);
		this.clickOn("#emailInput");
		this.type(KeyCode.SPACE);
		this.clickOn("#address1Input");
		this.type(KeyCode.SPACE);
		this.clickOn("#address2Input");
		this.type(KeyCode.SPACE);
		this.clickOn("#cityInput");
		this.type(KeyCode.SPACE);
		this.clickOn("#stateInput");
		this.type(KeyCode.SPACE);
		this.clickOn("#addCaregiver");
	}

}
