package edu.westga.cs4985.clinicApp.client;

/**
 * An Enum of request types used in the Tutor Scheduler Application 
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public enum RequestType {
	UPDATE_GENERAL_INFORMATION,
	GET_USER_BY_USERNAME,
	USER_LOGIN,
	GET_AVAILABILITIES,
	GET_APPOINTMENTS,
	BOOK_APPOINTMENT,
	CANCLE_APPOINTMENT,
	UPDATE_NOTES,
	ADD_AVAILABILITY,
	ADD_PATIENT,
	ADD_MEDICAL_CONDITION,
	REMOVE_AVAILABILITY;
}
