package edu.westga.cs4985.clinicApp.resources;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import edu.westga.cs4985.clinicApp.ClinicApp;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.view.dashboard.DashboardCodeBehind;
import edu.westga.cs4985.clinicApp.view.login.NewMedicalPersonnelCodeBehind;
import edu.westga.cs4985.clinicApp.view.login.NewPatientCodeBehind;
import edu.westga.cs4985.clinicApp.view.medicalPersonnel.MedicalPersonnelCodeBehind;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowGenerator {

	private static final String PATIENT_PROFILE_TITLE = "Patient Profile";
	private static final String ADMIN_PROFILE_TITLE = "Admin";
	private static final String NEW_PATIENT_GUI = "NewPatientGui.fxml";
	private static final String DASHBOARD_GUI = "../view/dashboard/DashboardGui.fxml";
	private static final String LOGIN_GUI = "LoginGui";
	private static final String PATIENT_GENERAL_INFO = "GeneralInfoGui.fxml";
	private static final String MEDICAL_PERSONNEL_GUI = "MedicalPersonnelGui.fxml";
	private static final String MEDICAL_PERSONNEL_PROFILE_TITLE = "Medical Personnel";
	private static final String NEW_MEDICAL_PERSONNEL_GUI = "NewMedicalPersonnelGui.fxml";

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

	/**
	 * Change the current scene to another scene
	 * 
	 * @param currentStage   the current scene
	 * @param fxml           the location of destination scene
	 * @param controller     the controller of destination scene
	 * @param newWindowTitle the title of destination scene
	 * @throws IOException the IO exception
	 */
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

	/**
	 * Set user view for logged user
	 * 
	 * @param currentStage the current stage
	 * 
	 * @throws IOException the IO exception
	 */
	public static void setUserView(Stage currentStage) throws IOException {
		String fxml = null;
		Object controller = null;
		User user = User.user;
		if (user instanceof Patient) {
			fxml = ClinicApp.DASHBOARD_GUI;
			controller = new DashboardCodeBehind();
		}
		if (user instanceof MedicalPersonnel) {
			fxml = "view/medicalpersonnel/MedicalPersonnelGui.fxml";
			controller = new MedicalPersonnelCodeBehind();
		}
		WindowGenerator.changeScene(currentStage, fxml, controller, PATIENT_PROFILE_TITLE);
	}

	/**
	 * Open a popup for user to view
	 *
	 * @param fxml       the fxml location
	 * @param controller the controller
	 * @return the stage
	 * @throws IOException the IO exception
	 */
	public static Stage openPopup(String fxml, Object controller, String title) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setController(controller);
		loader.setLocation(controller.getClass().getResource(fxml));
		Pane pane = (Pane) loader.load();
		Stage popup = new Stage();
		Scene scene = new Scene(pane);
		popup.setScene(scene);
		popup.setResizable(false);
		popup.setTitle(title);
		popup.initModality(Modality.APPLICATION_MODAL);
		return popup;
	}

	/**
	 * Open alert for user
	 *
	 * @param errorMessage the error message
	 * @return the alert
	 */
	public static Alert openAlert(String errorMessage) {
		Alert alert = new Alert(AlertType.WARNING, errorMessage);
		return alert;
	}

	/**
	 * Open confirm for user
	 *
	 * @param confirmMessage the confirm message
	 * @return the alert
	 */
	public static Alert openConfirm(String confirmMessage) {
		Alert alert = new Alert(AlertType.CONFIRMATION, confirmMessage, ButtonType.YES, ButtonType.NO);
		return alert;

	}

	public static void setupAddNewPatient() throws IOException {
		NewPatientCodeBehind codebehind = new NewPatientCodeBehind();
		FXMLLoader loader = new FXMLLoader();
		loader.setController(codebehind);
		loader.setLocation(codebehind.getClass().getResource(NEW_PATIENT_GUI));
		WindowGenerator.setupScene((Parent) loader.load(), ADMIN_PROFILE_TITLE);
	}

	public static void setMedicalPersonnelView() throws IOException {
		MedicalPersonnelCodeBehind codebehind = new MedicalPersonnelCodeBehind();
		FXMLLoader loader = new FXMLLoader();
		loader.setController(codebehind);
		loader.setLocation(codebehind.getClass().getResource(MEDICAL_PERSONNEL_GUI));
		WindowGenerator.setupScene((Parent) loader.load(), MEDICAL_PERSONNEL_PROFILE_TITLE);

	}

	public static void setupAddNewMedicalPersonnel() throws IOException {
		NewMedicalPersonnelCodeBehind codebehind = new NewMedicalPersonnelCodeBehind();
		FXMLLoader loader = new FXMLLoader();
		loader.setController(codebehind);
		loader.setLocation(codebehind.getClass().getResource(NEW_MEDICAL_PERSONNEL_GUI));
		WindowGenerator.setupScene((Parent) loader.load(), ADMIN_PROFILE_TITLE);
		
	}
}
