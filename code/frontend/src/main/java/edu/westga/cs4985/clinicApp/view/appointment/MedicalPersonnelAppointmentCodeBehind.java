package edu.westga.cs4985.clinicApp.view.appointment;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import edu.westga.cs4985.clinicApp.viewmodel.MedicalPersonnelViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class MedicalPersonnelAppointmentCodeBehind {
	
	private static final String APPOINTMENT_VIEW_POPUP = "../appointment/AppointmentViewPopup.fxml";
	private final String[] dateTimes = {"08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00",};
	
	@FXML
    private ListView<Appointment> futureAppointmentList;

    @FXML
    private Button showPast;

    @FXML
    private Button showfutureButton;

    @FXML
    private Button showAddAvailbilityButton;

    @FXML
    private ListView<LocalDateTime> availabilitiesList;

    @FXML
    private AnchorPane addAvailabilityPane;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<String> timePicker;

    @FXML
    private ListView<Appointment> pastAppointmentList;

    @FXML
    private Button hideAvailbilityButton;
    
    private MedicalPersonnelViewModel viewModel;
    
    public MedicalPersonnelAppointmentCodeBehind() {
    	this.viewModel = new MedicalPersonnelViewModel();
    }
    
    @FXML
    public void initialize() throws ParseException {
    	this.setBindings();
    	this.setListeners();
    	List<Appointment> appointments = FXCollections.observableArrayList(UserManager.userManager.getAppointmentsForMedicalPersonnel(this.viewModel.getMedicalePersonnel().getUsername()));
    	List<LocalDateTime> dayTimes = FXCollections.observableArrayList(UserManager.userManager.getAvailabilities(this.viewModel.getMedicalePersonnel().getUsername()));
    	this.viewModel.filterAppointment(appointments);
    	this.viewModel.setAvailabilityList(dayTimes);
    	this.showfutureButton.setVisible(false);
    	this.addAvailabilityPane.setVisible(false);
    	this.futureAppointmentList.setVisible(true);
    	this.pastAppointmentList.setVisible(false);
    	this.hideAvailbilityButton.setVisible(false);
    	this.showPast.setVisible(true);
    	
    	this.showAddAvailbilityButton.setVisible(true);
    	this.timePicker.itemsProperty().set(FXCollections.observableArrayList(this.dateTimes));
    	
    }
    
    public void setBindings() {
    	this.futureAppointmentList.itemsProperty().bindBidirectional(this.viewModel.futureAppointmentListProperty());
    	this.pastAppointmentList.itemsProperty().bindBidirectional(this.viewModel.pastAppointmentListProperty());
    	this.viewModel.selectedFutureAppointmentProperty().bind(this.futureAppointmentList.getSelectionModel().selectedItemProperty());
    	this.viewModel.selectedPastAppointmentProperty().bind(this.pastAppointmentList.getSelectionModel().selectedItemProperty());
    	this.availabilitiesList.itemsProperty().bindBidirectional(this.viewModel.availabilityListProperty());
    	this.viewModel.selectedAvailabilityProperty().bind(this.availabilitiesList.getSelectionModel().selectedItemProperty());
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
    	
    	this.availabilitiesList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			Alert alert = WindowGenerator.openConfirm("Are you sure want to remove the availability?");
				alert.setOnCloseRequest((evt) -> {
					if (alert.getResult().getButtonData().equals(ButtonData.YES)) {
						try {
							this.viewModel.deleteAvailability();
							UserManager.userManager.updateMedicalPersonnelAvaiabilities(this.viewModel.getMedicalePersonnel(),this.viewModel.availabilityList());
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				});
				alert.showAndWait();
    		}
    	});
    }

    @FXML
    void onAddAvailability(ActionEvent event) {
    	if (this.datePicker.getValue() == null) {
    		Alert alert = WindowGenerator.openAlert("Please select your date!");
        	
			alert.showAndWait();
    	} else if (this.timePicker.getValue() == null) {
    		Alert alert = WindowGenerator.openAlert("Please select your time!");
        	
			alert.showAndWait();
    	} else {
    		String dateTime = this.datePicker.getValue().toString() + " " + this.timePicker.getValue().toString();
        	if(this.viewModel.isAddedAvailability(dateTime)) {
        		Alert alert = WindowGenerator.openAlert("The availability already added! Please select another date!");
            	
    			alert.showAndWait();
        	}  else {
        		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        		LocalDateTime availability = LocalDateTime.parse(dateTime, formatter);
        		if (availability.isBefore(LocalDateTime.now())) {
        			Alert alert = WindowGenerator.openAlert("We are unable to add past availability! Please select another date!");
                	
        			alert.showAndWait();
        		} else {
        			Alert addAlert = WindowGenerator.openConfirm("Are you sure want to addAlert this availability?");
            		addAlert.setOnCloseRequest((action) -> {
                		if (addAlert.getResult().getButtonData().equals(ButtonData.YES)) {
                			try {
        						this.viewModel.addAvailability(dateTime);

        	        			UserManager.userManager.updateMedicalPersonnelAvaiabilities(this.viewModel.getMedicalePersonnel(),this.viewModel.availabilityList());

        					} catch (ParseException e) {
        						e.printStackTrace();
        					}

                		}
                	});
            		addAlert.show();
        		}
        		
        	}
    	}
    	
    }

    @FXML
    void onHideAddAvailabitiy(ActionEvent event) {
    	this.addAvailabilityPane.setVisible(false);
    	this.hideAvailbilityButton.setVisible(false);
    	
    	this.showAddAvailbilityButton.setVisible(true);
    }

    @FXML
    void onShowAddAvailabitiy(ActionEvent event) {
    	this.addAvailabilityPane.setVisible(true);
    	this.hideAvailbilityButton.setVisible(true);
    	
    	this.showAddAvailbilityButton.setVisible(false);
    }

    @FXML
    void onShowFuture(ActionEvent event) {
    	this.futureAppointmentList.setVisible(true);
    	this.pastAppointmentList.setVisible(false);
    	this.showfutureButton.setVisible(false);
    	this.showPast.setVisible(true);
    }

    @FXML
    void onShowPast(ActionEvent event) {

    	this.futureAppointmentList.setVisible(false);
    	this.pastAppointmentList.setVisible(true);
    	this.showfutureButton.setVisible(true);
    	this.showPast.setVisible(false);
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
        
        private MedicalPersonnelViewModel viewModel;
        
        public AppointmentViewPopupCodeBehind(MedicalPersonnelViewModel viewModel) {
        	this.viewModel = viewModel;
        }
        
        @FXML
        public void initialize() {
        	this.editButton.setVisible(true);
        	this.saveButton.setVisible(false);
        	this.appointmentNotes.setEditable(false);
        	if (this.viewModel.selectedFutureAppointmentProperty().get() != null) {
        		this.medicalPersonnelLabel.textProperty().set("Medical Personnel: " + this.viewModel.selectedFutureAppointmentProperty().get().getMedicalPersonnel());
            	this.timeLabel.textProperty().set("Time: " + this.viewModel.selectedFutureAppointmentProperty().get().getDateTime());
            	this.appointmentNotes.textProperty().set(this.viewModel.selectedFutureAppointmentProperty().get().getNotes());
            	this.patientLabel.textProperty().set("Patient: " + this.viewModel.selectedFutureAppointmentProperty().get().getPatient().getFullName());
            	this.locationLabel.textProperty().set("Location: " + this.viewModel.selectedFutureAppointmentProperty().get().getLocation());
        		this.cancelAppointmentButton.setVisible(true);
        	} 
        	if (this.viewModel.selectedPastAppointmentProperty().get() != null) {
        		this.cancelAppointmentButton.setVisible(false);
        		this.medicalPersonnelLabel.textProperty().set("Medical Personnel: " + this.viewModel.selectedPastAppointmentProperty().get().getMedicalPersonnel());
            	this.timeLabel.textProperty().set("Time: " + this.viewModel.selectedPastAppointmentProperty().get().getDateTime());
            	this.appointmentNotes.textProperty().set(this.viewModel.selectedPastAppointmentProperty().get().getNotes());
            	this.patientLabel.textProperty().set("Patient: " + this.viewModel.selectedPastAppointmentProperty().get().getPatient().getFullName());
            	this.locationLabel.textProperty().set("Location: " + this.viewModel.selectedPastAppointmentProperty().get().getLocation());
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
						
						List<LocalDateTime> dayTimes = FXCollections.observableArrayList(UserManager.userManager.getAvailabilities(this.viewModel.getMedicalePersonnel().getUsername()));
				    	this.viewModel.setAvailabilityList(dayTimes);
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

			this.viewModel.selectedFutureAppointmentProperty().get().setNotes(this.appointmentNotes.getText());
			UserManager.userManager.updateAppointment(this.viewModel.selectedFutureAppointmentProperty().get());
        }
        

    }

}
