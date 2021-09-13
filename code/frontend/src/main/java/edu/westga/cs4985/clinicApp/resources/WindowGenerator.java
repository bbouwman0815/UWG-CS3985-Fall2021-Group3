package edu.westga.cs4985.clinicApp.resources;

import java.io.IOException;

import edu.westga.cs4985.clinicApp.ClinicApp;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.utils.login.UToken;
import edu.westga.cs4985.clinicApp.view.dashboard.DashboardCodeBehind;
import edu.westga.cs4985.clinicApp.view.generalInfor.PatientGeneralInfoCodeBehind;
import edu.westga.cs4985.clinicApp.view.login.LoginCodeBehind;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowGenerator {

	private static final String PATIENT_PROFILE_TITLE = "Patient Profile";
	private static final String DASHBOARD_GUI = "../view/dashboard/DashboardGui.fxml";
	private static final String LOGIN_GUI = "LoginGui";
	private static final String PATIENT_GENERAL_INFO = "GeneralInfoGui.fxml";

	/**
	 * Sets up the Scene by using the window root and scene title
	 * 
	 * @param addWindowRoot the window root to set the scene to
	 * @param sceneTitle    the title of the new scene
	 * @precondition none
	 * @postcondition the new scene is shown
	 */
	public static void setupScene(Parent addWindowRoot, String sceneTitle) {
		try {
			Stage addStage = new Stage();
			Scene scene = new Scene(addWindowRoot);
			addStage.setScene(scene);
			addStage.setTitle(sceneTitle);
			addStage.setResizable(false);
			addStage.show();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}
	
	public static void changeScene(Stage currentStage, String fxml, Object controller, String newWindowTitle)
			throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ClinicApp.class.getResource(fxml));
		loader.setController(controller);
		loader.load();

		Scene scene = new Scene(loader.getRoot());
		currentStage.setScene(scene);
		currentStage.setTitle(newWindowTitle);
	}
	
	public static void setUserView(Stage currentStage) throws IOException {
		String fxml = null;
		Object controller = null;
		User user = User.user;
		if (user instanceof Patient) {
			fxml = ClinicApp.DASHBOARD_GUI;
			controller = new DashboardCodeBehind();
		}
		WindowGenerator.changeScene(currentStage, fxml, controller, PATIENT_PROFILE_TITLE);
	}

	public static void setupDashboardWindow() throws IOException {
		DashboardCodeBehind dashboardCodeBehind = new DashboardCodeBehind();
		FXMLLoader loader = new FXMLLoader();
		loader.setController(dashboardCodeBehind);
		loader.setLocation(dashboardCodeBehind.getClass().getResource(DASHBOARD_GUI));
		WindowGenerator.setupScene((Parent) loader.load(), PATIENT_PROFILE_TITLE);
	}

	public static void setupLoginWindow() throws IOException {
		LoginCodeBehind loginCodeBehind = new LoginCodeBehind();
		FXMLLoader loader = new FXMLLoader();
		loader.setController(loginCodeBehind);
		loader.setLocation(loginCodeBehind.getClass().getResource(LOGIN_GUI));
		WindowGenerator.setupScene((Parent) loader.load(), PATIENT_PROFILE_TITLE);
	}
	
	public static void setupGeneralInfoWindow() throws IOException {
		PatientGeneralInfoCodeBehind patientGeneralInfoCodeBehind = new PatientGeneralInfoCodeBehind();
		FXMLLoader loader = new FXMLLoader();
		loader.setController(patientGeneralInfoCodeBehind);
		loader.setLocation(patientGeneralInfoCodeBehind.getClass().getResource(PATIENT_GENERAL_INFO));
		WindowGenerator.setupScene((Parent) loader.load(), PATIENT_PROFILE_TITLE);
	}

}
