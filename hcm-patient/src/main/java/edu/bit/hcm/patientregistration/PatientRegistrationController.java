package edu.bit.hcm.patientregistration;

import java.io.IOException;

import edu.bit.hcm.framework.service.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PatientRegistrationController implements Controller {
	private FXMLLoader loader;
	
	@FXML
	private Button btnPatRegNew; 

	@Override
	public Scene getScene() throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/patient-registration/patient_registration.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}
	
	
	@FXML
	public void showPatientRegistrationDialog() throws IOException {
		PatientRegistrationDialogController controller = new PatientRegistrationDialogController();
		
		Stage childStage = new Stage();
		
		childStage.setTitle("Patient Registration");
		childStage.setScene(controller.getScene());
		childStage.initModality(Modality.APPLICATION_MODAL);
		
		childStage.initStyle(StageStyle.DECORATED);
		childStage.show();
	}
	

}
