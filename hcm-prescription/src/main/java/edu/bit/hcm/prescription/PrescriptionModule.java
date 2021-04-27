package edu.bit.hcm.prescription;

import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.framework.service.Module;

public class PrescriptionModule implements Module {

	private Controller prescriptionController;
	private String moduleName, moduleTitle;

	public PrescriptionModule() {
		this.prescriptionController = new PrescriptionController();
	}

	@Override
	public Controller getController() {
		// TODO Auto-generated method stub
		return this.prescriptionController;
	}

	@Override
	public void setController(Controller controller) {
		this.prescriptionController = controller;
	}

	@Override
	public String getModuleName() {
		return moduleName;
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
