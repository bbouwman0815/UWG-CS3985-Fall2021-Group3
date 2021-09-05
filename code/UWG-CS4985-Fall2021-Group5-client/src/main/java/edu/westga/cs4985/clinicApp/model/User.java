package edu.westga.cs4985.clinicApp.model;

import edu.westga.cs4985.clinicApp.resources.UI;

/*
 * The User Class
 */
public class User {

	public String username;

	public String password;

	/**
	 * Instantiates a new user.
	 * 
	 * @precondition username != null && !(username.isEmpty()) && password != null
	 *               && !(password.isEmpty())
	 * @postcondition username = getUsername() && password = getPassword()
	 *
	 * @param username the username
	 * @param password the password
	 */
	public User(String username, String password) {
		if (username == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_USERNAME);
		}
		if (username.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_USERNAME);
		}
		if (password == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_PASSWORD);
		}
		if (password.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_PASSWORD);
		}
		this.username = username;
		this.password = password;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}
}
