package edu.westga.cs4985.clinicApp.view.medicalConditions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.json.simple.parser.ParseException;

import edu.westga.cs4985.clinicApp.model.MedicalCondition;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import edu.westga.cs4985.clinicApp.viewmodel.PatientViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MedicalConditionsCodeBehind {

	@FXML
	private Button dashboardNavButton;

	@FXML
	private Button medicationsNavButton;

	@FXML
	private Button medicalConditionsNavButton;

	@FXML
	private Button appointmentNavButton;

	@FXML
	private TableView<MedicalCondition> medicalConditionTableView;

	@FXML
	private TableColumn<MedicalCondition, String> conditionNameTableColumn;

	@FXML
	private TableColumn<MedicalCondition, String> conditionDiagnosedDateTableColumn;

	@FXML
	private TableColumn<MedicalCondition, String> conditionTerminationTableColumn;

	@FXML
	private TableColumn<MedicalCondition, String> conditionNotesColumn;

	@FXML
	private Button addMedicationConditionButton;

	@FXML
	private Button deleteConditionButton;

	private PatientViewModel viewModel;

	public MedicalConditionsCodeBehind() {
		this.viewModel = new PatientViewModel();
	}

	@FXML
	public void initialize() throws ParseException {
		this.conditionNameTableColumn.setCellValueFactory(new PropertyValueFactory<MedicalCondition, String>("name"));
		this.conditionDiagnosedDateTableColumn
				.setCellValueFactory(new PropertyValueFactory<MedicalCondition, String>("diagnosisDate"));
		this.conditionTerminationTableColumn
				.setCellValueFactory(new PropertyValueFactory<MedicalCondition, String>("terminationDate"));
		this.conditionNotesColumn.setCellValueFactory(new PropertyValueFactory<MedicalCondition, String>("notes"));

		this.deleteConditionButton.disableProperty()
				.bind(this.medicalConditionTableView.getSelectionModel().selectedItemProperty().isNull());
		this.setMedicalConditions();
	}

	@FXML
	void handelAddMedicalCondition(ActionEvent event) throws IOException, ParseException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../medicalconditions/AddMedicalConditionPopup.fxml"));
		loader.setController(new AddMedicalConditionPopupCodeBehind());
		Pane pane = (Pane) loader.load();
		Stage popup = new Stage();
		Scene scene = new Scene(pane);
		popup.setScene(scene);
		popup.setResizable(false);
		popup.setTitle("Add Medical Condition Window");
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.show();
		this.setMedicalConditions();
	}

	@FXML
	void handleDeleteMedicalCondition(ActionEvent event) {
		this.onRemovePlaceHolder();
	}

	void onRemovePlaceHolder() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.contentTextProperty().set("Are you sure you want to remove this medication?");
		alert.showAndWait().ifPresent(respone -> {
			if (respone == ButtonType.OK) {
				MedicalCondition selectedMedicalCondition = this.medicalConditionTableView.getSelectionModel()
						.getSelectedItem();
				if (selectedMedicalCondition != null) {
					this.medicalConditionTableView.getItems().remove(selectedMedicalCondition);
					UserManager.userManager.removeMedicalCondition(selectedMedicalCondition);
				}
			}
		});
	}

	void setMedicalConditions() throws ParseException {
		List<MedicalCondition> medicalConditions = FXCollections.observableArrayList(
				UserManager.userManager.getMedicalConditions(this.viewModel.getPatient().getUsername()));
		this.medicalConditionTableView.itemsProperty().set((ObservableList<MedicalCondition>) medicalConditions);
	}

	public class AddMedicalConditionPopupCodeBehind {

		@FXML
		private Button addButton;

		@FXML
		private Button cancelButton;

		@FXML
		private DatePicker diagnosisDatePicker;

		@FXML
		private TextField nameTextField;

		@FXML
		private DatePicker terminationDatePicker;

		@FXML
		private RadioButton conditionRadioButton;

		@FXML
		private TextArea notesTextArea;

		@FXML
		void handleAddCondition(ActionEvent event) throws ParseException {
			Patient user = (Patient) User.user;
			try {
				String name = this.nameTextField.getText();
				LocalDate diagnosisDate = this.diagnosisDatePicker.getValue();
				LocalDate terminationDate = this.terminationDatePicker.getValue();
				String diagnosis = diagnosisDate.toString();
				String termination = terminationDate.toString();
				String notes = this.notesTextArea.getText();
				MedicalCondition medicalCondition = new MedicalCondition(user, name, diagnosis, termination, notes);
				UserManager.userManager.addMedicalCondition(medicalCondition);
				MedicalConditionsCodeBehind.this.setMedicalConditions();
				this.returnToPreviousStage(event);
			} catch (NullPointerException e) {
				Alert alert = WindowGenerator.openAlert("Please fill out all data!");

				alert.showAndWait();
			}

		}

		@FXML
		void handleCancel(ActionEvent event) {
			this.returnToPreviousStage(event);
		}

		@FXML
		void handleTerminationDate(ActionEvent event) {

		}

		public AddMedicalConditionPopupCodeBehind() {
		}

		@FXML
		public void initialize() {
			this.conditionRadioButton.setDisable(true);
		}

		private void returnToPreviousStage(ActionEvent event) {
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
			currentStage.close();
		}

	}

}
