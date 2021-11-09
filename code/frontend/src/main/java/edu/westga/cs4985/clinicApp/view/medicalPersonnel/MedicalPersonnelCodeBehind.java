package edu.westga.cs4985.clinicApp.view.medicalPersonnel;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.json.simple.parser.ParseException;
import edu.westga.cs4985.clinicApp.model.MedicalCondition;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import edu.westga.cs4985.clinicApp.view.generalInfor.PatientGeneralInfoCodeBehind.AddCaregiverPopupCodeBehind;
import edu.westga.cs4985.clinicApp.viewmodel.MedicalPersonnelViewModel;
import edu.westga.cs4985.clinicApp.viewmodel.PatientViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The Class MedicalPersonnelCodeBehind.
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 * 
 */
public class MedicalPersonnelCodeBehind {
	
	@FXML
	private Button addCaregiverButton;

	@FXML
	private Button removeCaregiverButton;

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
	private TableView<MedicalCondition> medicalConditionTableView;

	@FXML
	private TableColumn<MedicalCondition, String> conditionNameColumn;

	@FXML
	private TableColumn<MedicalCondition, String> diagnosedDateColumn;

	@FXML
	private TableColumn<MedicalCondition, String> terminationDateColumn;

	@FXML
	private TableColumn<MedicalCondition, String> notesColumn;

	@FXML
	private TabPane patientTabPane;

	@FXML
	private Tab appointmentTab;

	@FXML
	private Tab generalInfoTab;

	@FXML
	private AnchorPane appointmentPane;

	@FXML
	private Button addPatientButton;

	@FXML
	private RadioButton showAllPatientsRadioButton;

	@FXML
	private Button logoutButton;
	
    @FXML
    private Button removePatientButton;

	private MedicalPersonnelViewModel viewmodel;

	public MedicalPersonnelCodeBehind() {
		this.viewmodel = new MedicalPersonnelViewModel();
		this.viewmodel.loadPatients();
	}

	@FXML
	public void initialize() throws IOException {
		this.setBindings();
		this.setListeners();
		this.setMedicalConditionsTable();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../appointment/MedicalPersonnelAppointmentGui.fxml"));
		this.appointmentPane.getChildren().setAll(pane);
		this.addCaregiverButton.setVisible(false);
		this.removeCaregiverButton.setVisible(false);
		this.removePatientButton.setVisible(true);
		this.addPatientButton.setVisible(false);
	}

	private void setMedicalConditionsTable() {
		this.conditionNameColumn.setCellValueFactory(new PropertyValueFactory<MedicalCondition, String>("name"));
		this.diagnosedDateColumn
				.setCellValueFactory(new PropertyValueFactory<MedicalCondition, String>("diagnosisDate"));
		this.terminationDateColumn
				.setCellValueFactory(new PropertyValueFactory<MedicalCondition, String>("terminationDate"));
		this.notesColumn.setCellValueFactory(new PropertyValueFactory<MedicalCondition, String>("notes"));
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
							if (this.caregiverLabel.textProperty().get() == "") {
					    		this.addCaregiverButton.setVisible(true);
					    		this.removeCaregiverButton.setVisible(false);
					    	} else {
					    		this.removeCaregiverButton.setVisible(true);
					    		this.addCaregiverButton.setVisible(false);
					    	}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

		this.showAllPatientsRadioButton.selectedProperty().addListener((observable, oldType, newType) -> {
			this.updateDisplay();
		});
	}

	private void updateDisplay() {
		if (this.showAllPatientsRadioButton.isSelected()) {
			this.removePatientButton.setVisible(false);
			this.addPatientButton.setVisible(true);
			this.viewmodel.loadAllPatients();
		} else {
			this.viewmodel.loadPatients();
			this.removePatientButton.setVisible(true);
			this.addPatientButton.setVisible(false);
		}

	}

	@FXML
	void handleAddPatient(ActionEvent event) {
		if (this.viewmodel.checkPatientUnderCare()) {
			Alert a = new Alert(AlertType.WARNING);
			a.setContentText("Patient is already under your care");
			a.show();
		} else {
			this.viewmodel.addPatientToCare();
			this.patientListView.getSelectionModel().clearSelection();
			UserManager.userManager.updateMedicalPersonnelsPatients(this.viewmodel.getMedicalePersonnel(),
					this.viewmodel.getPatients());
			this.updateDisplay();
		}
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
		
		this.setMedicalConditions();
	}
	
	void setMedicalConditions() {
		List<MedicalCondition> medicalConditions;
		try {
			medicalConditions = FXCollections.observableArrayList(
					UserManager.userManager.getMedicalConditions(this.viewmodel.selectedPatient().getUsername()));
			this.medicalConditionTableView.itemsProperty().set((ObservableList<MedicalCondition>) medicalConditions);
		} catch (ParseException e) {
		
			e.printStackTrace();
		}
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
	
	@FXML
    void addCaregiver(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../generalInfor/AddCaregiverPopup.fxml"));
    	loader.setController(new AddCaregiverPopupCodeBehind(this.viewmodel));
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
    	this.viewmodel.selectedPatient().setCaregiver("");
    	this.caregiverLabel.textProperty().set("");
    	this.addCaregiverButton.setVisible(true);
		this.removeCaregiverButton.setVisible(false);
    }
    
    @FXML
    void handleRemovePatient(ActionEvent event) {
    	Alert alert = WindowGenerator
				.openConfirm("Are you sure want to remove the patient from your care?");
		alert.setOnCloseRequest((evt) -> {

			if (alert.getResult().getButtonData().equals(ButtonData.YES)) {
				viewmodel.removePatientFromCare();
				patientListView.getSelectionModel().clearSelection();
				UserManager.userManager.updateMedicalPersonnelsPatients(viewmodel.getMedicalePersonnel(),
						viewmodel.getPatients());
				updateDisplay();
			}
			if (alert.getResult().getButtonData().equals(ButtonData.NO)) {
				patientListView.getSelectionModel().clearSelection();
			}
		});
		alert.showAndWait();
    }
    
    public class AddCaregiverPopupCodeBehind {

        @FXML
        private ListView<String> caregiverList;
        
        private MedicalPersonnelViewModel viewModel;
        
        public AddCaregiverPopupCodeBehind(MedicalPersonnelViewModel viewModel) {
        	this.viewModel = viewModel;
        }

        @FXML
        void onAdd(ActionEvent event) {
        	if(this.caregiverList.getSelectionModel().getSelectedItem() == null) {
        		Alert alert = WindowGenerator.openAlert("Please select your caregiver!");
            	
    			alert.showAndWait();
        	} else {
            	addCaregiverButton.setVisible(false);
            	removeCaregiverButton.setVisible(true);
            	caregiverLabel.textProperty().set(this.caregiverList.getSelectionModel().getSelectedItem());
            	this.viewModel.selectedPatient().setCaregiver(this.caregiverList.getSelectionModel().getSelectedItem());
            	UserManager.userManager.updatePatientGeneralInfo(this.viewModel.selectedPatient());
            	this.returnToPreviousStage(event);
        	}
        	
        }
        
        @FXML
        public void initialize() {
        	this.caregiverList.getItems().add("Caregiver A");
        	this.caregiverList.getItems().add("Caregiver B");
        	this.caregiverList.getItems().add("Caregiver C");
        }

        @FXML
        void onCancel(ActionEvent event) {
        	this.returnToPreviousStage(event);
        }
        
        private void returnToPreviousStage(ActionEvent event) {
        	Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
        	currentStage.close();
        }

    }

}
