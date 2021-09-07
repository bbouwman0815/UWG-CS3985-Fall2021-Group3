package edu.westga.cs4985.clinicApp.view.dashboard;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class DashboardCodeBehind {

	@FXML
	private GridPane navbarGridPane;

	@FXML
	private Button generalInfoNavButton;

	@FXML
	private Button medicationsNavButton;

	@FXML
	private Button medicalConditionsNavButton;

	@FXML
	private Button appointmentNavButton;
	
    @FXML
    private AnchorPane anchorPaneViewer;

	public DashboardCodeBehind() {

	}

	@FXML
	void handleNavigateAppointment(ActionEvent event) {

	}

	@FXML
	void handleNavigateMedicalConditions(ActionEvent event) {

	}

	@FXML
	void handleNavigateMedications(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../medications/MedicationsGUI.fxml"));
		this.anchorPaneViewer.getChildren().setAll(pane);
	}

	@FXML
	void handleNavigateToGeneralInfo(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../generalInfor/PatientGeneralInfoGui.fxml"));
		this.anchorPaneViewer.getChildren().setAll(pane);
	}

}
