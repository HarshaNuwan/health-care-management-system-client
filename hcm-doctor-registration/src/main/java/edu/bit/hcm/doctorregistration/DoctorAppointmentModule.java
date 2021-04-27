package edu.bit.hcm.doctorregistration;

import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.framework.service.Module;

public class DoctorAppointmentModule implements Module {

	private Controller appointmentController;
	private String moduleName;
	private String moduleTitle;
	
	public DoctorAppointmentModule() {
		this.appointmentController = new DoctorAppointmentController();
	}

	@Override
	public Controller getController() {
		return this.appointmentController;
	}

	@Override
	public void setController(Controller controller) {
		this.appointmentController = controller;

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
