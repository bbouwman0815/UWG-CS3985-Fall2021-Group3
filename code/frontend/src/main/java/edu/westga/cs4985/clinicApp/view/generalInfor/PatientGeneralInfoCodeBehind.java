package edu.westga.cs4985.clinicApp.view.generalInfor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;
import edu.westga.cs4985.clinicApp.utils.login.UToken;
import edu.westga.cs4985.clinicApp.view.appointment.AppointmentCodeBehind.AppointmentViewPopupCodeBehind;
import edu.westga.cs4985.clinicApp.view.appointment.AppointmentCodeBehind.BookAppointmentPopupCodeBehind;
import edu.westga.cs4985.clinicApp.viewmodel.PatientAppointmentViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * The code behind for the patient general information view.
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class PatientGeneralInfoCodeBehind {
	
    @FXML
    private Label patientNameLabel;

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
    private Button removeCaregiverButton;
    
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
	
    @FXML
    private Label caregiverLabel;
    
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
    	this.setForm(this.viewModel.getPatient());
    	if (this.caregiverLabel.textProperty().get() == "") {
    		this.addCaregiverButton.setVisible(true);
    		this.addCaregiverButton.setDisable(true);
    		this.removeCaregiverButton.setVisible(false);
    		this.removeCaregiverButton.setDisable(false);
    	} else {
    		this.removeCaregiverButton.setVisible(true);
    		this.removeCaregiverButton.setDisable(true);
    		this.addCaregiverButton.setVisible(false);
    		this.addCaregiverButton.setDisable(false);
    	}
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
    void addCaregiver(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../generalInfor/AddCaregiverPopup.fxml"));
    	loader.setController(new AddCaregiverPopupCodeBehind(this.viewModel));
    	Pane pane = (Pane) loader.load();
    	Stage popup = new Stage();
    	Scene scene = new Scene(pane);
    	popup.setScene(scene);
    	popup.setResizable(false);
    	popup.setTitle("Add Caregiver Window");
    	popup.initModality(Modality.APPLICATION_MODAL);
    	popup.show();
    }
    
    @FXML
    void removerCaregiver(ActionEvent event) {
    	this.viewModel.getPatient().setCaregiver("");
    	this.caregiverLabel.textProperty().set("");
    	this.addCaregiverButton.setVisible(true);
		this.removeCaregiverButton.setVisible(false);
    }

    @FXML
    void cancel(ActionEvent event) {
    	this.editbutton.setVisible(true);
    	this.saveButton.setVisible(false);
    	this.cancelButton.setVisible(false);
    	this.formActivation(true);
    	this.setForm((Patient) UserManager.userManager.getUserByUserName(this.viewModel.getPatient().getUsername()));
    }
    
    private void setForm(Patient patient) {
    	DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			LocalDate datetime = LocalDate.parse(patient.getDateOfBirth(), pattern);
			this.firstNameInput.setText(patient.getFirstName());
			this.lastNameInput.setText(patient.getLastName());
			this.descriptionInput.setText("Sample");
			this.birthdayPicker.setValue(datetime);
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
			this.caregiverLabel.setText(patient.getCaregiver());
		} catch (IllegalArgumentException e) {

		}
	}
    
    private void setPatientInfo() {
    	this.viewModel.getPatient().setFirstName(this.firstNameInput.getText());
    	this.viewModel.getPatient().setLastName(this.lastNameInput.getText());
    	this.viewModel.getPatient().setDateOfBirth(this.birthdayPicker.getValue().toString());
    	this.viewModel.getPatient().setPhoneNumber(this.phoneInput.getText());
    	this.viewModel.getPatient().setEmail(this.emailInput.getText());
    	this.viewModel.getPatient().setAddress1(this.address1Input.getText());
    	this.viewModel.getPatient().setAddress2(this.address2Input.getText());
    	this.viewModel.getPatient().setCity(this.cityInput.getText());
    	this.viewModel.getPatient().setState(this.stateInput.getText());
    	this.viewModel.getPatient().setEthnicity(this.ethnicityChoiceBox.getSelectionModel().getSelectedItem());
    	this.viewModel.getPatient().setCountry(this.countryChoiceBox.getSelectionModel().getSelectedItem());
    	this.viewModel.getPatient().setRace(this.raceChoiceBox.getSelectionModel().getSelectedItem());
    	this.viewModel.getPatient().setGender(this.sexChoiceBox.getSelectionModel().getSelectedItem());
    	this.viewModel.getPatient().setInsurance(this.insuranceInput.getText());
    	this.viewModel.getPatient().setCaregiver(this.caregiverLabel.getText());
    }
    
    private Patient samplePatient() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patientDummy = new Patient("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
				"770-111-222", "email@email.com", "United Healthcare", "New", "New");
		return patientDummy;
	}

    @FXML
    void editGeneralInfo(ActionEvent event) {
    	this.editbutton.setVisible(false);
    	this.saveButton.setVisible(true);
    	this.cancelButton.setVisible(true);
    	this.formActivation(false);
    	if (this.caregiverLabel.textProperty().get() == "") {
    		this.addCaregiverButton.setDisable(false);
    	} else {
    		this.removeCaregiverButton.setDisable(false);
    	}

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
    	if (this.caregiverLabel.textProperty().get() == "") {
    		this.addCaregiverButton.setDisable(true);
    	} else {
    		this.removeCaregiverButton.setDisable(true);
    	}
    	this.setPatientInfo();
    	UserManager.userManager.updatePatientGeneralInfo(this.viewModel.getPatient());
    }

    @FXML
    void onCancel(ActionEvent event) {
    	
    }

    public class AddCaregiverPopupCodeBehind {

        @FXML
        private ListView<String> caregiverList;
        
        private PatientAppointmentViewModel viewModel;

        @FXML
        void onAdd(ActionEvent event) {
        	if(this.caregiverList.getSelectionModel().getSelectedItem() == null) {
        		Alert alert = new Alert(AlertType.CONFIRMATION, "Please select your caregiver!", ButtonType.OK);
            	
    			alert.showAndWait();
        	} else {
            	addCaregiverButton.setVisible(false);
            	removeCaregiverButton.setVisible(true);
            	caregiverLabel.textProperty().set(this.caregiverList.getSelectionModel().getSelectedItem());
            	Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            	currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
            	currentStage.close();
        	}
        	
        }
        
        public AddCaregiverPopupCodeBehind(PatientAppointmentViewModel viewModel) {
        	this.viewModel = viewModel;
        }
        
        @FXML
        public void initialize() {
        	this.caregiverList.getItems().add("Caregiver A");
        	this.caregiverList.getItems().add("Caregiver B");
        	this.caregiverList.getItems().add("Caregiver C");
        }

        @FXML
        void onCancel(ActionEvent event) {
        	Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
        	currentStage.close();
        }

    }
	

}

