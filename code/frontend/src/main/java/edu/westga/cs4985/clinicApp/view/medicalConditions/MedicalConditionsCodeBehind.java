package edu.westga.cs4985.clinicApp.view.medicalConditions;

import java.io.IOException;
import java.time.LocalDate;
import edu.westga.cs4985.clinicApp.model.MedicalCondition;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;
import edu.westga.cs4985.clinicApp.viewmodel.PatientViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	public void initialize() {
		this.conditionNameTableColumn.setCellValueFactory(new PropertyValueFactory<MedicalCondition, String>("name"));
		this.conditionDiagnosedDateTableColumn
				.setCellValueFactory(new PropertyValueFactory<MedicalCondition, String>("diagnosisDate"));
		this.conditionTerminationTableColumn
				.setCellValueFactory(new PropertyValueFactory<MedicalCondition, String>("terminationDate"));
		this.conditionNotesColumn.setCellValueFactory(new PropertyValueFactory<MedicalCondition, String>("notes"));

		this.deleteConditionButton.disableProperty()
				.bind(this.medicalConditionTableView.getSelectionModel().selectedItemProperty().isNull());
	}

	@FXML
	void handelAddMedicalCondition(ActionEvent event) throws IOException {
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
	}

	@FXML
	void handleDeleteMedicalCondition(ActionEvent event) {
		onRemovePlaceHolder();
	}

	void onRemovePlaceHolder() {
		String name = "Lyme Disease";
		String diagnosisDate = "09-08-2012";
		String terminationDate = "N/A";
		String notes = "Tick bite";
		Patient patient = (Patient) User.user;
		MedicalCondition medicalCondition = new MedicalCondition(patient, name, diagnosisDate, terminationDate, notes);
		UserManager.userManager.addMedicalCondition(medicalCondition);
		UserManager.userManager.removeMedicalCondition(medicalCondition);
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
		void handleAddCondition(ActionEvent event) {
			Patient user = (Patient) User.user;
			String name = this.nameTextField.getText();
			LocalDate diagnosisDate = this.diagnosisDatePicker.getValue();
			LocalDate terminationDate = this.terminationDatePicker.getValue();
			String diagnosis = diagnosisDate.toString();
			String termination = terminationDate.toString();
			String notes = this.notesTextArea.getText();
			MedicalCondition medicalCondition = new MedicalCondition(user, name, diagnosis, termination, notes);
			UserManager.userManager.addMedicalCondition(medicalCondition);
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

		}

		private void returnToPreviousStage(ActionEvent event) {
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			currentStage.fireEvent(new WindowEvent(currentStage, WindowEvent.WINDOW_CLOSE_REQUEST));
			currentStage.close();
		}

	}

}
