package edu.bit.hcm.viewpatient;

import java.io.IOException;

import edu.bit.hcm.framework.service.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class ViewPatientController implements Controller{

	private FXMLLoader loader;

	@Override
	public Scene getScene() throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/view-patient/view_patient.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}

}
