package edu.westga.cs4985.clinicApp.view.login;

import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;
import edu.westga.cs4985.clinicApp.viewmodel.PatientViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
	private DatePicker birthdayPicker;

	@FXML
	private TextField stateInput;

	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField passwordTextField;

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
		String birthday = this.birthdayPicker.getValue().toString();
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

		UserManager.userManager.addPatient(patient);
	}
}
