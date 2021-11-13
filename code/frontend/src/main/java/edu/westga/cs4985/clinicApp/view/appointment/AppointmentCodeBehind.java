package edu.westga.cs4985.clinicApp.view.appointment;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.json.simple.parser.ParseException;

import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import edu.westga.cs4985.clinicApp.viewmodel.PatientViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The code behind for appointment view.
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class AppointmentCodeBehind {
	
	@FXML
    private Button bookAppointment;

    @FXML
    private ListView<Appointment> futureAppointmentList;

    @FXML
    private ListView<Appointment> pastAppointmentList;
    
    private PatientViewModel viewModel;
    
    private static final String APPOINTMENT_VIEW_POPUP = "../appointment/AppointmentViewPopup.fxml";
    
    private static final String BOOK_APPOINTMENT_POPUP = "../appointment/BookAppointmentPopup.fxml";
    
    public AppointmentCodeBehind() {
    	this.viewModel = new PatientViewModel();
    }
    
    @FXML
    public void initialize() throws ParseException {
    	this.setBindings();
    	this.setListeners();
    	List<Appointment> appointments = FXCollections.observableArrayList(UserManager.userManager.getAppointments(this.viewModel.getPatient().getUsername()));
    	this.viewModel.filterAppointment(appointments);
    }
    
    public void setBindings() {
    	this.futureAppointmentList.itemsProperty().bindBidirectional(this.viewModel.futureAppointmentListProperty());
    	this.pastAppointmentList.itemsProperty().bindBidirectional(this.viewModel.pastAppointmentListProperty());
    	this.viewModel.selectedFutureAppointmentProperty().bind(this.futureAppointmentList.getSelectionModel().selectedItemProperty());
    	this.viewModel.selectedPastAppointmentProperty().bind(this.pastAppointmentList.getSelectionModel().selectedItemProperty());
    	
    }
    
    public void setListeners() {
    	this.futureAppointmentList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			try {
    				Stage popup = WindowGenerator.openPopup(APPOINTMENT_VIEW_POPUP, new AppointmentViewPopupCodeBehind(this.viewModel), "Appointment View Window");
    	        	popup.setOnCloseRequest((event) -> {
    	        		this.futureAppointmentList.getSelectionModel().clearSelection();
    	        	});
    	        	
    	        	popup.show();
    	        	
    			}
    			catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	});
    	
    	this.pastAppointmentList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			try {
    				Stage popup = WindowGenerator.openPopup(APPOINTMENT_VIEW_POPUP, new AppointmentViewPopupCodeBehind(this.viewModel), "Appointment View Window");
    	        	popup.setOnCloseRequest((event) -> {
    	        		this.pastAppointmentList.getSelectionModel().clearSelection();
    	        	});
    	        	
    	        	popup.show();
    	        	
    	        	
    			}
    			catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	});
    }

    @FXML
    void bookAppointment(ActionEvent event) throws IOException {
    	Stage popup = WindowGenerator.openPopup(BOOK_APPOINTMENT_POPUP, new BookAppointmentPopupCodeBehind(this.viewModel), "Booking Appointment Window");
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
        
        private PatientViewModel viewModel;
        
        public BookAppointmentPopupCodeBehind(PatientViewModel viewModel) {
        	this.viewModel = viewModel;
        }
        
        @FXML
        public void initialize(){
        	this.setBindings();
        	
        	this.medicalPersonList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        		if (newValue != null) {
        			List<LocalDateTime> list;
					try {
						list = UserManager.userManager.getAvailabilities(newValue.getUsername());
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
        		Alert bookAlert = WindowGenerator.openConfirm("Are you sure want to book this appointment?");
            	bookAlert.setOnCloseRequest((action) -> {
            		if (bookAlert.getResult().getButtonData().equals(ButtonData.YES)) {
            			this.viewModel.notesProperty().set(this.noteTextBox.getText());
            			Appointment appointment = this.viewModel.bookAppointment();
            			UserManager.userManager.bookAppointment(appointment);
            			
            			try {
							List<LocalDateTime> availabilityList = UserManager.userManager.getAvailabilities(this.viewModel.seletedMedicalPersonnel().getValue().getUsername());
							availabilityList.remove(appointment.getDateTime());
							UserManager.userManager.updateMedicalPersonnelAvaiabilities(this.viewModel.seletedMedicalPersonnel().get(), availabilityList);
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
        		List<MedicalPersonnel> list = UserManager.userManager.getAllMedicalPersonnels(zipcode);
        		if (list.size() == 0) {
        			Alert alert = WindowGenerator.openAlert("There are no any Medical Personnel in this area. Please entry another zipcode!");
                	
        			alert.showAndWait();
        			return;
        		} else {
        			this.medicalPersonList.itemsProperty().set(FXCollections.observableArrayList(list));
        		}
        		
        	} catch(NumberFormatException e) {
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
        private Button OKButton;

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
        
        private PatientViewModel viewModel;
        
        public AppointmentViewPopupCodeBehind(PatientViewModel viewModel) {
        	this.viewModel = viewModel;
        }
        
        @FXML
        public void initialize() {
        	this.editButton.setVisible(false);
        	this.saveButton.setVisible(false);
        	this.appointmentNotes.setEditable(false);
        	if (this.viewModel.selectedFutureAppointmentProperty().get() != null) {
        		this.medicalPersonnelLabel.textProperty().set("Medical Personnel: " + this.viewModel.selectedFutureAppointmentProperty().get().getMedicalPersonnel());
            	this.timeLabel.textProperty().set("Time: " + this.viewModel.selectedFutureAppointmentProperty().get().getDateTime());
            	this.appointmentNotes.textProperty().set(this.viewModel.selectedFutureAppointmentProperty().get().getNotes());
            	this.patientLabel.textProperty().set("Patient: " + this.viewModel.selectedFutureAppointmentProperty().get().getPatient().getFullName());
            	this.locationLabel.textProperty().set("Location: " + this.viewModel.selectedFutureAppointmentProperty().get().getLocation());
        		this.cancelAppointmentButton.setVisible(true);
        	} else if (this.viewModel.selectedPastAppointmentProperty().get() != null) {
        		this.cancelAppointmentButton.setVisible(false);
        		this.medicalPersonnelLabel.textProperty().set("Medical Personnel: " + this.viewModel.selectedPastAppointmentProperty().get().getMedicalPersonnel());
            	this.timeLabel.textProperty().set("Time: " + this.viewModel.selectedPastAppointmentProperty().get().getDateTime());
            	this.appointmentNotes.textProperty().set(this.viewModel.selectedPastAppointmentProperty().get().getNotes());
            	this.patientLabel.textProperty().set("Patient: " + this.viewModel.selectedPastAppointmentProperty().get().getPatient().getFullName());
            	this.locationLabel.textProperty().set("Location: " + this.viewModel.selectedPastAppointmentProperty().get().getLocation());
        	} else {
        		this.cancelAppointmentButton.setVisible(false);
        		this.medicalPersonnelLabel.textProperty().set("Medical Personnel: " + this.viewModel.seletedMedicalPersonnel().get());
        		this.patientLabel.textProperty().set("Patient: " + this.viewModel.getPatient().getFullName());
            	this.timeLabel.textProperty().set("Time: " + this.viewModel.selectedAvailabilityProperty().get());
            	this.locationLabel.textProperty().set("Location: " + this.viewModel.seletedMedicalPersonnel().get().getFullAddress());
            	this.appointmentNotes.textProperty().set(this.viewModel.notesProperty().get());
        		
        	}
        	
        }

        @FXML
        void cancelAppointment(ActionEvent event) {
        	Alert alert = WindowGenerator.openConfirm("Are you sure you want to cancel this appointment?");
        	
			alert.setOnCloseRequest((evt) -> {
				if (alert.getResult().getButtonData().equals(ButtonData.YES)) {
					Appointment appointment =  this.viewModel.cancelAppointment();
					UserManager.userManager.cancelAppointment(appointment);
					
					try {
						List<LocalDateTime> availabilityList = UserManager.userManager.getAvailabilities(appointment.getMedicalPersonnel().getUsername());
						availabilityList.add(appointment.getDateTime());
						UserManager.userManager.updateMedicalPersonnelAvaiabilities(appointment.getMedicalPersonnel(), availabilityList);
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
        	//No need for Patient
        }

        @FXML
        void saveNotes(ActionEvent event) {
        	//No need for Patient
        }
    }

}



