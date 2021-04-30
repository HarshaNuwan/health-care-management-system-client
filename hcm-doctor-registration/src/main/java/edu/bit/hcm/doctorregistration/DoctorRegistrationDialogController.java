package edu.bit.hcm.doctorregistration;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import edu.bit.hcm.DoctorDTO;
import edu.bit.hcm.framework.service.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DoctorRegistrationDialogController implements Controller {
	
	private FXMLLoader loader;
	
	private TextField txtNicNumber;
	
	private TextField txtFirstName;
	
	private TextField txtLastName;

	@Override
	public Scene getScene() throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/doctor-registration/doctor-details-dialog.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}

	@FXML
	public void saveDoctorDetails() {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setDoctorId(0);
		doctorDTO.setFirstName(txtFirstName.getText());
		doctorDTO.setLastName(txtLastName.getText());
		doctorDTO.setSpecializationCode(1);
		doctorDTO.setNic(txtNicNumber.getText());
		
		DoctorDetailsRegistrationAPIConnector apiConnector = new DoctorDetailsRegistrationAPIConnector();
		try {
			String response = apiConnector.createNewDoctor(doctorDTO);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(response);
			alert.setHeaderText(response);
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					alert.close();
				} 
				
			});
			
			
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
