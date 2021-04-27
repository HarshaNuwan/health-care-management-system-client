package edu.bit.hcm.authenticaion;

import java.io.IOException;

import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.framework.service.Module;

public class AuthenticationModule implements Module {

	private Controller controller;
	private String moduleName;
	
	public AuthenticationModule() {
		try {
			controller = new AuthenticationController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Controller getController() {
		// TODO Auto-generated method stub
		return controller;
	}

	@Override
	public void setController(Controller controller) {
		this.controller = controller;

	}

	@Override
	public String getModuleName() {
		// TODO Auto-generated method stub
		return moduleName;
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
