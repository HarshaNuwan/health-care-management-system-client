package edu.bit.hcm.patientregistration;

import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.framework.service.Module;

public class PatientRegistrationModel implements Module {

	private Controller patientRegistrationController;
	private String moduleName, moduleTitle;

	public PatientRegistrationModel() {
		this.patientRegistrationController = new PatientRegistrationController();
	}

	@Override
	public Controller getController() {
		return this.patientRegistrationController;
	}

	@Override
	public void setController(Controller controller) {
		this.patientRegistrationController = controller;
	}

	@Override
	public String getModuleName() {
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
