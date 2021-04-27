package edu.bit.hcm.framework.module;

import edu.bit.hcm.authenticaion.AuthenticationModule;
import edu.bit.hcm.dashboard.DashboardModule;
import edu.bit.hcm.doctorregistration.DoctorAppointmentModule;
import edu.bit.hcm.doctorregistration.DoctorRegistrationModule;
import edu.bit.hcm.framework.service.Module;
import edu.bit.hcm.framework.service.ModuleType;
import edu.bit.hcm.laboratorytesting.LaboratoryTestingModule;
import edu.bit.hcm.patientregistration.PatientRegistrationModel;
import edu.bit.hcm.prescription.PrescriptionModule;
import edu.bit.hcm.user.registration.UserManagementModule;
import edu.bit.hcm.viewpatient.ViewPatientModule;
import edu.bit.hcm.viewprescription.ViewPrescriptionModule;

/**
 * 
 * @author Harsha Hulangamuwa
 * 
 * !Important Note
 * This factory should be modified to load modules from a configuration file and load modules dynamically 
 * rather than hard coded module names form the ModuleType enum
 *
 */

public class ModuleFactory {

	private static ModuleFactory instance;

	private ModuleFactory() {
	}

	public static ModuleFactory getInstance() {
		if (null == instance) {
			instance = new ModuleFactory();
		}

		return instance;
	}

	public Module getModule(ModuleType moduleType) {

		Module module = null;

		switch (moduleType) {
		case AUTHENTICATION_MODULE:
			module = new AuthenticationModule();
			initializeModule(module, moduleType);
			break;
		case DASHBOARD_MODULE:
			module = new DashboardModule();
			initializeModule(module, moduleType);
			break;
		case DOCTOR_REGISTRATION_MODULE:
			module = new DoctorRegistrationModule();
			initializeModule(module, moduleType);
			break;
		case PATIENT_REGISTRATION_MODULE:
			module = new PatientRegistrationModel();
			initializeModule(module, moduleType);
			break;
		case LABORATORY_TESTING_MODULE:
			module = new LaboratoryTestingModule();
			initializeModule(module, moduleType);
			break;
		case VIEW_PATIENT_MODULE:
			module = new ViewPatientModule();
			initializeModule(module, moduleType);
			break;
		case PRESCRIPTION_MODULE:
			module = new PrescriptionModule();
			initializeModule(module, moduleType);
			break;
		case VIEW_PRESCRIPTION_MODULE:
			module = new ViewPrescriptionModule();
			initializeModule(module, moduleType);
			break;
		case USER_MANAGEMENT_MODULE:
			module = new UserManagementModule();
			initializeModule(module, moduleType);
			break;
		case DOCTOR_APPOINTMENT_MODULE:
			module = new DoctorAppointmentModule();
			initializeModule(module, moduleType);
			break;
		default:
			break;
		}

		return module;
	}
	
	private void initializeModule(Module module, ModuleType moduleType) {
		module.setModuleName(moduleType.getModuleName());
		module.setModuleTitle(moduleType.getModuleTitle());
	}
}
