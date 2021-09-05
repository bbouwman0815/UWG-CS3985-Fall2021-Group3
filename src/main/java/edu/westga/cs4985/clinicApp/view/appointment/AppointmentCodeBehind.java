package edu.westga.cs4985.clinicApp.view.appointment;

import java.io.IOException;
import java.time.LocalDateTime;
import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.viewmodel.PatientViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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
    
    private PatientViewModel viewModel;
    
    public AppointmentCodeBehind() {
    	this.viewModel = new PatientViewModel();
    }
    
    @FXML
    public void initialize() {
    	this.futureAppointmentList.itemsProperty().bindBidirectional(this.viewModel.appointmentListProperty());
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
        
        private PatientViewModel viewModel;
        
        public BookAppointmentPopupCodeBehind(PatientViewModel viewModel) {
        	this.viewModel = viewModel;
        }
        
        @FXML
        public void initialize() {
        	this.medicalPersonList.getItems().add("Person A");
        	this.medicalPersonList.getItems().add("Person B");
        	this.availableTimeList.getItems().add(LocalDateTime.of(2021,9,01,13,00));
        	this.availableTimeList.getItems().add(LocalDateTime.of(2021,9,02,14,00));
        	
        	this.viewModel.seletedMedicalPersonnel().bind(this.medicalPersonList.getSelectionModel().selectedItemProperty());
        	this.viewModel.selectedAvailabilityProperty().bind(this.availableTimeList.getSelectionModel().selectedItemProperty());
        	
        }

        @FXML
        void bookTheAppointment(ActionEvent event) throws IOException {
        	this.viewModel.bookAppointment();
			this.viewModel.notesProperty().set(this.noteTextBox.getText());
        	
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
        	popup.show();
        	
        	Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
        	currentStage.close();
        	
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
        
        private PatientViewModel viewModel;
        
        public AppointmentViewPopupCodeBehind(PatientViewModel viewModel) {
        	this.viewModel = viewModel;
        }
        
        @FXML
        public void initialize() {
        	this.saveButton.setVisible(false);
        	this.appointmentNotes.setEditable(false);
        	this.medicalPersonnelLabel.textProperty().set("Medical Personnel: " + this.viewModel.seletedMedicalPersonnel().get());
        	this.timeLabel.textProperty().set("Time: " + this.viewModel.selectedAvailabilityProperty().get());
        	this.appointmentNotes.textProperty().set(this.viewModel.notesProperty().get());
        	
        }

        @FXML
        void cancelAppointment(ActionEvent event) {

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
        	this.cancelAppointmentButton.setVisible(true);
        }

    }



}



