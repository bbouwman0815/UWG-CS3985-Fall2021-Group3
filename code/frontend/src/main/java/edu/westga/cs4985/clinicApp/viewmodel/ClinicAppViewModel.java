package edu.westga.cs4985.clinicApp.viewmodel;

import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.model.UserManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * The ClinicAppViewModel class.
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class ClinicAppViewModel {
	
	private StringProperty userNameProperty;
	private StringProperty passwordProperty;

	/**
	 * Creates a ViewModel for entry user login view.
	 * 
	 * @precondition none
	 * @postcondition userNameTextProperty().getValue() == null &&
	 *                passwordTextProperty().getValue() == null
	 */
	public ClinicAppViewModel() {
		this.userNameProperty = new SimpleStringProperty();
		this.passwordProperty = new SimpleStringProperty();
	}

	/**
	 * Login in a user by returns its user object.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the user who logged in; null if user does not exist.
	 */
	public String login() {
		String user = null;
		try {
			String userName = this.userNameProperty.get();
			String password = this.passwordProperty.get();
			user = ""+ userName + "," + password;
		} catch (Exception e) {
			user = null;
		}
		return user;
	}

	/**
	 * Gets the user name text property.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the user name text property
	 */
	public StringProperty userNameProperty() {
		return this.userNameProperty;
	}

	/**
	 * Gets the password text property.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the password text property
	 */
	public StringProperty passwordTextProperty() {
		return this.passwordProperty;
	}

}
