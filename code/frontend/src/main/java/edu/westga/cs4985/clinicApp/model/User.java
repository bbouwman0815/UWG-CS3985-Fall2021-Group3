package edu.westga.cs4985.clinicApp.model;

import edu.westga.cs4985.clinicApp.resources.UI;
import edu.westga.cs4985.clinicApp.utils.login.UToken;

/*
 * The User Class
 */
public abstract class User {

	public static User user = null;

	private String username;

	private String password;

	private UToken userToken;

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
		this.setUserToken();
	}
	
	private void setUserToken() {
		this.userToken = new UToken(this.username, this.password);
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User user = (User) obj;
			return this.userToken.getTokenId() == user.getUserToken().getTokenId();
		}
		return false;
	}

	public UToken getUserToken() {
		return this.userToken;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public static void setUser(User user) {
		User.user = user;
	}
}
