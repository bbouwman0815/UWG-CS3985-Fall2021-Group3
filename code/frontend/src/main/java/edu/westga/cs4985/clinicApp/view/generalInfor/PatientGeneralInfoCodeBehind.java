package edu.westga.cs4985.clinicApp.view.generalInfor;

import java.io.IOException;

import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;
import edu.westga.cs4985.clinicApp.utils.login.UToken;
import edu.westga.cs4985.clinicApp.viewmodel.PatientAppointmentViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * The code behind for the patient general information view.
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class PatientGeneralInfoCodeBehind {

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
    private TextField stateInput;

    @FXML
    private TextField countryInput;

    @FXML
    private Button addCaregiverButton;

    @FXML
    private Button editbutton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;
    
    @FXML
    private ChoiceBox<String> raceChoiceBox;

    @FXML
    private ChoiceBox<String> sexChoiceBox;

    @FXML
    private ChoiceBox<String> ethnicityChoiceBox;
    
    @FXML
    private TextField insuranceInput;

    @FXML
    private ChoiceBox<?> stateChoiceBox;

    @FXML
    private ChoiceBox<String> countryChoiceBox;

    @FXML
    private DatePicker birthdayPicker;
    
	@FXML
	private Button dashboardNavButton;

	@FXML
	private Button medicationsNavButton;

	@FXML
	private Button medicalConditionsNavButton;

	@FXML
	private Button appointmentNavButton;
    
    private Race race;
    private Gender gender;
    private Ethnicity ethnicity;
    private Country country;
    
    private PatientAppointmentViewModel viewModel;

	/**
	 * Initialize Construct.
	 * 
	 * @precondition none
	 * 
	 * @postcondition none
	 */
    public PatientGeneralInfoCodeBehind() {
    	this.race = new Race();
    	this.gender = new Gender();
    	this.ethnicity = new Ethnicity();
    	this.country = new Country();
    	this.viewModel = new PatientAppointmentViewModel();
    }
    
    @FXML
    public void initialize() {
    	this.saveButton.setVisible(false);
    	this.cancelButton.setVisible(false);
    	this.setUpChoiceBoxes();
    	this.setupBindings();
    	this.formActivation(true);
    	this.setForm();
    }
    
    private void formActivation(boolean action) {
    	this.firstNameInput.setDisable(action);
    	this.lastNameInput.setDisable(action);
    	this.descriptionInput.setDisable(action);
    	this.phoneInput.setDisable(action);
    	this.emailInput.setDisable(action);
    	this.address1Input.setDisable(action);
    	this.address2Input.setDisable(action);
    	this.cityInput.setDisable(action);
    	this.stateInput.setDisable(action);
    	this.insuranceInput.setDisable(action);
    	this.birthdayPicker.setDisable(action);
    	this.birthdayPicker.setDisable(action);
    	this.raceChoiceBox.setDisable(action);
    	this.sexChoiceBox.setDisable(action);
    	this.ethnicityChoiceBox.setDisable(action);
    	this.countryChoiceBox.setDisable(action);
    	this.addCaregiverButton.setDisable(action);
    }
    
    private void setUpChoiceBoxes() {
    	this.raceChoiceBox.itemsProperty().set(FXCollections.observableArrayList(this.race.race));
    	this.sexChoiceBox.itemsProperty().set(FXCollections.observableArrayList(this.gender.sex));
    	this.ethnicityChoiceBox.itemsProperty().set(FXCollections.observableArrayList(this.ethnicity.ethnicity));
    	this.countryChoiceBox.itemsProperty().set(FXCollections.observableArrayList(this.country.country));
    }
    
    private void setupBindings() {
    }

    @FXML
    void addCaregiver(ActionEvent event) {
    	//the print codes are just testing code to see if I can actually get the choice value 
    	System.out.println(this.country.country[this.countryChoiceBox.getSelectionModel().selectedIndexProperty().getValue()]);
    	System.out.println(this.race.race[this.raceChoiceBox.getSelectionModel().selectedIndexProperty().getValue()]);
    	System.out.println(this.ethnicity.ethnicity[this.ethnicityChoiceBox.getSelectionModel().selectedIndexProperty().getValue()]);
    	System.out.println(this.gender.sex[this.sexChoiceBox.getSelectionModel().selectedIndexProperty().getValue()]);
    	System.out.println(this.birthdayPicker.getValue());
    }

    @FXML
    void cancel(ActionEvent event) {

    }
    
    private void setForm() {
		try {
			// code to communicate with server to retrieve patient object to fill out
			// general info form
			int adminHash = new String("Admin" + "123").hashCode() * 66;
			//instead of if statement, replace with class that communicates w/ server
			if (User.user.getUserToken().getTokenId() == adminHash) {
				// fill form -- still need all fields
				Patient patient = this.samplePatient();
				this.firstNameInput.setText(patient.getFirstName());
				this.lastNameInput.setText(patient.getLastName());
				this.descriptionInput.setText("Sample");
				this.phoneInput.setText(patient.getPhoneNumber());
				this.emailInput.setText(patient.getEmail());
				this.address1Input.setText(patient.getAddress1());
				this.address2Input.setText(patient.getAddress2());
				this.cityInput.setText(patient.getCity());
				this.stateInput.setText(patient.getState());
				this.ethnicityChoiceBox.setValue(patient.getEthnicity());
				this.countryChoiceBox.setValue(patient.getCountry());
				this.raceChoiceBox.setValue(patient.getRace());
				this.sexChoiceBox.setValue(patient.getGender());
				this.insuranceInput.setText(patient.getInsurance());
			}
		} catch (IllegalArgumentException e) {

		}
	}
    
    private Patient samplePatient() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patientDummy = new Patient("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
				"770-111-222", "email@email.com", "United Healthcare");
		return patientDummy;
	}

    @FXML
    void editGeneralInfo(ActionEvent event) {
    	this.editbutton.setVisible(false);
    	this.saveButton.setVisible(true);
    	this.cancelButton.setVisible(true);
    	this.formActivation(false);

    }

    @FXML
    void getAddress1(ActionEvent event) {

    }

    @FXML
    void getAddress2(ActionEvent event) {

    }

    @FXML
    void getCity(ActionEvent event) {

    }
    
    @FXML
    void getState(ActionEvent event) {
    	
    }

    @FXML
    void getEmail(ActionEvent event) {

    }

    @FXML
    void getFirstName(ActionEvent event) {

    }

    @FXML
    void getLastName(ActionEvent event) {

    }

    @FXML
    void getPhone(ActionEvent event) {

    }
    
    @FXML
    void getInsurance(ActionEvent event) {

    }


    @FXML
    void saveGeneralInfo(ActionEvent event) {
    	this.editbutton.setVisible(true);
    	this.saveButton.setVisible(false);
    	this.cancelButton.setVisible(false);
    	this.formActivation(true);
    }
    
	@FXML
	void handleNavigateAppointment(ActionEvent event) throws IOException {
	}

	@FXML
	void handleNavigateMedicalConditions(ActionEvent event) {

	}

	@FXML
	void handleNavigateMedications(ActionEvent event) {

	}

	@FXML
	void handleNavigateToDashboard(ActionEvent event) throws IOException {
		Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currentStage.close();
		WindowGenerator.setupDashboardWindow();
	}

}

