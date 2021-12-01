package edu.westga.cs4985.clinicApp.view.login;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.resources.WindowGenerator;
import edu.westga.cs4985.clinicApp.viewmodel.ClinicAppViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class LoginCodeBehind {

	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	private Button loginButton;

	@FXML
	private Button registerButton;

	@FXML
	private Button addPatientButton;

	@FXML
	private Button addMedicalPersonnel;

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
	void handleLogin(ActionEvent event) throws ParseException {
		
		String userString = this.viewmodel.login();
		String[] userStringList = userString.split(",");
		if (userStringList[0].equalsIgnoreCase("shutdown")) {
			UserManager.userManager().shutDownServer();
		}
		User user = UserManager.userManager().login(userStringList[0], userStringList[1]);
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

	@FXML
	void onRegister(ActionEvent event) throws IOException {
		RegisterCodeBehind codebehind = new RegisterCodeBehind();
		FXMLLoader loader = new FXMLLoader();
		loader.setController(codebehind);
		loader.setLocation(codebehind.getClass().getResource("Registration.fxml"));
		WindowGenerator.setupScene((Parent) loader.load(), "Registration Window");
	}
}
