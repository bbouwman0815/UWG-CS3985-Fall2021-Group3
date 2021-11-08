package edu.westga.cs4985.clinicApp.test.UITesting;

import java.io.IOException;

import edu.westga.cs4985.clinicApp.ClinicApp;
import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.UserManager;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.TextInputControlMatchers;


public class TestLoginUI extends ApplicationTest {
	
	@Override
	public void start(Stage stage) throws IOException {
		ClinicApp app = new ClinicApp();
	    UserManager user = new UserManager(new ServerFake());
	    app.user = user;
	    app.start(stage);
	}
	
	private class ServerFake extends Communicator {


		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request.equals("USER_LOGIN,{\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"userName\":\"jimmy123\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"PATIENT\",\"userName\":\"jimmy123\",\"firstName\":\"Jimmy\",\"password\":\"b0baee9d279d34fa1dfd71aadb908c3f\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			} else {
				return "ERROR";
			}
		}
		
	}
	
	@Test
	public void testLoginWithButton() {
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
		this.clickOn("#loginButton");
	}
	
	@Test
	public void testLoginWithEnter() {
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
		this.type(KeyCode.ENTER);
		this.clickOn("#logoutButton");
	}
	
	@Test
	public void testInvlaidLoginWithEnter() {
		this.clickOn("#usernameTextField");
		this.type(KeyCode.J);
		this.type(KeyCode.I);
		this.type(KeyCode.M);
		this.type(KeyCode.M);
		this.type(KeyCode.Y);
		this.type(KeyCode.DIGIT1);
		this.clickOn("#passwordTextField");
		this.type(KeyCode.P);
		this.type(KeyCode.A);
		this.type(KeyCode.S);
		this.type(KeyCode.S);
		this.type(KeyCode.W);
		this.type(KeyCode.O);
		this.type(KeyCode.R);
		this.type(KeyCode.D);
		this.type(KeyCode.ENTER);
	}

}
