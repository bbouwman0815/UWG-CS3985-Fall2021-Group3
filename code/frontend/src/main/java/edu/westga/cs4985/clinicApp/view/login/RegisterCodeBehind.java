package edu.westga.cs4985.clinicApp.view.login;

import java.io.IOException;

import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class RegisterCodeBehind {

	public static final String[] ROLELIST = { "Patient", "Medical Personnel", "Caregiver" };

	@FXML
	private ComboBox<String> roleSelecter;

	@FXML
	private void initialize() {
		this.roleSelecter.itemsProperty().set(FXCollections.observableArrayList(ROLELIST));
	}

	@FXML
	void onCancel(ActionEvent event) {

		Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
		currentStage.close();

	}

	@FXML
	void onGo(ActionEvent event) throws IOException {

		if (this.roleSelecter.getValue() == null) {
			Alert alert = WindowGenerator.openAlert("Please select your role!");

			alert.showAndWait();
		} else if (this.roleSelecter.getValue().equals("Patient")) {
			WindowGenerator.setupAddNewPatient();
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
			currentStage.close();

		} else if (this.roleSelecter.getValue().equals("Medical Personnel")) {
			WindowGenerator.setupAddNewMedicalPersonnel();
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
			currentStage.close();

		} else if (this.roleSelecter.getValue().equals("Caregiver")) {
			WindowGenerator.setupAddNewCaregiver();
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
			currentStage.close();
		}

	}

}
