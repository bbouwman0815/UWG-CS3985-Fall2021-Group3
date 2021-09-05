package edu.westga.cs4985.clinicApp.view.generalInfor;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


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
    
    private Race race;
    private Gender gender;
    private Ethnicity ethnicity;
    private Country country;
    
    private PatientViewModel viewModel;

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
    	this.viewModel = new PatientViewModel();
    }
    
    @FXML
    public void initialize() {
    	this.saveButton.setVisible(false);
    	this.cancelButton.setVisible(false);
    	this.setUpChoiceBoxes();
    	this.setupBindings();
    	this.formActivation(true);
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

}

