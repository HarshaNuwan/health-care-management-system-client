package edu.bit.hcm.doctorregistration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventObject;
import java.util.ResourceBundle;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import edu.bit.hcm.DiagnosisDTO;
import edu.bit.hcm.PatientDTO;
import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.framework.util.AgeCalculator;
import edu.bit.hcm.wrapper.DiagnosisDTOListWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DoctorConsultationDialogController implements Controller, Initializable {

	private FXMLLoader loader;

	private Date diagnosisDate;
	private int pid, doctorId;

	@FXML
	private TextField txtNic, txtTitle, txtFirstName, txtLastName, txtDOB, txtAge, txtAddressLine1, txtAddressLine2,
			txtAddressLine3, txtMobileNo, txtTelephoneNo, txtEmail, txtGender, txtBloodGroup, txtHeight, txtWeight;

	@FXML
	private TextArea txtDiagnosis, txtPrescription, txtLabTests;

	@FXML
	private Button btnClose;

	@FXML
	private Button btnClear;

	@FXML
	private Button btnSave;

	@FXML
	private TableView<Diagnosis> tblDiagnosis;

	@FXML
	private TableColumn<Diagnosis, String> clmDate, clmDiagnosis;

	final ObservableList<Diagnosis> tableData = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		clmDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().date));
		clmDiagnosis.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().diagnosis));

		tblDiagnosis.setOnMouseClicked(event -> {

			if (event.getClickCount() == 2 && tblDiagnosis.getSelectionModel().getSelectedItem() != null) {
				Diagnosis diagnosis = tblDiagnosis.getSelectionModel().getSelectedItem();
				txtDiagnosis.setText(diagnosis.getDiagnosisDTO().getDiagnosis());
				txtPrescription.setText(diagnosis.getDiagnosisDTO().getPrescription());
				txtLabTests.setText(diagnosis.getDiagnosisDTO().getReports());
			}
		});

	}

	private void loadDiagnosisDetails() {
		DoctorDetailsRegistrationAPIConnector apiConnector = new DoctorDetailsRegistrationAPIConnector();
		tableData.clear();

		try {

			DiagnosisDTOListWrapper dtoListWrapper = apiConnector.getDiagnosisDetaislByPidAndDoctorId(this.pid,
					this.doctorId);
			for (DiagnosisDTO diagnosisDTO : dtoListWrapper.getList()) {
				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setDate(diagnosisDTO.getDate() != null
						? new SimpleDateFormat("yyyy-MM-dd").format(diagnosisDTO.getDate())
						: "-");
				diagnosis.setDiagnosis(diagnosisDTO.getDiagnosis());
				diagnosis.setDiagnosisDTO(diagnosisDTO);
				tableData.add(diagnosis);
			}

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

		tblDiagnosis.setItems(tableData);
	}

	@FXML
	public void saveDiagnosisData(ActionEvent event) {
		DoctorDetailsRegistrationAPIConnector apiConnector = new DoctorDetailsRegistrationAPIConnector();

		DiagnosisDTO diagnosisDTO = new DiagnosisDTO();
		diagnosisDTO.setDate(diagnosisDate);
		diagnosisDTO.setDiagnosis(txtDiagnosis.getText());
		diagnosisDTO.setPrescription(txtPrescription.getText());
		diagnosisDTO.setReports(txtLabTests.getText());
		diagnosisDTO.setDoctorId(this.doctorId);
		diagnosisDTO.setPid(this.pid);

		try {
			apiConnector.createNewDiagnosis(diagnosisDTO);
			clearForm(event);
			closeDialog(event);
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

	@Override
	public Scene getScene() throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/consultation/consultation.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}

	public void loadConsultationDialogData(PatientDTO patientDTO) {
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

			this.pid = patientDTO.getPid();

			loadDiagnosisDetails();
		}

	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}

	@FXML
	public void closeDialog(ActionEvent event) {
		Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
		stage.close();
	}

	@FXML
	public void clearForm(ActionEvent event) {
		txtDiagnosis.clear();
		txtPrescription.clear();
		txtLabTests.clear();
	}

	public FXMLLoader getLoader() {
		return loader;
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

	private class Diagnosis {
		private String date, diagnosis;
		private DiagnosisDTO diagnosisDTO;

		public Diagnosis() {
			// TODO Auto-generated constructor stub
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getDiagnosis() {
			return diagnosis;
		}

		public void setDiagnosis(String diagnosis) {
			this.diagnosis = diagnosis;
		}

		public void setDiagnosisDTO(DiagnosisDTO diagnosisDTO) {
			this.diagnosisDTO = diagnosisDTO;
		}

		public DiagnosisDTO getDiagnosisDTO() {
			return diagnosisDTO;
		}

	}

}
