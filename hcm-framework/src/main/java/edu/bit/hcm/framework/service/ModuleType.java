package edu.bit.hcm.framework.service;

public enum ModuleType {
	AUTHENTICATION_MODULE("authentication.module", null), 
	DASHBOARD_MODULE("dashboard", null), 
	DOCTOR_REGISTRATION_MODULE("doctor.registration", "Doctor Registration"),
	PATIENT_REGISTRATION_MODULE("patient.registration", "Patient Registration"), 
	LABORATORY_TESTING_MODULE("laboratory.testing", "Laboratory Testing"), 
	VIEW_PATIENT_MODULE("view.patient", "View Patient"), 
	PRESCRIPTION_MODULE("prescription", "Prescription"), 
	VIEW_PRESCRIPTION_MODULE("view.prescription", "View Lab Tests"),
	USER_MANAGEMENT_MODULE("user.management", "User Management"),
	DOCTOR_APPOINTMENT_MODULE("doctor.appointment", "Appointment");
	
	
	private String moduleName, moduleTitle;
	
	private ModuleType(String moduleName, String moduleTitle) {
		this.moduleName = moduleName;
		this.moduleTitle = moduleTitle;
	}
	
	public String getModuleName() {
		return moduleName;
	}
	
	public String getModuleTitle() {
		return moduleTitle;
	}
}
