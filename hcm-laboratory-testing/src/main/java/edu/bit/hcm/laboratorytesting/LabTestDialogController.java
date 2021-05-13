package edu.bit.hcm.laboratorytesting;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.EventObject;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import edu.bit.hcm.DiagnosisDTO;
import edu.bit.hcm.PatientDTO;
import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.framework.util.AgeCalculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LabTestDialogController implements Controller {

	private FXMLLoader loader;
	private int diagnosisId;
	private LaboratoryTestingController parentController;
	private String date;

	@FXML
	private TextField txtNic, txtTitle, txtFirstName, txtLastName, txtDOB, txtAge, txtAddressLine1, txtAddressLine2,
			txtAddressLine3, txtMobileNo, txtTelephoneNo, txtEmail, txtGender, txtBloodGroup, txtHeight, txtWeight;

	@FXML
	private TextArea txtDiagnosis, txtLabTests;

	@Override
	public Scene getScene() throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/laboratory-testing/lab_test_dialog.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}

	public FXMLLoader getLoader() {
		return loader;
	}
	
	@FXML
	public void closeDialog(ActionEvent event) {
		Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void updateLabTestRecord(ActionEvent event) {
		LaboratoryTestingAPIConnector apiConnector = new LaboratoryTestingAPIConnector();
		
		try {
			apiConnector.updateLabTestRecord(this.diagnosisId);
			this.parentController.loadLabTableRecords(date);
			closeDialog(event);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadConsultationDialogData(DiagnosisDTO diagnosisDTO) {
		PatientDTO patientDTO = diagnosisDTO.getPatientDTO();
		
		if(diagnosisDTO != null) {
			txtDiagnosis.setText(diagnosisDTO.getDiagnosis());
			txtLabTests.setText(diagnosisDTO.getReports());
		}
		
		if (patientDTO != null) {
			txtNic.setText(patientDTO.getNic());
			txtTitle.setText(titleCodeConverter(patientDTO.getTitleCode()));
			txtFirstName.setText(patientDTO.getFirstName());
			txtLastName.setText(patientDTO.getLastName());
			txtAge.setText(patientDTO.getDob() != null
					? String.valueOf(AgeCalculator.calculateAge(patientDTO.getDob()).getYears())
					: "-");
			txtDOB.setText(
					patientDTO.getDob() != null ? new SimpleDateFormat("yyyy-MM-dd").format(patientDTO.getDob()) : "-");
			txtAddressLine1.setText(patientDTO.getAddressLine1());
			txtAddressLine2.setText(patientDTO.getAddressLine2());
			txtAddressLine3.setText(patientDTO.getAddressLine3());
			txtMobileNo.setText(patientDTO.getMobile());
			txtTelephoneNo.setText(patientDTO.getTelephone());
			txtEmail.setText(patientDTO.getEmail());
			txtGender.setText(patientDTO.getGenderCode() == 1 ? "Male" : "Female");
			txtBloodGroup.setText(patientDTO.getBloodGroupCode() + " ");
			txtHeight.setText(patientDTO.getHeight());
			txtWeight.setText(patientDTO.getWeight());

			 this.diagnosisId = diagnosisDTO.getDiagnosisId();
			 this.date = new SimpleDateFormat("yyyy-MM-dd").format(diagnosisDTO.getDate());

		}

	}
	
	public void setParentController(LaboratoryTestingController laboratoryTestingController) {
		this.parentController = laboratoryTestingController;
	}

	private String titleCodeConverter(int titleCode) {
		switch (titleCode) {
		case 1:
			return "Mr.";
		case 2:
			return "Mrs.";
		case 3:
			return "Rev.";
		case 4:
			return "Miss";

		default:
			return null;
		}
	}

}
