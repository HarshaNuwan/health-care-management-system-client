package edu.bit.hcm.framework.util;

public class LoggedUser {

	private static LoggedUser instance;
	private String userName, jwtToken;
	private int doctorId;
	private int userRoleId;
	
	
	private LoggedUser() {
		// TODO Auto-generated constructor stub
	}
	
	public static LoggedUser getInstance() {
		if(null != instance) {
			return instance;
		}else {
			instance = new LoggedUser();
			return instance;
		}
	}
	
	public String getJwtToken() {
		return jwtToken;
	}
	
	public LoggedUser setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
		return getInstance();
	}
	
	public String getUserName() {
		return userName;
	}
	
	public LoggedUser setUserName(String userName) {
		this.userName = userName;
		return getInstance();
	}

	public void logout() {
		this.jwtToken = null;
		this.userName = null;
		
	}
	
	public int getDoctorId() {
		return doctorId;
	}
	
	public LoggedUser setDoctorId(int doctorId) {
		this.doctorId = doctorId;
		return getInstance();
	}
	
	public int getUserRoleId() {
		return userRoleId;
	}
	
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.userName + " | did: " + this.doctorId + " | urid: " + this.userRoleId;
	}
}
