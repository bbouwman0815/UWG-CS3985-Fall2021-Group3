package edu.westga.cs4985.clinicApp.view.medicalPersonnel;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.viewmodel.MedicalPersonnelViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * The Class MedicalPersonnelCodeBehind.
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 * 
 */
public class MedicalPersonnelCodeBehind {

	@FXML
	private TextField firstNameInput;

	@FXML
	private TextField lastNameInput;

	@FXML
	private TextArea descriptionInput;

	@FXML
	private TextField phoneInput;

	@FXML
	private TextField emailInput;

	@FXML
	private TextField address1Input;

	@FXML
	private TextField address2Input;

	@FXML
	private TextField cityInput;

	@FXML
	private Label caregiverLabel;

	@FXML
	private ChoiceBox<String> raceChoiceBox;

	@FXML
	private ChoiceBox<String> sexChoiceBox;

	@FXML
	private ChoiceBox<String> ethnicityChoiceBox;

	@FXML
	private TextField insuranceInput;

	@FXML
	private ChoiceBox<String> countryChoiceBox;

	@FXML
	private DatePicker birthdayPicker;

	@FXML
	private TextField stateInput;

	@FXML
	private ListView<Patient> patientListView;

	@FXML
	private Tab medicalConditionsTab;

	@FXML
	private TabPane patientTabPane;
	
	@FXML
	private Tab appointmentTab;
	
    @FXML
    private Tab generalInfoTab;
    
    @FXML
    private AnchorPane appointmentPane;

	private MedicalPersonnelViewModel viewmodel;

	public MedicalPersonnelCodeBehind() {
		this.viewmodel = new MedicalPersonnelViewModel();
		this.viewmodel.loadPatients();
	}

	@FXML
	public void initialize() throws IOException {
		this.setBindings();
		this.setListeners();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../appointment/MedicalPersonnelAppointmentGui.fxml"));
		this.appointmentPane.getChildren().setAll(pane);
	}

	public void setBindings() {
		this.patientListView.itemsProperty().bindBidirectional(this.viewmodel.patientsListProperty());
		this.viewmodel.selectedPatientProperty().bind(this.patientListView.getSelectionModel().selectedItemProperty());
	}

	public void setListeners() {
		this.patientListView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						try {
							this.loadPatientData();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
	}

	private void loadPatientData() {
		Patient selectedPatient = this.viewmodel.selectedPatient();
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate datetime = LocalDate.parse(selectedPatient.getDateOfBirth(), pattern);
		this.firstNameInput.setText(selectedPatient.getFirstName());
		this.lastNameInput.setText(selectedPatient.getLastName());
		this.phoneInput.setText(selectedPatient.getPhoneNumber());
		this.emailInput.setText(selectedPatient.getEmail());
		this.address1Input.setText(selectedPatient.getAddress1());
		this.address2Input.setText(selectedPatient.getAddress2());
		this.cityInput.setText(selectedPatient.getCity());
		this.caregiverLabel.setText((selectedPatient.getCaregiver()));
		this.stateInput.setText(selectedPatient.getState());
		this.ethnicityChoiceBox.setValue(selectedPatient.getEthnicity());
		this.countryChoiceBox.setValue(selectedPatient.getCountry());
		this.raceChoiceBox.setValue(selectedPatient.getRace());
		this.sexChoiceBox.setValue(selectedPatient.getGender());
		this.insuranceInput.setText(selectedPatient.getInsurance());
		this.birthdayPicker.setValue(datetime);
		this.caregiverLabel.setText(selectedPatient.getCaregiver());
	}

}
