package edu.westga.cs4985.clinicApp.model;

import edu.westga.cs4985.clinicApp.resources.UI;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

/**
 * The Person class.
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 *
 */
public class Person {

	private String firstName;

	private String lastName;

	private Gender gender;

	private String dateOfBirth;

	private String address1;

	private String address2;

	private String city;

	private String state;

	private String country;

	private String race;

	private String ethnicity;

	public Person() {

	}

	/**
	 * Instantiates a new person.
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
	 * 
	 * @precondition firstName != null && !firstName.isEmpty() && lastName != null
	 *               && !lastName.isEmpty() && gender != null && dateOfBirth != null
	 *               && !dateOfBirth.isEmpty() && address1 != null &&
	 *               !address1.isEmpty() && address2 != null
	 *               && city != null && !city.isEmpty() && state != null &&
	 *               !state.isEmpty() && country != null && race =! null &&
	 *               ethnicity != null
	 * @postcondition firstName = getFirstName() && lastName == getLastName() &&
	 *                gender == getGender() && dateOfBirth = getDateOfBirth() &&
	 *                address1 = getAddress1() && address2 == getAddress2() && city
	 *                == getCity() state == getState() && country == getCountry() &&
	 *                race == getRace() && ethnicity == getEthnicity
	 */
	public Person(String firstName, String lastName, String gender, String dateOfBirth, String address1,
			String address2, String city, String state, String country, String race, String ethnicity) {
		nameDOBCheck(firstName, lastName, dateOfBirth);
		addressCheck(address1, address2, city, state);
		demographicCheck(gender, country, race, ethnicity);
	}

	private void nameDOBCheck(String firstName, String lastName, String dateOfBirth) {
		if (firstName == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_FIRSTNAME);
		}
		if (firstName.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_FIRSTNAME);
		}
		if (lastName == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_LASTNAME);
		}
		if (lastName.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_LASTNAME);
		}
		if (dateOfBirth == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_DATEOFBIRTH);
		}
		if (dateOfBirth.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_DATEOFBIRTH);
		}
	}

	private void addressCheck(String address1, String address2, String city, String state) {
		if (address1 == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_ADDRESS1);
		}
		if (address1.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_ADDRESS1);
		}
		if (address2 == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_ADDRESS2);
		}
		if (city == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_CITY);
		}
		if (city.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_CITY);
		}
		if (state == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_STATE);
		}
		if (state.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_STATE);
		}
	}

	private void demographicCheck(String gender, String country, String race, String ethnicity) {
		if (gender == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_GENDER);
		}
		if (gender.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_GENDER);
		}
		if (country == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_COUNTRY);
		}
		if (country.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_COUNTRY);
		}
		if (race == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_RACE);
		}
		if (race.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_RACE);
		}
		if (ethnicity == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_ETHNICITY);
		}
		if (ethnicity.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_ETHNICITY);
		}
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * Gets the date of birth.
	 *
	 * @return the date of birth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth.
	 *
	 * @param dateOfBirth the new date of birth
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Gets the address 1.
	 *
	 * @return the address 1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * Sets the address 1.
	 *
	 * @param address1 the new address 1
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * Gets the address 2.
	 *
	 * @return the address 2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * Sets the address 2.
	 *
	 * @param address2 the new address 2
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the race.
	 *
	 * @return the race
	 */
	public String getRace() {
		return race;
	}

	/**
	 * Sets the race.
	 *
	 * @param race the new race
	 */
	public void setRace(String race) {
		this.race = race;
	}

	/**
	 * Gets the ethnicity.
	 *
	 * @return the ethnicity
	 */
	public String getEthnicity() {
		return ethnicity;
	}

	/**
	 * Sets the ethnicity.
	 *
	 * @param ethnicity the new ethnicity
	 */
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
}
