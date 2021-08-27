package edu.westga.cs4985.clinicApp.view;

import java.io.IOException;

import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.viewmodel.ClinicAppViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClinicAppCodeBehind {

	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	private Button loginButton;

	private ClinicAppViewModel viewmodel;

	/**
	 * Instantiates a new code behind
	 */
	public ClinicAppCodeBehind() {
		this.viewmodel = new ClinicAppViewModel();
	}

	@FXML
	private void initialize() {
		this.bindToViewModel();
	}

	private void bindToViewModel() {
	}

	@FXML
	void handleLogin(ActionEvent event) {
		if (verifyLogin()) {
			User u = new User("Admin", "123");
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			try {
				Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("edu/westga/cs4985/clinicApp/view/DashboardGui.fxml"));
				stage.setUserData(u);
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				System.err.println(String.format("Error: %s",  e.getMessage()));
			}
		}
	}
	
	private boolean verifyLogin() {
		User user = new User("Admin","123");
		String loggingUserName = this.usernameTextField.getText();
		String loggingUserPW = this.passwordTextField.getText();
		
		if (user.getUsername().equals(loggingUserName) && user.getPassword().equals(loggingUserPW)) {
			return true;
		}
		return false;
	}
}
