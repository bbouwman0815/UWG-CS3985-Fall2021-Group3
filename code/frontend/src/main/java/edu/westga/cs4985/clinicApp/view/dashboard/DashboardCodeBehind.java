package edu.westga.cs4985.clinicApp.view.dashboard;

import java.io.IOException;
import java.time.LocalDateTime;

import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import edu.westga.cs4985.clinicApp.viewmodel.PatientViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashboardCodeBehind {
	
	@FXML
	private Label upcomingLabel;

	@FXML
	private Label todayLabel;

    @FXML
    private Label pageLabel;

    @FXML
    private AnchorPane anchorPaneViewer;

    @FXML
    private Button generalInfoNavButton;

    @FXML
    private Button medicalConditionsNavButton;

    @FXML
    private Button appointmentNavButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Label welcomeText;

    @FXML
    private Label appointmentText;

    @FXML
    private Label dayText;

	private PatientViewModel viewModel;

	public DashboardCodeBehind() {

		this.viewModel = new PatientViewModel();
	}

	@FXML
	public void initialize() {
		this.pageLabel.setVisible(false);
		this.welcomeText.textProperty().set("Welcome, " + this.viewModel.getPatient().getFullName());
		this.appointmentText.textProperty().set("" + this.viewModel.futureppointmentList().size() + " is coming");
		this.dayText.textProperty().set(LocalDateTime.now().toString());
	}

	@FXML
	void handleNavigateAppointment(ActionEvent event) throws IOException {
		this.upcomingLabel.setVisible(false);
		this.todayLabel.setVisible(false);
		this.appointmentText.setVisible(false);
		this.dayText.setVisible(false);
		this.welcomeText.setVisible(false);
		this.pageLabel.setVisible(true);
		this.anchorPaneViewer.setVisible(true);
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../appointment/AppointmentGui.fxml"));
		this.anchorPaneViewer.getChildren().setAll(pane);
		this.pageLabel.textProperty().set("" + this.viewModel.getPatient().getFullName() + "'s Appointments");
	}

	@FXML
	void handleNavigateMedicalConditions(ActionEvent event) throws IOException {
		this.upcomingLabel.setVisible(false);
		this.todayLabel.setVisible(false);
		this.appointmentText.setVisible(false);
		this.dayText.setVisible(false);
		this.welcomeText.setVisible(false);
		this.pageLabel.setVisible(true);
		this.anchorPaneViewer.setVisible(true);
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../medicalconditions/MedicalConditionsGui.fxml"));
		this.anchorPaneViewer.getChildren().setAll(pane);
		this.pageLabel.textProperty().set("" + this.viewModel.getPatient().getFullName() + "'s Medical Conditions");
	}

	@FXML
	void handleNavigateToGeneralInfo(ActionEvent event) throws IOException {
		this.upcomingLabel.setVisible(false);
		this.todayLabel.setVisible(false);
		this.appointmentText.setVisible(false);
		this.dayText.setVisible(false);
		this.welcomeText.setVisible(false);
		this.pageLabel.setVisible(true);
		this.anchorPaneViewer.setVisible(true);
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../generalInfor/GeneralInfoGui.fxml"));
		this.anchorPaneViewer.getChildren().setAll(pane);
		this.pageLabel.textProperty().set("" + this.viewModel.getPatient().getFullName() + "'s General Information");
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
