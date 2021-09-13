package edu.westga.cs4985.clinicApp.view.appointment;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.UserManager;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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
    private ListView<Appointment> futureAppointmentList;

    @FXML
    private ListView<Appointment> pastAppointmentList;
    
    private PatientAppointmentViewModel viewModel;
    
    public AppointmentCodeBehind() {
    	this.viewModel = new PatientAppointmentViewModel();
    }
    
    @FXML
    public void initialize() {
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
    				
    				FXMLLoader loader = new FXMLLoader();
    	        	loader.setLocation(getClass().getResource("../appointment/AppointmentViewPopup.fxml"));
    	        	loader.setController(new AppointmentViewPopupCodeBehind(this.viewModel));
    	        	
    	        	Pane pane = (Pane) loader.load();
    	        	Stage popup = new Stage();
    	        	Scene scene = new Scene(pane);
    	        	popup.setScene(scene);
    	        	popup.setResizable(false);
    	        	popup.setTitle("Appointment View Window");
    	        	popup.initModality(Modality.APPLICATION_MODAL);
    	        	
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
    				
    				FXMLLoader loader = new FXMLLoader();
    	        	loader.setLocation(getClass().getResource("../appointment/AppointmentViewPopup.fxml"));
    	        	loader.setController(new AppointmentViewPopupCodeBehind(this.viewModel));
    	        	
    	        	Pane pane = (Pane) loader.load();
    	        	Stage popup = new Stage();
    	        	Scene scene = new Scene(pane);
    	        	popup.setScene(scene);
    	        	popup.setResizable(false);
    	        	popup.setTitle("Appointment View Window");
    	        	popup.initModality(Modality.APPLICATION_MODAL);
    	        	
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
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../appointment/BookAppointmentPopup.fxml"));
    	loader.setController(new BookAppointmentPopupCodeBehind(this.viewModel));
    	Pane pane = (Pane) loader.load();
    	Stage popup = new Stage();
    	Scene scene = new Scene(pane);
    	popup.setScene(scene);
    	popup.setResizable(false);
    	popup.setTitle("Booking Appointment Window");
    	popup.initModality(Modality.APPLICATION_MODAL);
    	popup.show();
    }

    public class BookAppointmentPopupCodeBehind {

        @FXML
        private ListView<String> medicalPersonList;

        @FXML
        private ListView<LocalDateTime> availableTimeList;

        @FXML
        private TextArea noteTextBox;
        
        private PatientAppointmentViewModel viewModel;
        
        public BookAppointmentPopupCodeBehind(PatientAppointmentViewModel viewModel) {
        	this.viewModel = viewModel;
        }
        
        @FXML
        public void initialize() {
        	
        	this.medicalPersonList.getItems().add("Person A");
        	this.medicalPersonList.getItems().add("Person B");
        	this.availableTimeList.getItems().add(LocalDateTime.of(2021,10,01,13,00));
        	this.availableTimeList.getItems().add(LocalDateTime.of(2021,10,02,14,00));
        	this.availableTimeList.getItems().add(LocalDateTime.of(2021,11,01,13,00));
        	this.setBindings();
        }
        
        public void setBindings() {
        	this.viewModel.seletedMedicalPersonnel().bind(this.medicalPersonList.getSelectionModel().selectedItemProperty());
        	this.viewModel.selectedAvailabilityProperty().bind(this.availableTimeList.getSelectionModel().selectedItemProperty());
        	
        }

        @FXML
        void bookTheAppointment(ActionEvent event) throws IOException {
        	if(this.viewModel.isBookedAppointment()) {
        		Alert alert = new Alert(AlertType.CONFIRMATION, "The appointment already book! Please select another date!", ButtonType.OK);
            	
    			alert.showAndWait();
        	} else {
        		Alert bookAlert = new Alert(AlertType.CONFIRMATION, "Are you sure want to book this appointment?", ButtonType.CANCEL, ButtonType.YES);
            	bookAlert.setOnCloseRequest((action) -> {
            		if (bookAlert.getResult().getButtonData().equals(ButtonData.YES)) {
            			this.viewModel.notesProperty().set(this.noteTextBox.getText());
            			Appointment appointment = this.viewModel.bookAppointment();
            			UserManager.userManager.bookAppointment(appointment);
            			FXMLLoader loader = new FXMLLoader();
                    	loader.setLocation(getClass().getResource("../appointment/AppointmentViewPopup.fxml"));
                    	loader.setController(new AppointmentViewPopupCodeBehind(this.viewModel));
                    	Pane pane;
						try {
							pane = (Pane) loader.load();
							Stage popup = new Stage();
	                    	Scene scene = new Scene(pane);
	                    	popup.setScene(scene);
	                    	popup.setResizable(false);
	                    	popup.setTitle("Appointment View Window");
	                    	popup.initModality(Modality.APPLICATION_MODAL);
	                    	popup.show();
	                    	
	                    	Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	                    	currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
	                    	currentStage.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
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
        void getCityName(ActionEvent event) {

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
        
        private PatientAppointmentViewModel viewModel;
        
        public AppointmentViewPopupCodeBehind(PatientAppointmentViewModel viewModel) {
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
        	} else if (this.viewModel.selectedFutureAppointmentProperty().get() == null) {
        		this.cancelAppointmentButton.setVisible(false);
        		this.medicalPersonnelLabel.textProperty().set("Medical Personnel: " + this.viewModel.seletedMedicalPersonnel().get());
        		this.patientLabel.textProperty().set("Patient: " + this.viewModel.getPatient().getFullName());
            	this.timeLabel.textProperty().set("Time: " + this.viewModel.selectedAvailabilityProperty().get());
            	this.appointmentNotes.textProperty().set(this.viewModel.notesProperty().get());
        		
        	}
        	if (this.viewModel.selectedPastAppointmentProperty().get() != null) {
        		this.cancelAppointmentButton.setVisible(false);
        		this.editButton.setVisible(true);
        		this.medicalPersonnelLabel.textProperty().set("Medical Personnel: " + this.viewModel.selectedPastAppointmentProperty().get().getMedicalPersonnel());
            	this.timeLabel.textProperty().set("Time: " + this.viewModel.selectedPastAppointmentProperty().get().getDateTime());
            	this.appointmentNotes.textProperty().set(this.viewModel.selectedPastAppointmentProperty().get().getNotes());
            	this.patientLabel.textProperty().set("Patient: " + this.viewModel.selectedPastAppointmentProperty().get().getPatient().getFullName());
            	this.locationLabel.textProperty().set("Location: " + this.viewModel.selectedPastAppointmentProperty().get().getLocation());
        	}
        }

        @FXML
        void cancelAppointment(ActionEvent event) {
        	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to cancel this appointment?", ButtonType.YES, ButtonType.NO);
        	
			alert.setOnCloseRequest((evt) -> {
				if (alert.getResult().getButtonData().equals(ButtonData.YES)) {
					Appointment appointment =  this.viewModel.cancelAppointment();
					UserManager.userManager.cancelAppointment(appointment);
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
        	this.saveButton.setVisible(true);
        	this.appointmentNotes.setEditable(true);
        	this.OKButton.setVisible(false);
        	this.cancelAppointmentButton.setVisible(false);
        }

        @FXML
        void saveNotes(ActionEvent event) {
        	this.saveButton.setVisible(false);
        	this.appointmentNotes.setEditable(false);
        	this.OKButton.setVisible(true);

			this.viewModel.notesProperty().set(this.appointmentNotes.getText());
        }
        

    }



}



