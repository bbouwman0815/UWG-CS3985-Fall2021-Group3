package edu.westga.cs4985.clinicApp.view.login;

import java.io.IOException;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import edu.westga.cs4985.clinicApp.viewmodel.ClinicAppViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class LoginCodeBehind {

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
	public LoginCodeBehind() {
		this.viewmodel = new ClinicAppViewModel();
	}

	@FXML
	private void initialize() {
		this.bindToViewModel();
	}

	private void bindToViewModel() {
		this.usernameTextField.textProperty().bindBidirectional(this.viewmodel.userNameProperty());
		this.passwordTextField.textProperty().bindBidirectional(this.viewmodel.passwordTextProperty());
		this.passwordTextField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				this.loginButton.fire();
			}
		});
	}

	@FXML
	void handleLogin(ActionEvent event) {
		String userString = this.viewmodel.login();
		String[] userStringList = userString.split(",");
		User user = UserManager.userManager.login(userStringList[0], userStringList[1]);
		if (user == null) {
			Alert alert = WindowGenerator.openAlert("UserName or Password is incorrect!");
			alert.showAndWait();
		} else {
			try {
				User.setUser(user);
				Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				WindowGenerator.setUserView(currentStage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		

	}
}
