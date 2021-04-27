package edu.bit.hcm.viewprescription;

import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.framework.service.Module;

public class ViewPrescriptionModule implements Module {

	private Controller viewPrescriptionController;
	private String moduleName, moduleTitle;

	public ViewPrescriptionModule() {
		this.viewPrescriptionController = new ViewPrescriptionController();
	}

	@Override
	public Controller getController() {
		// TODO Auto-generated method stub
		return this.viewPrescriptionController;
	}

	@Override
	public void setController(Controller controller) {
		this.viewPrescriptionController = controller;
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
