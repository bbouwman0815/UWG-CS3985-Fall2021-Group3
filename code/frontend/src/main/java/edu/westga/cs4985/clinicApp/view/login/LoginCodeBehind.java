package edu.westga.cs4985.clinicApp.view.login;

import java.io.IOException;

import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import edu.westga.cs4985.clinicApp.utils.login.LoginVerifier;
import edu.westga.cs4985.clinicApp.utils.login.TokenGenerator;
import edu.westga.cs4985.clinicApp.utils.login.UToken;
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

public class LoginCodeBehind {

	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	private Button loginButton;
	
	private UToken userToken;

	private ClinicAppViewModel viewmodel;
	
	private LoginVerifier login;
	
	private TokenGenerator tokenGenerator;

	/**
	 * Instantiates a new code behind
	 */
	public LoginCodeBehind() {
		this.viewmodel = new ClinicAppViewModel();
		this.login = new LoginVerifier();
		this.tokenGenerator = new TokenGenerator();
	}

	@FXML
	private void initialize() {
		this.bindToViewModel();
	}

	private void bindToViewModel() {
	}

	@FXML
	void handleLogin(ActionEvent event) {
		if (this.verifyLogin()) {
			try {
				WindowGenerator.setupDashboardWindow(this.userToken);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	private boolean verifyLogin() {
		String loggingUserName = this.usernameTextField.getText();
		String loggingUserPW = this.passwordTextField.getText();
		this.tokenGenerator = new TokenGenerator(loggingUserName, loggingUserPW);
		this.userToken = this.tokenGenerator.getToken();
		
		return this.login.validateCredentials(loggingUserName, loggingUserPW);
	}
}
