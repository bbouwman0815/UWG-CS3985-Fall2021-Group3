package edu.westga.cs4985.clinicApp.model;

/**
 * The Class LoggedUser.
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 */
public class LoggedUser extends User {

	/**
	 * Instantiates a new logged user.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public LoggedUser(String username, String password) {
		super(username, password);
	}
	
	/**
	 * Record login.
	 */
	public void recordLogin() {
		//record login info to txt file lmao
	}

}
