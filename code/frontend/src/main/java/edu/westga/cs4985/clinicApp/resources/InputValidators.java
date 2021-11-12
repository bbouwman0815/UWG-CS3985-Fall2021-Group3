package edu.westga.cs4985.clinicApp.resources;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class InputValidators.
 * 
 * @author Brian Bouwman
 * @version Fall 20201
 */
public class InputValidators {

	/**
	 * Validate phone number.
	 *
	 * @param phoneNumber the phone number
	 * 
	 * @return true if valid, false otherwise
	 */
	public static boolean validatePhoneNumber(String phoneNumber) {
		Pattern pattern = Pattern.compile("^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

	/**
	 * Validate email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	public static boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * Validate birthday.
	 *
	 * @param birthday the birthday
	 * @return true, if successful
	 */
	public static boolean validateBirthday(String birthday) {
		Pattern pattern = Pattern.compile("^(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$");
		Matcher matcher = pattern.matcher(birthday);
		return matcher.matches();
	}

}
