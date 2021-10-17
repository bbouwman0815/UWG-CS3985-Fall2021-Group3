package edu.westga.cs4985.clinicApp.view.dashboard;

import java.io.IOException;

import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import edu.westga.cs4985.clinicApp.viewmodel.PatientViewModel;
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
    
    @FXML
    private Button logoutButton;
    
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
	
    @FXML
    void handleLogout(ActionEvent event) {
    	Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		try {
			WindowGenerator.setUpLogin(currentStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
