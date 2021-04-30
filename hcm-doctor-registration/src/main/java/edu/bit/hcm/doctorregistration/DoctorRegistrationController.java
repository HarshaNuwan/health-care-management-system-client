package edu.bit.hcm.doctorregistration;

import java.io.IOException;

import edu.bit.hcm.framework.service.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DoctorRegistrationController implements Controller {

	private FXMLLoader loader;

	@Override
	public Scene getScene() throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/doctor-registration/doctor_registration.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}
	
	
	@FXML
	public void openDoctorRegistrationDialog() throws IOException {
		Stage childStage = new Stage();
		
		DoctorRegistrationDialogController dialogController = new DoctorRegistrationDialogController();
		
		childStage.setTitle("Doctor Registration");
		childStage.setScene(dialogController.getScene());
		childStage.initModality(Modality.APPLICATION_MODAL);
		
		childStage.initStyle(StageStyle.DECORATED);
		childStage.show();
	}
	


}
