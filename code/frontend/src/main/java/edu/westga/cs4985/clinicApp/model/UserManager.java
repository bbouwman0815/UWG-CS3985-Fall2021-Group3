package edu.westga.cs4985.clinicApp.model;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.utils.DataReader;
import edu.westga.cs4985.clinicApp.utils.DataWriter;

/**
 * The UserManager class.
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class UserManager {
	
	public static UserManager userManager;
	public Communicator communicator;
	
	public UserManager() {
		this.communicator = new Communicator();
	}
	
	public User login(String username, String password) {
		String requestData = DataWriter.getUserLoginInfo(username, password);
		String reply = this.communicator.request(RequestType.USER_LOGIN, requestData);
		if (reply.equals("ERROR")) {
			return null;
		}
		return DataReader.convertToUser(reply);
	}
	
	public static void setUserManager(UserManager userManager) {
		UserManager.userManager = userManager;
	}
	
	public User getUserByUserName(String userName) {
		String request = DataWriter.getUserName(userName);
		String reply = this.communicator.request(RequestType.GET_USER_BY_USERNAME, request);
		if (reply.equals("ERROR")) {
			return null;
		}
		return DataReader.convertToUser(reply);
	}
	
	public List<Appointment> getAppointments(String userName){
		String request = DataWriter.getUserName(userName);
		String reply = this.communicator.request(RequestType.GET_APPOINTMENTS, request);
		if (reply.equals("ERROR")) {
			return new ArrayList<Appointment>();
		}
		return DataReader.convertToAppointments(reply);
	}
	public boolean bookAppointment(Appointment appointment) {
		String requestData = DataWriter.writeAppointmentInfo(appointment);
		String reply = this.communicator.request(RequestType.BOOK_APPOINTMENT, requestData);
		if (reply.equals("ERROR")) {
			return false;
		}
		return true;
	}
	
	public boolean cancelAppointment(Appointment appointment) {
		String requestData = DataWriter.writeAppointmentInfo(appointment);
		String reply = this.communicator.request(RequestType.CANCLE_APPOINTMENT, requestData);
		if (reply.equals("ERROR")) {
			return false;
		}
		return true;
	}
	

}
