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

public class TestCancelAppointmentByMedicalPersonnel  extends ApplicationTest {
	
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

			if (request.equals("USER_LOGIN,{\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"userName\":\"jimmy123\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"MedicalPersonnel\",\"userName\":\"jimmy123\",\"firstName\":\"Jimmy\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			}else if (request.equals("GET_USER_BY_MEDICAL_PERSONNEL_USERNAME,{\"medicalPersonnel\":\"jimmy123\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"MedicalPersonnel\",\"userName\":\"jimmy123\",\"firstName\":\"Jimmy\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			}else if (request.equals("GET_USER_BY_USERNAME,{\"patient\":\"jimmy1\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"PATIENT\",\"userName\":\"jimmy1\",\"firstName\":\"Jimmy\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			} else if (request.equals("GET_APPOINTMENTS_FOR_MEDICAL_PEROSNNEL,{\"medicalPersonnel\":\"jimmy123\"}")) {
				return "[{\"date\":\"2024-11-01T13:00\",\"notes\":\"new\",\"patient\":\"jimmy1\",\"location\":\"TCL\",\"medicalPersonnel\":\"jimmy123\"},{\"date\":\"2020-11-01T10:00\",\"notes\":\"\",\"patient\":\"jimmy1\",\"location\":\"1601 Maple St, Carronllton, GA, Bhutan\",\"medicalPersonnel\":\"jimmy123\"}]";
			} else {
				return "ERROR";
			}
		}
		
	}
	
	@Test
	public void testCancelAppointment() throws InterruptedException {
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
		
		this.clickOn("#appointmentTab");
		this.clickOn("#futureAppointmentList");
		this.type(KeyCode.ENTER);
		this.doubleClickOn("#futureAppointmentList");
		this.clickOn("#cancelAppointmentButton");
		this.type(KeyCode.ENTER);
		this.clickOn("#showPast");
		
		this.clickOn("#pastAppointmentList");
		this.type(KeyCode.ENTER);
		this.doubleClickOn("#pastAppointmentList");
		this.clickOn("#OKButton");
		this.type(KeyCode.ENTER);
		
		this.clickOn("#showfutureButton");
		
		
	}
	
	@Test
	public void testViewAndEditNotesAppointment() throws InterruptedException {
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
		
		this.clickOn("#appointmentTab");
		this.clickOn("#futureAppointmentList");
		this.type(KeyCode.ENTER);
		this.doubleClickOn("#futureAppointmentList");
		
		this.clickOn("#editButton");
		this.clickOn("#appointmentNotes");
		this.type(KeyCode.P);
		this.clickOn("#saveButton");
		this.clickOn("#OKButton");
		
	}

}

