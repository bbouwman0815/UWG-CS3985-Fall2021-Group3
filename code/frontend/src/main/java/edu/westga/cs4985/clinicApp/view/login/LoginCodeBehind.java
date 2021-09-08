package edu.westga.cs4985.clinicApp.view.login;

import java.io.IOException;

import edu.westga.cs4985.clinicApp.model.LoggedUser;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import edu.westga.cs4985.clinicApp.utils.login.LoginVerifier;
import edu.westga.cs4985.clinicApp.viewmodel.ClinicAppViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
	}

	@FXML
	void handleLogin(ActionEvent event) {
		String loggingUserName = this.usernameTextField.getText();
		String loggingUserPW = this.passwordTextField.getText();
		if (verifyLogin(loggingUserName, loggingUserPW)) {
			try {
				User.user = new LoggedUser(loggingUserName, loggingUserPW);
				WindowGenerator.setupDashboardWindow();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static boolean verifyLogin(String loggingUserName, String loggingUserPW) {
		LoginVerifier login = new LoginVerifier();
		return login.validateCredentials(loggingUserName, loggingUserPW);
	}
}
