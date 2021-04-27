package edu.bit.hcm.laboratorytesting;

import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.framework.service.Module;

public class LaboratoryTestingModule implements Module {

	private Controller laboratoryTestingController;
	private String moduleName;
	private String moduleTitle;

	public LaboratoryTestingModule() {
		this.laboratoryTestingController = new LaboratoryTestingController();
	}

	@Override
	public Controller getController() {
		// TODO Auto-generated method stub
		return this.laboratoryTestingController;
	}

	@Override
	public void setController(Controller controller) {
		this.laboratoryTestingController = controller;
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
