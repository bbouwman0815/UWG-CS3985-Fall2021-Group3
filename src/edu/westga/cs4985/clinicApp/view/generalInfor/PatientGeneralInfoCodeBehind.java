package edu.westga.cs4985.clinicApp.view.generalInfor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    public void initialize() {
    	this.saveButton.setVisible(false);
    	this.cancelButton.setVisible(false);
    }

    @FXML
    void addCaregiver(ActionEvent event) {

    }

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void editGeneralInfo(ActionEvent event) {
    	this.editbutton.setVisible(false);
    	this.saveButton.setVisible(true);
    	this.cancelButton.setVisible(true);

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
    void getCountry(ActionEvent event) {

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
    void getState(ActionEvent event) {

    }

    @FXML
    void saveGeneralInfo(ActionEvent event) {
    	this.editbutton.setVisible(true);
    	this.saveButton.setVisible(false);
    	this.cancelButton.setVisible(false);

    }

}

