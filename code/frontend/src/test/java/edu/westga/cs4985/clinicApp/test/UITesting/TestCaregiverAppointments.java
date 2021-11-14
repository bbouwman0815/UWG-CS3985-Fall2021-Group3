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

public class TestCaregiverAppointments extends ApplicationTest {
	
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
			if (request.equals("GET_CAREGIVER_PATIENTS,{\"caregiver\":\"jimmy1234\"}")) {
				return "[\"jimmy123\"]";
			} else if (request.equals("USER_LOGIN,{\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"userName\":\"jimmy1234\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"Caregiver\",\"userName\":\"jimmy1234\",\"firstName\":\"Jimmy\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			} else if (request.equals("GET_USER_BY_USERNAME,{\"patient\":\"jimmy123\"}")) {
				return "{\"insurance\":\"8888888888\",\"lastName\":\"Bob\",\"country\":\"USA\",\"gender\":\"male\",\"race\":\"American Indian or Alaska Native\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"3433 Atlanta Peachway\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"PATIENT\",\"userName\":\"jimmy123\",\"firstName\":\"Jimmy\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"123456789\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"Caregiver C\",\"state\":\"GA\",\"email\":\"jimmy12334@gmail.com\"}";
			} else if (request.equals("GET_USER_BY_MEDICAL_PERSONNEL_USERNAME,{\"medicalPersonnel\":\"jimmy1\"}")) {
				return "{\"lastName\":\"asdfasdf\",\"country\":\"Bhutan\",\"gender\":\"Female\",\"race\":\"Asian\",\"address2\":\"123\",\"city\":\"Carronllton\",\"address1\":\"1601 Maple St\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"MedicalPersonnel\",\"userName\":\"jimmy1\",\"zipcode\":\"30118\",\"firstName\":\"asdfasdf\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"4444444444\",\"ethnicty\":\"Hispanic or Latino\",\"caregiver\":\"\",\"state\":\"GA\",\"email\":\"temail@email\"}";
			} else if (request.equals("GET_ALL_MEDICAL_PERSONNELS,{\"zipcode\":\"30118\"}")) {
				return "[{\"lastName\":\"asdfasdf\",\"country\":\"Bhutan\",\"gender\":\"Female\",\"race\":\"Asian\",\"address2\":\"123\",\"city\":\"Carronllton\",\"address1\":\"1601 Maple St\",\"dateOfBirth\":\"1990-09-29\",\"type\":\"MedicalPersonnel\",\"userName\":\"jimmy1\",\"zipcode\":\"30118\",\"firstName\":\"asdfasdf\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"4444444444\",\"ethnicty\":\"Hispanic or Latino\",\"caregiver\":\"\",\"state\":\"GA\",\"email\":\"temail@email\"}]";
			} else if (request.equals("GET_APPOINTMENTS,{\"patient\":\"jimmy123\"}")) {
				return "[{\"date\":\"2024-11-01T13:00\",\"notes\":\"new\",\"patient\":\"jimmy123\",\"location\":\"TCL\",\"medicalPersonnel\":\"jimmy1\"},{\"date\":\"2020-11-01T10:00\",\"notes\":\"\",\"patient\":\"jimmy123\",\"location\":\"1601 Maple St, Carronllton, GA, Bhutan\",\"medicalPersonnel\":\"jimmy1\"}]";
			} else if (request.equals("GET_AVAILABILITIES,{\"medicalPersonnel\":\"jimmy1\"}")) {
				return "[\"2022-10-29T13:00\"]";
			} else { 
				return "ERROR";  
			}
		}
		
	}
	
	@Test
	public void testPatientFutureAndBookAppointment() throws InterruptedException {
		this.clickOn("#usernameTextField");
		this.type(KeyCode.J);
		this.type(KeyCode.I);
		this.type(KeyCode.M);
		this.type(KeyCode.M);
		this.type(KeyCode.Y);
		this.type(KeyCode.DIGIT1);
		this.type(KeyCode.DIGIT2);
		this.type(KeyCode.DIGIT3);
		this.type(KeyCode.DIGIT4);
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
		this.clickOn("#patientListView");
		this.type(KeyCode.PAGE_UP);
		
		this.clickOn("#appointmentTab");
		
		this.clickOn("#futureAppointmentList");
		this.type(KeyCode.ENTER);
		this.clickOn("#futureAppointmentList");
		this.clickOn("#cancelAppointmentButton");
		this.type(KeyCode.ENTER);

		
		this.clickOn("#bookAppointment");
		
		this.clickOn("#bookAnAppointment");
		this.type(KeyCode.ENTER);
		
		this.clickOn("#zipcodeInput");
		this.type(KeyCode.DIGIT3);
		this.type(KeyCode.DIGIT0);
		this.type(KeyCode.DIGIT1);
		this.type(KeyCode.DIGIT1);
		this.type(KeyCode.DIGIT8);
		this.clickOn("#search");
		
		this.clickOn("#bookAnAppointment");
		this.type(KeyCode.ENTER);
		
		this.clickOn("#medicalPersonList");
		this.type(KeyCode.ENTER);
		this.clickOn("#medicalPersonList");
		
		this.clickOn("#bookAnAppointment");
		this.type(KeyCode.ENTER);
		
		this.clickOn("#availableTimeList");
		this.type(KeyCode.ENTER);
		this.clickOn("#availableTimeList");
		
		this.clickOn("#noteTextBox");
		this.type(KeyCode.A);
		
		this.clickOn("#bookAnAppointment");
		this.type(KeyCode.ENTER);
		this.clickOn("#oKButton");
	}
	
	@Test
	public void testPatientPastAppointment() throws InterruptedException {
		this.clickOn("#usernameTextField");
		this.type(KeyCode.J);
		this.type(KeyCode.I);
		this.type(KeyCode.M);
		this.type(KeyCode.M);
		this.type(KeyCode.Y);
		this.type(KeyCode.DIGIT1);
		this.type(KeyCode.DIGIT2);
		this.type(KeyCode.DIGIT3);
		this.type(KeyCode.DIGIT4);
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
		this.clickOn("#patientListView");
		this.type(KeyCode.PAGE_UP);
		
		this.clickOn("#appointmentTab");
		
		this.clickOn("#pastAppointmentList");
		this.type(KeyCode.ENTER);
		this.clickOn("#pastAppointmentList");
		this.clickOn("#oKButton");
		
	}
	
	@Test
	public void testLongZipCode() throws InterruptedException {
		this.clickOn("#usernameTextField");
		this.type(KeyCode.J);
		this.type(KeyCode.I);
		this.type(KeyCode.M);
		this.type(KeyCode.M);
		this.type(KeyCode.Y);
		this.type(KeyCode.DIGIT1);
		this.type(KeyCode.DIGIT2);
		this.type(KeyCode.DIGIT3);
		this.type(KeyCode.DIGIT4);
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
		this.clickOn("#patientListView");
		this.type(KeyCode.PAGE_UP);
		
		this.clickOn("#appointmentTab");
		
		
		this.clickOn("#bookAppointment");
		
		this.clickOn("#zipcodeInput");
		this.type(KeyCode.DIGIT3);
		this.type(KeyCode.DIGIT0);
		this.type(KeyCode.DIGIT1);
		this.type(KeyCode.DIGIT1);
		this.type(KeyCode.DIGIT8);
		this.type(KeyCode.DIGIT8);
		this.clickOn("#search");
	}
	
	@Test
	public void testShortZipCode() throws InterruptedException {
		this.clickOn("#usernameTextField");
		this.type(KeyCode.J);
		this.type(KeyCode.I);
		this.type(KeyCode.M);
		this.type(KeyCode.M);
		this.type(KeyCode.Y);
		this.type(KeyCode.DIGIT1);
		this.type(KeyCode.DIGIT2);
		this.type(KeyCode.DIGIT3);
		this.type(KeyCode.DIGIT4);
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
		this.clickOn("#patientListView");
		this.type(KeyCode.PAGE_UP);
		
		this.clickOn("#appointmentTab");
		
		
		this.clickOn("#bookAppointment");
		
		this.clickOn("#zipcodeInput");
		this.type(KeyCode.DIGIT3);
		this.type(KeyCode.DIGIT0);
		this.clickOn("#search");
	}
	
	@Test
	public void testLetterZipCode() throws InterruptedException {
		this.clickOn("#usernameTextField");
		this.type(KeyCode.J);
		this.type(KeyCode.I);
		this.type(KeyCode.M);
		this.type(KeyCode.M);
		this.type(KeyCode.Y);
		this.type(KeyCode.DIGIT1);
		this.type(KeyCode.DIGIT2);
		this.type(KeyCode.DIGIT3);
		this.type(KeyCode.DIGIT4);
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
		this.clickOn("#patientListView");
		this.type(KeyCode.PAGE_UP);
		
		this.clickOn("#appointmentTab");
		
		
		this.clickOn("#bookAppointment");
		
		this.clickOn("#zipcodeInput");
		this.type(KeyCode.A);
		this.clickOn("#search");
		this.type(KeyCode.ENTER);
		this.clickOn("#cancelBook");
	}
	
	@Test
	public void testNoMedicalPersonnelExist() throws InterruptedException {
		this.clickOn("#usernameTextField");
		this.type(KeyCode.J);
		this.type(KeyCode.I);
		this.type(KeyCode.M);
		this.type(KeyCode.M);
		this.type(KeyCode.Y);
		this.type(KeyCode.DIGIT1);
		this.type(KeyCode.DIGIT2);
		this.type(KeyCode.DIGIT3);
		this.type(KeyCode.DIGIT4);
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
		this.clickOn("#patientListView");
		this.type(KeyCode.PAGE_UP);
		
		this.clickOn("#appointmentTab");
		
		
		this.clickOn("#bookAppointment");
		
		this.clickOn("#zipcodeInput");
		this.type(KeyCode.DIGIT1);
		this.type(KeyCode.DIGIT2);
		this.type(KeyCode.DIGIT3);
		this.type(KeyCode.DIGIT2);
		this.type(KeyCode.DIGIT3);
		this.clickOn("#search");
		this.type(KeyCode.ENTER);
		this.clickOn("#cancelBook");
	}

}
