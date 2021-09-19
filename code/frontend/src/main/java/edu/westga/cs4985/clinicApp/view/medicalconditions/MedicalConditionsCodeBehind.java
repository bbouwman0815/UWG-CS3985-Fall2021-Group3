package edu.westga.cs4985.clinicApp.view.medicalconditions;

import java.io.IOException;
import java.time.LocalDate;
import edu.westga.cs4985.clinicApp.model.MedicalCondition;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.model.UserManager;
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

public class MedicalConditionsCodeBehind {

	@FXML
	private Button addMedicalConditionButton;

	public MedicalConditionsCodeBehind() {

	}

	@FXML
	public void initialize() {

	}

	@FXML
	void handleAddMedicalCondition(ActionEvent event) throws IOException {
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
