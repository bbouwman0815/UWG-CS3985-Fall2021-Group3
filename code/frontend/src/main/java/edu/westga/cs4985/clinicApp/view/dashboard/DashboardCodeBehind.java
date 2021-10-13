package edu.westga.cs4985.clinicApp.view.dashboard;

import java.io.IOException;

<<<<<<< HEAD
=======
import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import edu.westga.cs4985.clinicApp.viewmodel.PatientViewModel;
>>>>>>> master
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

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
    
    private PatientViewModel viewModel;

	public DashboardCodeBehind() {
		
		this.viewModel = new PatientViewModel();
	}
	
	@FXML
	public void initialize() {
		this.pageLabel.textProperty().set("" + this.viewModel.getPatient().getFullName() + "'s Dashboard");
	}

	@FXML
	void handleNavigateAppointment(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../appointment/AppointmentGui.fxml"));
		this.anchorPaneViewer.getChildren().setAll(pane);
		this.pageLabel.textProperty().set("" + this.viewModel.getPatient().getFullName() + "'s Appointments");
	}

	@FXML
	void handleNavigateMedicalConditions(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../medicalconditions/MedicalConditionsGui.fxml"));
		this.anchorPaneViewer.getChildren().setAll(pane);
		this.pageLabel.textProperty().set("" + this.viewModel.getPatient().getFullName() + "'s Medical Conditions");
	}

	@FXML
	void handleNavigateToGeneralInfo(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../generalInfor/GeneralInfoGui.fxml"));
		this.anchorPaneViewer.getChildren().setAll(pane);
		this.pageLabel.textProperty().set("" + this.viewModel.getPatient().getFullName() + "'s General Inforamtion");
	}

}
