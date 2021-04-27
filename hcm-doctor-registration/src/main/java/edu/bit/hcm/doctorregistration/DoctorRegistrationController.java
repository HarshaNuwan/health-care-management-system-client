package edu.bit.hcm.doctorregistration;

import java.io.IOException;

import edu.bit.hcm.framework.service.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class DoctorRegistrationController implements Controller {

	private FXMLLoader loader;

	@Override
	public Scene getScene() throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/doctor-registration/doctor_registration.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}

}
