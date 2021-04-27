package edu.bit.hcm.doctorregistration;

import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.framework.service.Module;

public class DoctorRegistrationModule implements Module {

	private Controller doctorRegistrationController;
	private String moduleName;
	private String moduleTitle;
	
	public DoctorRegistrationModule() {
		this.doctorRegistrationController = new DoctorRegistrationController();
	}

	@Override
	public Controller getController() {
		return this.doctorRegistrationController;
	}

	@Override
	public void setController(Controller controller) {
		this.doctorRegistrationController = controller;

	}

	@Override
	public String getModuleName() {
		// TODO Auto-generated method stub
		return this.moduleName;
	}

	@Override
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Override
	public String getModuleTitle() {
		// TODO Auto-generated method stub
		return this.moduleTitle;
	}

	@Override
	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}

	
}
