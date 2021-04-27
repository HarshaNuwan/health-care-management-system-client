package edu.bit.hcm.patientregistration;

import java.io.IOException;

import edu.bit.hcm.framework.service.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class PatientRegistrationController implements Controller {
	private FXMLLoader loader;

	@Override
	public Scene getScene() throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/patient-registration/patient_registration.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}

}
