package edu.westga.cs4985.clinicApp.utils.login;

import edu.westga.cs4985.clinicApp.model.User;

/**
 * The Login class.
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 * 
 */
public class LoginVerifier implements ILogin{
	
	/**
	 * Instantiates a new login.
	 */
	public LoginVerifier() {
		
	}

	/**
	 * Validate credentials.
	 *
	 * @param username the username
	 * @param password the password
	 * @return true, if successful
	 */
	@Override
	public boolean validateCredentials(String username, String password) {
		//code that communicates with server
		return true;
	}

	/**
	 * Login.
	 *
	 * @return the user
	 */
	@Override
	public User login() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
