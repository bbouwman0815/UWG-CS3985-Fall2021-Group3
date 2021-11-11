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
	UPDATE_APPOINTMENT,
	GET_USER_BY_USERNAME,
	GET_USER_BY_MEDICAL_PERSONNEL_USERNAME,
	USER_LOGIN,
	GET_AVAILABILITIES,
	GET_APPOINTMENTS,
	GET_APPOINTMENTS_FOR_MEDICAL_PEROSNNEL,
	BOOK_APPOINTMENT,
	CANCLE_APPOINTMENT,
	UPDATE_NOTES,
	UPDATE_AVAILABILITY,
	ADD_PATIENT,
	ADD_MEDICAL_CONDITION,
	ADD_MEDICAL_PERSONNEL,
	REMOVE_MEDICAL_CONDITION,
	GET_MEDICAL_CONDITIONS,
	REMOVE_AVAILABILITY, 
	GET_ALL_PATIENTS,
	GET_ALL_MEDICAL_PERSONNELS,
	UPDATE_MEDICAL_PERSONNELS_PATIENTS,
	GET_CAREGIVER_BY_USER_NAME,
	GET_ALL_CAREGIVERS,
	GET_MEDICAL_PERSONNELS_PATIENTS;
}
