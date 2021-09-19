package edu.westga.cs4985.clinicApp.view.dashboard;

import java.io.IOException;

import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DashboardCodeBehind {

	@FXML
    private Label pageLabel;
	
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
	void handleNavigateAppointment(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../appointment/AppointmentGui.fxml"));
		this.anchorPaneViewer.getChildren().setAll(pane);
		this.pageLabel.textProperty().set("Patient's Appointments");
	}

	@FXML
	void handleNavigateMedicalConditions(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../medicalconditions/MedicalConditionsGui.fxml"));
		this.anchorPaneViewer.getChildren().setAll(pane);
		this.pageLabel.textProperty().set("Patient's Medical Conditions");
	}

	@FXML
	void handleNavigateMedications(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../medications/MedicationsGUI.fxml"));
		this.anchorPaneViewer.getChildren().setAll(pane);
		this.pageLabel.textProperty().set("Patient's Medications");
	}

	@FXML
	void handleNavigateToGeneralInfo(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../generalInfor/GeneralInfoGui.fxml"));
		this.anchorPaneViewer.getChildren().setAll(pane);
		this.pageLabel.textProperty().set("Patient's General Inforamtion");
	}

}
