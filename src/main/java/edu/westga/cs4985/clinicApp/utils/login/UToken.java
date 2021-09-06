package edu.westga.cs4985.clinicApp.utils.login;

public class UToken {
	
	public int tokenId;
	
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
}
