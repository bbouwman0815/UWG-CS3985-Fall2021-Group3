package edu.westga.cs4985.clinicApp.utils.login;

/*
 * @author Brian Bouwman
 * @version Fall 2021
 */
public class UToken {

	private int tokenId;

	/**
	 * Instantiates a new token.
	 */
	public UToken() {
	}

	/**
	 * Instantiates a new token.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public UToken(String username, String password) {
		this.setTokenId(username, password);
	}

	private void setTokenId(String username, String password) {
		int hashCode = new String(username + password).hashCode() * 66;
		this.tokenId = hashCode;
	}

	/**
	 * Gets the token id.
	 *
	 * @return the token id
	 */
	public int getTokenId() {
		return this.tokenId;
	}
}
