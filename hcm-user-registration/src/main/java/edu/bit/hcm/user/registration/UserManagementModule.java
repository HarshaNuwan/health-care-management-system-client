package edu.bit.hcm.user.registration;

import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.framework.service.Module;

public class UserManagementModule implements Module {

	private Controller userManagementController;
	private String moduleName;
	private String moduleTitle;

	public UserManagementModule() {
		this.userManagementController = new UserManagementController();
	}

	@Override
	public Controller getController() {
		return this.userManagementController;
	}

	@Override
	public void setController(Controller controller) {
		this.userManagementController = controller;
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
		return this.moduleTitle;
	}

	@Override
	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}

}
