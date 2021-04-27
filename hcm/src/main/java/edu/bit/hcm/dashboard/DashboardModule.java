package edu.bit.hcm.dashboard;

import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.framework.service.Module;

public class DashboardModule implements Module {

	private Controller conroller;
	private String moduleName;

	public DashboardModule() {
		if (null == this.conroller) {
			this.conroller = new DashboardController();
		}
	}

	@Override
	public Controller getController() {

		return conroller;
	}

	@Override
	public void setController(Controller controller) {
		this.conroller = controller;
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
		return null;
	}

	@Override
	public void setModuleTitle(String moduleTitle) {
		// TODO Auto-generated method stub
		
	}

}
