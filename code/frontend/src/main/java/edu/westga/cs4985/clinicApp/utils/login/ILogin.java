package edu.westga.cs4985.clinicApp.utils.login;

import edu.westga.cs4985.clinicApp.model.User;

/**
 * The Interface ILogin.
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 */
public interface ILogin {

	/*
	 * Validates a users credentials
	 * 
	 * @param username the username
	 * 
	 * @param password the password
	 * 
	 * @return true if valid, false otherwise
	 * 
	 */
	boolean validateCredentials(String username, String password);

	/*
	 * Logs the user in
	 */
	User login();

}
