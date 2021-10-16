package edu.westga.cs4985.clinicApp.view.login;

import org.json.simple.parser.ParseException;

import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.resources.InputValidators;
import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class NewMedicalPersonnelCodeBehind {

	@FXML
    private Button addMedicalPersonnel;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private ChoiceBox<String> sexChoiceBox;

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
    private TextField stateInput;

    @FXML
    private ChoiceBox<String> countryChoiceBox;

    @FXML
    private ChoiceBox<String> raceChoiceBox;

    @FXML
    private ChoiceBox<String> ethnicityChoiceBox;

    @FXML
    private TextField birthdayTextField;

    @FXML
    private Label invalidEmailLabel;

    @FXML
    private Label invalidDateFormat;

    @FXML
    private TextField zipcodeInput;

    @FXML
    private Label invalidFirstName;

    @FXML
    private Label invalidState;

    @FXML
    private Label invalidLastName;

    @FXML
    private Label invalidCity;

    @FXML
    private Label invalidPhoneNumberLabel;

    @FXML
    private Label invalidCountry;

    @FXML
    private Label invalidSex;

    @FXML
    private Label invalidRace;

    @FXML
    private Label invaildEthnicity;

    @FXML
    private Label invalidZipcode;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label usernameExistsLabel;

    @FXML
    private Label invalidAddress1;

    @FXML
    private Label invalidPassword;

	private Race race;
	private Gender gender;
	private Ethnicity ethnicity;
	private Country country;

	public NewMedicalPersonnelCodeBehind() {
		this.race = new Race();
		this.gender = new Gender();
		this.ethnicity = new Ethnicity();
		this.country = new Country();
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
		
		this.invalidFirstName.setVisible(false);
		this.invalidLastName.setVisible(false);
		this.invalidSex.setVisible(false);
		this.invaildEthnicity.setVisible(false);
		this.invalidRace.setVisible(false);
		this.invalidAddress1.setVisible(false);
		this.invalidCity.setVisible(false);
		this.invalidState.setVisible(false);
		this.invalidCountry.setVisible(false);
		this.invalidPassword.setVisible(false);
		this.invalidZipcode.setVisible(false);
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
		this.firstNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			this.invalidFirstName.setVisible(false);
    		}
    	});
		this.lastNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			this.invalidLastName.setVisible(false);
    		}
    	});
		this.address1Input.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			this.invalidAddress1.setVisible(false);
    		}
    	});
		
		this.cityInput.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			this.invalidCity.setVisible(false);
    		}
    	});
		this.stateInput.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			this.invalidState.setVisible(false);
    		}
    	});
		
		this.usernameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			this.usernameExistsLabel.setVisible(false);
    		}
    	});
		this.passwordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			this.invalidPassword.setVisible(false);
    		}
    	});
		this.countryChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			this.invalidCountry.setVisible(false);
    		}
    	});
		this.raceChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			this.invalidRace.setVisible(false);
    		}
    	});
		this.ethnicityChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			this.invaildEthnicity.setVisible(false);
    		}
    	});
		this.sexChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			this.invalidSex.setVisible(false);
    		}
    	});
		this.zipcodeInput.textProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			this.invalidZipcode.setVisible(false);
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
	void addMedicalPersonnel(ActionEvent event) throws ParseException {
		if (UserManager.userManager.getUserByUserName(this.usernameTextField.getText()) != null) {
			this.usernameExistsLabel.setText("UserName already Exist!");
			this.usernameExistsLabel.setVisible(true);
		}
		else {
			boolean valid = true;
			if (this.firstNameInput.getText() == null || this.firstNameInput.getText() == "") {
	    		this.invalidFirstName.setVisible(true);
	    		valid = false;
	    	} 
			if (this.lastNameInput.getText() == null || this.lastNameInput.getText() == "") {
	    		this.invalidLastName.setVisible(true);
	    		valid = false;
	    	} 
			if (this.birthdayTextField.getText() == null || this.birthdayTextField.getText().toString() == "") {
	    		this.invalidDateFormat.setVisible(true);
	    		valid = false;
	    	} 
			if (this.phoneInput.getText() == null || this.phoneInput.getText() == "") {
	    		this.invalidPhoneNumberLabel.setVisible(true);
	    		valid = false;
	    	} 
			if (this.emailInput.getText() == null || this.emailInput.getText() == "") {
	    		this.invalidEmailLabel.setVisible(true);
	    		valid = false;
	    	} 
			if (this.address1Input.getText() == null || this.address1Input.getText() == "") {
	    		this.invalidAddress1.setVisible(true);
	    		valid = false;
	    	} 
			if (this.usernameTextField.getText() == null || this.usernameTextField.getText() == "") {
	    		this.usernameExistsLabel.setText("Invalid User Name!");
				this.usernameExistsLabel.setVisible(true);
				valid = false;
	    	}  
			if (this.passwordTextField.getText() == null || this.passwordTextField.getText() == "") {
	    		this.invalidPassword.setVisible(true);
	    		valid = false;
	    	}
	    	if (this.cityInput.getText() == null || this.cityInput.getText() == "") {
	    		this.invalidCity.setVisible(true);
	    		valid = false;
	    	} 
	    	if (this.stateInput.getText() == null || this.stateInput.getText() == "") {
	    		this.invalidState.setVisible(true);
	    		valid = false;
	    	} 
	    	if (this.zipcodeInput.getText() == null || this.zipcodeInput.getText() == "") {
	    		this.invalidZipcode.setVisible(true);
	    		valid = false;
	    	} 
	    	if (this.ethnicityChoiceBox.getSelectionModel().getSelectedItem() == null) {
	    		this.invaildEthnicity.setVisible(true);
	    		valid = false;
	    	} 
	    	if (this.countryChoiceBox.getSelectionModel().getSelectedItem() == null) {
	    		this.invalidCountry.setVisible(true);
	    		valid = false;
	    	} 
	    	if (this.raceChoiceBox.getSelectionModel().getSelectedItem() == null) {
	    		this.invalidRace.setVisible(true);
	    		valid = false;
	    	} 
	    	if (this.sexChoiceBox.getSelectionModel().getSelectedItem() == null) {
	    		this.invalidSex.setVisible(true);
	    		valid = false;
	    	} 
	    	if (valid) {
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
				String zip = this.zipcodeInput.getText();

				MedicalPersonnel medicalPersonnel = new MedicalPersonnel(firstname, lastname, sex, birthday, address1, address2, city, state, country, race,
						ethnicity, phone, email, username, password, zip);

				if (UserManager.userManager.addMedicalPersonnel(medicalPersonnel)) {
					Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			    	currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
			    	currentStage.close();
				}
	    	}
			
		}
		
	}

}
