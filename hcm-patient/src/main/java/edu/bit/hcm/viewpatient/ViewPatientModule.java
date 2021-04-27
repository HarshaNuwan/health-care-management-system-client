package edu.bit.hcm.viewpatient;

import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.framework.service.Module;

public class ViewPatientModule implements Module {

	private Controller viewPatientController;
	private String moduleName, moduleTitle;

	public ViewPatientModule() {
		this.viewPatientController = new ViewPatientController();
	}

	@Override
	public Controller getController() {
		return this.viewPatientController;
	}

	@Override
	public void setController(Controller controller) {
		this.viewPatientController = controller;
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
