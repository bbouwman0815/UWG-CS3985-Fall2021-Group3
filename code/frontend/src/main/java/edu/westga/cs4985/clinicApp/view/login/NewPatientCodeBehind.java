package edu.westga.cs4985.clinicApp.view.login;

import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.resources.InputValidators;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;
import edu.westga.cs4985.clinicApp.viewmodel.PatientViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The Class NewPatientCodeBehind.
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 * 
 */
public class NewPatientCodeBehind {

	@FXML
	private Button addNewPatient;

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
	private TextField birthdayTextField;

	@FXML
	private TextField stateInput;

	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField passwordTextField;
	
    @FXML
    private Label invalidPhoneNumberLabel;

    @FXML
    private Label invalidEmailLabel;
    
    @FXML
    private Label usernameExistsLabel;
    
    @FXML
    private Label invalidDateFormat;

	private PatientViewModel viewmodel;

	private Race race;
	private Gender gender;
	private Ethnicity ethnicity;
	private Country country;

	public NewPatientCodeBehind() {
		this.race = new Race();
		this.gender = new Gender();
		this.ethnicity = new Ethnicity();
		this.country = new Country();
		this.viewmodel = new PatientViewModel();
	}

	@FXML
	private void initialize() {
		this.setUpChoiceBoxes();
		this.setListeners();
		this.setInputValidationLabels();
	}
	
	private void setInputValidationLabels() {
		this.invalidEmailLabel.setVisible(false);
		this.invalidPhoneNumberLabel.setVisible(false);
		this.usernameExistsLabel.setVisible(false);
		this.invalidDateFormat.setVisible(false);
	}

	private void setListeners() {
		this.phoneInput.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			if (!InputValidators.validatePhoneNumber(newValue)) {
    				this.invalidPhoneNumberLabel.setVisible(true);
    			}
    			else {
    				this.invalidPhoneNumberLabel.setVisible(false);
    			}
    		}
    	});
		this.emailInput.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			if (!InputValidators.validateEmail(newValue)) {
    				this.invalidEmailLabel.setVisible(true);
    			}
    			else {
    				this.invalidEmailLabel.setVisible(false);
    			}
    		}
    	});
		this.birthdayTextField.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			if (!InputValidators.validateBirthday(newValue)) {
    				this.invalidDateFormat.setVisible(true);
    			}
    			else {
    				this.invalidDateFormat.setVisible(false);
    			}
    		}
    	});
		
	}

	private void setUpChoiceBoxes() {
		this.raceChoiceBox.itemsProperty().set(FXCollections.observableArrayList(this.race.race));
		this.sexChoiceBox.itemsProperty().set(FXCollections.observableArrayList(this.gender.sex));
		this.ethnicityChoiceBox.itemsProperty().set(FXCollections.observableArrayList(this.ethnicity.ethnicity));
		this.countryChoiceBox.itemsProperty().set(FXCollections.observableArrayList(this.country.country));
	}

	@FXML
	void addPatient(ActionEvent event) {
		String username = this.usernameTextField.getText();
		String password = this.passwordTextField.getText();
		String firstname = this.firstNameInput.getText();
		String lastname = this.lastNameInput.getText();
		String birthday = this.birthdayTextField.getText();
		String phone = this.phoneInput.getText();
		String email = this.emailInput.getText();
		String address1 = this.address1Input.getText();
		String address2 = this.address2Input.getText();
		String city = this.cityInput.getText();
		String state = this.stateInput.getText();
		String ethnicity = this.ethnicityChoiceBox.getSelectionModel().getSelectedItem();
		String country = this.countryChoiceBox.getSelectionModel().getSelectedItem();
		String race = this.raceChoiceBox.getSelectionModel().getSelectedItem();
		String sex = this.sexChoiceBox.getSelectionModel().getSelectedItem();
		String insurance = this.insuranceInput.getText();

		Patient patient = new Patient(firstname, lastname, sex, birthday, address1, address2, city, state, country, race,
				ethnicity, phone, email, insurance, username, password);

		if (UserManager.userManager.addPatient(patient)) {
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    	currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
	    	currentStage.close();
		}
	}
}
