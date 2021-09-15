package edu.westga.cs4985.clinicApp.model;

import edu.westga.cs4985.clinicApp.resources.UI;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

/**
 * The Patient class.
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 *
 */
public class Patient extends Person {

	private String insurance;

	private String phoneNumber;

	private String email;
	
	private String careGiver;

	/**
	 * Instantiates a new patient.
	 *
	 * @param firstName   the first name
	 * @param lastName    the last name
	 * @param gender      the gender
	 * @param dateOfBirth the date of birth
	 * @param address1    the address 1
	 * @param address2    the address 2
	 * @param city        the city
	 * @param state       the state
	 * @param country     the country
	 * @param race        the race
	 * @param ethnicity   the ethnicity
	 * @param phoneNumber the phone number
	 * @param email       the email
	 * @param insurance   the insurance
	 * 
	 * @precondition insurance != null && !insurance.isEmpty() && phoneNumber !=
	 *               null && !phoneNumber.isEmpty() && email != null &&
	 *               !email.isEmpty()
	 *
	 * @postcondition insurance == getInsurance() && phoneNumber == getPhoneNumber()
	 *                && email == getEmail()
	 */
	public Patient(String firstName, String lastName, String gender, String dateOfBirth, String address1,
			String address2, String city, String state, String country, String race, String ethnicity,
			String phoneNumber, String email, String insurance,
			String username, String password) {
		super(firstName, lastName, gender, dateOfBirth, address1, address2, city, state, country, race, ethnicity, username, password);
		if (insurance == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_FIRSTNAME);
		}
		if (insurance.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_FIRSTNAME);
		}
		if (phoneNumber == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_LASTNAME);
		}
		if (phoneNumber.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_LASTNAME);
		}
		if (email == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_DATEOFBIRTH);
		}
		if (email.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_DATEOFBIRTH);
		}
		this.insurance = insurance;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.careGiver = "";
	}
	
	/**
	 * Gets the caregiver.
	 *
	 * @return the caregiver
	 */
	public String getCaregiver() {
		return this.careGiver;
	}
	
	/**
	 * Sets the caregiver.
	 *
	 * @param insurance the new caregiver
	 */
	public void setCaregiver(String caregiver) {
		if (caregiver == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_DATEOFBIRTH);
		}
		this.careGiver = caregiver;
	}

	/**
	 * Gets the insurance.
	 *
	 * @return the insurance
	 */
	public String getInsurance() {
		return insurance;
	}

	/**
	 * Sets the insurance.
	 *
	 * @param insurance the new insurance
	 */
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFullName() {
		return this.getFirstName() + " " + this.getLastName();
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
