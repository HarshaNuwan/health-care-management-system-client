package edu.bit.hcm.framework.service;

public interface Module {

	
	public Controller getController();
	public void setController(Controller controller);
	public String getModuleName();
	public void setModuleName(String moduleName);
	public String getModuleTitle();
	public void setModuleTitle(String moduleTitle);
	
}
