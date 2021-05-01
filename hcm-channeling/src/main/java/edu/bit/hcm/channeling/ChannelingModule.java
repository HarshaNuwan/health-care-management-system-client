package edu.bit.hcm.channeling;

import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.framework.service.Module;

public class ChannelingModule implements Module {

	private Controller channelingController;
	private String moduleName;
	private String moduleTitle;

	public ChannelingModule() {
		this.channelingController = new ChannelingController();
	}

	@Override
	public Controller getController() {
		return this.channelingController;
	}

	@Override
	public void setController(Controller controller) {
		this.channelingController = controller;
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
