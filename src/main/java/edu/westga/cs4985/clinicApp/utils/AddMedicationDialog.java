package edu.westga.cs4985.clinicApp.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import edu.westga.cs4985.clinicApp.model.Medication;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddMedicationDialog {
	static String brandString;
	static String dosageString;
	static String frequeString;
	static Date refilDate;
	static String specialInstricString;
	static String formString;
	static int refills;
	
	public static Medication display() {
		Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
         
        TextField brandTextField = new TextField();
        TextField dosageTextField = new TextField();
        TextField frequencyTextField = new TextField();
        DatePicker refillDatePicker = new DatePicker();
        TextField specialInstructionsTextField = new TextField();
        TextField refillsTextField = new TextField();
        TextField formTextField = new TextField();
         
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
             brandString = brandTextField.getText();
             dosageString = dosageTextField.getText();
             frequeString = frequencyTextField.getText();
             LocalDate localDate = refillDatePicker.getValue();
             Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
             refilDate = Date.from(instant);
             specialInstricString = specialInstructionsTextField.getText();
             refills = Integer.valueOf(refillsTextField.getText());
             formString = formTextField.getText();
             stage.close();
        });
        
        Button closeButton = new Button("Cancel");
        closeButton.setOnAction(e -> {
        	stage.close();
        });
     
        Label label1 = new Label(" Add New Medication");
        Label label2 = new Label("Brand:");
        Label label3 = new Label("Dosage:");
        Label label4 = new Label("Frequency:");
        Label label5 = new Label("Refill By:");
        Label label6 = new Label("Special Instructions:");
        Label label7 = new Label("Number of Refills:");
        Label label8 = new Label("From:");
         
        GridPane layout = new GridPane();
         
        layout.setPadding(new Insets(10, 10, 10, 10)); 
        layout.setVgap(5); 
        layout.setHgap(5); 
         
        layout.add(brandTextField, 1,1);
        layout.add(dosageTextField, 1,2);
        layout.add(formTextField, 1, 3);
        layout.add(frequencyTextField, 1, 4);
        layout.add(refillDatePicker, 1, 5);
        layout.add(specialInstructionsTextField, 1, 6);
        layout.add(refillsTextField, 1, 7);
        layout.add(addButton, 0,8);
        layout.add(closeButton, 1, 8);
        layout.add(label1, 1,0);
        layout.add(label2, 0,1);
        layout.add(label8, 0, 3);
        layout.add(label3, 0,2);
        layout.add(label4, 0, 4);
        layout.add(label5, 0, 5);
        layout.add(label6, 0, 6);
        layout.add(label7, 0, 7);
         
        Scene scene = new Scene(layout, 375, 275);          
        stage.setTitle("Dialog");
        stage.setScene(scene);
        stage.showAndWait();
         
        return new Medication(brandString, formString, dosageString, frequeString, refilDate, specialInstricString, refills);
	}
}
