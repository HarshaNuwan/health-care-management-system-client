package edu.bit.hcm.framework.util;

public class LoggedUser {

	private static LoggedUser instance;
	private String userName, jwtToken;
	private int doctorId;
	
	
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
		return this.instance;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public LoggedUser setUserName(String userName) {
		this.userName = userName;
		return this.instance;
	}

	public void logout() {
		this.jwtToken = null;
		this.userName = null;
		
	}
	
	public int getDoctorId() {
		return doctorId;
	}
	
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
}
