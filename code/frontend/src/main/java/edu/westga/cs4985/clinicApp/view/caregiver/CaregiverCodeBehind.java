package edu.westga.cs4985.clinicApp.view.caregiver;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.json.simple.parser.ParseException;

import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.Caregiver;
import edu.westga.cs4985.clinicApp.model.MedicalCondition;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import edu.westga.cs4985.clinicApp.viewmodel.CaregiverViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The Caregiver code behind
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class CaregiverCodeBehind {
	
	private static final String APPOINTMENT_VIEW_POPUP = "../appointment/AppointmentViewPopup.fxml";
	    
	private static final String BOOK_APPOINTMENT_POPUP = "../appointment/BookAppointmentPopup.fxml";
	
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
    
    @FXML
    private Button bookAppointment;

    @FXML
    private ListView<Appointment> futureAppointmentList;

    @FXML
    private ListView<Appointment> pastAppointmentList;

	private CaregiverViewModel viewmodel;

	public CaregiverCodeBehind() {
		this.viewmodel = new CaregiverViewModel();
		this.viewmodel.loadPatients();
	}

	@FXML
	public void initialize() throws IOException, ParseException {
		this.setBindings();
		this.setListeners();
		this.setMedicalConditionsTable();
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
		this.futureAppointmentList.itemsProperty().bindBidirectional(this.viewmodel.futureAppointmentListProperty());
    	this.pastAppointmentList.itemsProperty().bindBidirectional(this.viewmodel.pastAppointmentListProperty());
    	this.viewmodel.selectedFutureAppointmentProperty().bind(this.futureAppointmentList.getSelectionModel().selectedItemProperty());
    	this.viewmodel.selectedPastAppointmentProperty().bind(this.pastAppointmentList.getSelectionModel().selectedItemProperty());
	}

	private void setPatientListViewListener() {
		this.patientListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				try {
					this.loadPatientData();
					List<Appointment> appointments = FXCollections.observableArrayList(UserManager.userManager().getAppointments(this.viewmodel.selectedPatient().getUsername()));
			    	this.viewmodel.filterAppointment(appointments);
					if (this.caregiverLabel.textProperty().get().equals("")) {
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
	
	private void setFutureAppointmentListListener() {
		this.futureAppointmentList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			try {
    				Stage popup = WindowGenerator.openPopup(APPOINTMENT_VIEW_POPUP, new AppointmentViewPopupCodeBehind(this.viewmodel), "Appointment View Window");
    	        	popup.setOnCloseRequest((event) -> {
    	        		this.futureAppointmentList.getSelectionModel().clearSelection();
    	        	});
    	        	
    	        	popup.show();
    	        	
    			} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	});
	}
	
	private void setPastAppointmenteListListener() {
		this.pastAppointmentList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			try {
    				Stage popup = WindowGenerator.openPopup(APPOINTMENT_VIEW_POPUP, new AppointmentViewPopupCodeBehind(this.viewmodel), "Appointment View Window");
    	        	popup.setOnCloseRequest((event) -> {
    	        		this.pastAppointmentList.getSelectionModel().clearSelection();
    	        	});
    	        	
    	        	popup.show();
    			} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	});
	}
	public void setListeners() {
		this.setPastAppointmenteListListener();
		this.setPatientListViewListener();
		this.setFutureAppointmentListListener();
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
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Patient is already under your care");
			alert.show();
		} else {
			this.viewmodel.addPatientToCare();
			this.patientListView.getSelectionModel().clearSelection();
			UserManager.userManager().updateCaregiverPatients(this.viewmodel.getCaregiver(),
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
		this.caregiverLabel.setText(selectedPatient.getCaregiver() == null ? "" : selectedPatient.getCaregiver().toString());
		this.stateInput.setText(selectedPatient.getState());
		this.ethnicityChoiceBox.setValue(selectedPatient.getEthnicity());
		this.countryChoiceBox.setValue(selectedPatient.getCountry());
		this.raceChoiceBox.setValue(selectedPatient.getRace());
		this.sexChoiceBox.setValue(selectedPatient.getGender());
		this.insuranceInput.setText(selectedPatient.getInsurance());
		this.birthdayPicker.setValue(datetime);
		this.setMedicalConditions();
	}
	
	void setMedicalConditions() {
		List<MedicalCondition> medicalConditions;
		try {
			medicalConditions = FXCollections.observableArrayList(
					UserManager.userManager().getMedicalConditions(this.viewmodel.selectedPatient().getUsername()));
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
    	this.viewmodel.selectedPatient().setCaregiver(null);
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
				this.viewmodel.removePatientFromCare();
				this.patientListView.getSelectionModel().clearSelection();
				UserManager.userManager().updateCaregiverPatients(this.viewmodel.getCaregiver(),
						this.viewmodel.getPatients());
				this.updateDisplay();
			}
			if (alert.getResult().getButtonData().equals(ButtonData.NO)) {
				this.patientListView.getSelectionModel().clearSelection();
			}
		});
		alert.showAndWait();
    }
    
    public class AddCaregiverPopupCodeBehind {

        @FXML
        private ListView<Caregiver> caregiverList;
        
        private CaregiverViewModel viewModel;
        
        public AddCaregiverPopupCodeBehind(CaregiverViewModel viewModel) {
        	this.viewModel = viewModel;
        }

        @FXML
        void onAdd(ActionEvent event) {
        	if (this.caregiverList.getSelectionModel().getSelectedItem() == null) {
        		Alert alert = WindowGenerator.openAlert("Please select your caregiver!");
            	
    			alert.showAndWait();
        	} else {
        		CaregiverCodeBehind.this.addCaregiverButton.setVisible(false);
        		CaregiverCodeBehind.this.removeCaregiverButton.setVisible(true);
        		CaregiverCodeBehind.this.caregiverLabel.textProperty().set(this.caregiverList.getSelectionModel().getSelectedItem().toString());
            	this.viewModel.selectedPatient().setCaregiver(this.caregiverList.getSelectionModel().getSelectedItem());
            	UserManager.userManager().updatePatientGeneralInfo(this.viewModel.selectedPatient());
            	this.returnToPreviousStage(event);
        	}
        	
        }
        
        @FXML
        public void initialize() throws ParseException {
        	this.caregiverList.itemsProperty().set(FXCollections.observableArrayList(UserManager.userManager().getAllCaregivers()));
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
    
    @FXML
    void bookAppointment(ActionEvent event) throws IOException {
    	Stage popup = WindowGenerator.openPopup(BOOK_APPOINTMENT_POPUP, new BookAppointmentPopupCodeBehind(this.viewmodel), "Booking Appointment Window");
    	popup.show();
    }

    public class BookAppointmentPopupCodeBehind {
    	
    	@FXML
    	private TextField zipcodeInput;

        @FXML
        private ListView<MedicalPersonnel> medicalPersonList;

        @FXML
        private ListView<LocalDateTime> availableTimeList;

        @FXML
        private TextArea noteTextBox;
        
        private CaregiverViewModel viewModel;
        
        public BookAppointmentPopupCodeBehind(CaregiverViewModel viewModel) {
        	this.viewModel = viewModel;
        }
        
        @FXML
        public void initialize() {
        	this.setBindings();
        	
        	this.medicalPersonList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        		if (newValue != null) {
        			List<LocalDateTime> list;
					try {
						list = UserManager.userManager().getAvailabilities(newValue.getUsername());
						if (list.size() == 0) {
							Alert alert = WindowGenerator.openAlert("The Medical Personnel not available now. Please choose another one!");
			            	
			    			alert.showAndWait();
	            		} else {
	            			this.availableTimeList.itemsProperty().set(FXCollections.observableArrayList(list));
	            		}
					} catch (ParseException e) {
						e.printStackTrace();
					}
            		
        			
        		}
        	});
        }
        
        public void setBindings() {
        	this.viewModel.seletedMedicalPersonnel().bind(this.medicalPersonList.getSelectionModel().selectedItemProperty());
        	this.viewModel.selectedAvailabilityProperty().bind(this.availableTimeList.getSelectionModel().selectedItemProperty());
        	
        }

        @FXML
        void bookTheAppointment(ActionEvent event) throws IOException {
        	if (this.viewModel.seletedMedicalPersonnel().get() == null) {
        		Alert alert = WindowGenerator.openAlert("Please select your medical personnel!");
            	
    			alert.showAndWait();
        	} else if (this.viewModel.selectedAvailabilityProperty().get() == null) {
        		Alert alert = WindowGenerator.openAlert("Please select your time!");
            	
    			alert.showAndWait();
        	} else {
        		this.booKAppointmentHelper(event);
        	}
        	
        }
        
        private void booKAppointmentHelper(ActionEvent event) {
        	Alert bookAlert = WindowGenerator.openConfirm("Are you sure want to book this appointment?");
        	bookAlert.setOnCloseRequest((action) -> {
        		if (bookAlert.getResult().getButtonData().equals(ButtonData.YES)) {
        			this.viewModel.notesProperty().set(this.noteTextBox.getText());
        			Appointment appointment = this.viewModel.bookAppointment();
        			UserManager.userManager().bookAppointment(appointment);
        			try {
						List<LocalDateTime> availabilityList = UserManager.userManager().getAvailabilities(this.viewModel.seletedMedicalPersonnel().getValue().getUsername());
						availabilityList.remove(appointment.getDateTime());
						UserManager.userManager().updateMedicalPersonnelAvaiabilities(this.viewModel.seletedMedicalPersonnel().get(), availabilityList);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
        			Stage popup;
					try {
						popup = WindowGenerator.openPopup(APPOINTMENT_VIEW_POPUP, new AppointmentViewPopupCodeBehind(this.viewModel), "Appointment View Window");
						popup.show();
					} catch (IOException e) {
						e.printStackTrace();
					}
					Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
                    currentStage.close();
        		}
        	});
    		bookAlert.show();
        }

        @FXML
        void cancel(ActionEvent event) {
        	Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
        	currentStage.close();
        }

        @FXML
        void getCityName(ActionEvent event) throws ParseException {
        	
        	String zipcode = this.zipcodeInput.getText();
        	try {
        		int zip = Integer.parseInt(zipcode);
        		if (zip > 99999 || zip < 10000) {
        			Alert alert = WindowGenerator.openAlert("Please entery FIVE digits for zipcode!");
        			
        			alert.showAndWait();
        			return;
        		}
        		List<MedicalPersonnel> list = UserManager.userManager().getAllMedicalPersonnels(zipcode);
        		if (list.size() == 0) {
        			Alert alert = WindowGenerator.openAlert("There are no any Medical Personnel in this area. Please entry another zipcode!");
                	
        			alert.showAndWait();
        			return;
        		} else {
        			this.medicalPersonList.itemsProperty().set(FXCollections.observableArrayList(list));
        		}
        		
        	} catch (NumberFormatException e) {
        		Alert alert = WindowGenerator.openAlert("Please entery numbers for zipcode!");
        	
        		alert.showAndWait();
        		return;
        	}
        }

    }

    public class AppointmentViewPopupCodeBehind {

        @FXML
        private TextArea appointmentNotes;
        
        @FXML
        private Button editButton;

        @FXML
        private Button oKButton;

        @FXML
        private Button saveButton;
        
        @FXML
        private Button cancelAppointmentButton;
        
        @FXML
        private Label medicalPersonnelLabel;

        @FXML
        private Label patientLabel;

        @FXML
        private Label timeLabel;

        @FXML
        private Label locationLabel;
        
        private CaregiverViewModel viewModel;
        
        public AppointmentViewPopupCodeBehind(CaregiverViewModel viewModel) {
        	this.viewModel = viewModel;
        }
        
        @FXML
        public void initialize() {
        	this.editButton.setVisible(false);
        	this.saveButton.setVisible(false);
        	this.appointmentNotes.setEditable(false);
        	if (this.viewModel.selectedFutureAppointmentProperty().get() != null) {
        		this.whenSelectFuture();
        	} else if (this.viewModel.selectedPastAppointmentProperty().get() != null) {
        		this.whenSelectPast();
        	} else {
        		this.cancelAppointmentButton.setVisible(false);
        		this.medicalPersonnelLabel.textProperty().set("Medical Personnel: " + this.viewModel.seletedMedicalPersonnel().get());
        		this.patientLabel.textProperty().set("Patient: " + this.viewModel.selectedPatient().getFullName());
            	this.timeLabel.textProperty().set("Time: " + this.viewModel.selectedAvailabilityProperty().get());
            	this.locationLabel.textProperty().set("Location: " + this.viewModel.seletedMedicalPersonnel().get().getFullAddress());
            	this.appointmentNotes.textProperty().set(this.viewModel.notesProperty().get());
        	}
        }
        
        private void whenSelectFuture() {
        	this.medicalPersonnelLabel.textProperty().set("Medical Personnel: " + this.viewModel.selectedFutureAppointmentProperty().get().getMedicalPersonnel());
        	this.timeLabel.textProperty().set("Time: " + this.viewModel.selectedFutureAppointmentProperty().get().getDateTime());
        	this.appointmentNotes.textProperty().set(this.viewModel.selectedFutureAppointmentProperty().get().getNotes());
        	this.patientLabel.textProperty().set("Patient: " + this.viewModel.selectedFutureAppointmentProperty().get().getPatient().getFullName());
        	this.locationLabel.textProperty().set("Location: " + this.viewModel.selectedFutureAppointmentProperty().get().getLocation());
    		this.cancelAppointmentButton.setVisible(true);
        }
        
        private void whenSelectPast() {
        	this.cancelAppointmentButton.setVisible(false);
    		this.medicalPersonnelLabel.textProperty().set("Medical Personnel: " + this.viewModel.selectedPastAppointmentProperty().get().getMedicalPersonnel());
        	this.timeLabel.textProperty().set("Time: " + this.viewModel.selectedPastAppointmentProperty().get().getDateTime());
        	this.appointmentNotes.textProperty().set(this.viewModel.selectedPastAppointmentProperty().get().getNotes());
        	this.patientLabel.textProperty().set("Patient: " + this.viewModel.selectedPastAppointmentProperty().get().getPatient().getFullName());
        	this.locationLabel.textProperty().set("Location: " + this.viewModel.selectedPastAppointmentProperty().get().getLocation());
        }

        @FXML
        void cancelAppointment(ActionEvent event) {
        	Alert alert = WindowGenerator.openConfirm("Are you sure you want to cancel this appointment?");
        	
			alert.setOnCloseRequest((evt) -> {
				if (alert.getResult().getButtonData().equals(ButtonData.YES)) {
					Appointment appointment =  this.viewModel.cancelAppointment();
					UserManager.userManager().cancelAppointment(appointment);
					try {
						List<LocalDateTime> availabilityList = UserManager.userManager().getAvailabilities(appointment.getMedicalPersonnel().getUsername());
						availabilityList.add(appointment.getDateTime());
						UserManager.userManager().updateMedicalPersonnelAvaiabilities(appointment.getMedicalPersonnel(), availabilityList);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
					currentStage.close();
				}
			});
			alert.showAndWait();
        }

        @FXML
        void onOKButton(ActionEvent event) {
        	Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
        	currentStage.close();

        }
        
        @FXML
        void onEditNotes(ActionEvent event) {
        	//No need for Caregiver
        }

        @FXML
        void saveNotes(ActionEvent event) {
        	//No need for Caregiver
        }
    }


}
