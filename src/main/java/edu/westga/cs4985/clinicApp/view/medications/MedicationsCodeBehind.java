package edu.westga.cs4985.clinicApp.view.medications;

import java.util.Date;

import edu.westga.cs4985.clinicApp.model.Medication;
import edu.westga.cs4985.clinicApp.utils.AddMedicationDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MedicationsCodeBehind {

	@FXML
	private TableView<Medication> medicationTableView;

	@FXML
	private TableColumn<Medication, String> brandTableColumn;

	@FXML
	private TableColumn<Medication, String> formTableColumn;

	@FXML
	private TableColumn<Medication, String> dosageTableColumn;

	@FXML
	private TableColumn<Medication, String> frequencyTableColumn;

	@FXML
	private TableColumn<Medication, Date> refillByTableColumn;

	@FXML
	private TableColumn<Medication, String> instructionsTableColumn;

	@FXML
	private TableColumn<Medication, String> refillsTableColumn;

	@FXML
	private Button addMedicaitonButton;

	/**
	 * Instantiates a new medications code behind
	 */
	public MedicationsCodeBehind() {

	}
	
	@FXML
	void initialize() {
		this.brandTableColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("brand"));
		this.formTableColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("form"));
		this.dosageTableColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("dosage"));
		this.frequencyTableColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("frequency"));
		this.refillByTableColumn.setCellValueFactory(new PropertyValueFactory<Medication, Date>("refilDate"));
		this.instructionsTableColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("specialInstructionString"));
		this.refillsTableColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("refills"));
		
	}

	@FXML
	void handleAddMedication(ActionEvent event) {
		Medication inputMedication = AddMedicationDialog.display();
		this.medicationTableView.itemsProperty().get().add(inputMedication);
	}
}
