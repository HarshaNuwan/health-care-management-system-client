package edu.bit.hcm.patientregistration;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import edu.bit.hcm.PatientDTO;
import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.wrapper.PatientDTOListWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PatientRegistrationController implements Controller, Initializable {
	private FXMLLoader loader;

	@FXML
	private Button btnPatRegNew;

	@FXML
	private TableView<Patient> tblPatients;

	@FXML
	private TableColumn<Patient, String> clmPatientId, clmPatientName, clmNicNumber;

	final ObservableList<Patient> tableData = FXCollections.observableArrayList();

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
		
		((PatientRegistrationDialogController) controller.getLoader().getController())
		.setParentController(this);;
		
		childStage.initModality(Modality.APPLICATION_MODAL);

		childStage.initStyle(StageStyle.DECORATED);
		childStage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		clmPatientId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().patientId));
		clmPatientName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPatientName()));
		clmNicNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nicNumber));
		
		loadPatientsTable();
	}
	
	@FXML
	public void onPatientsTableClicked(MouseEvent event) throws IOException {
		if (event.getClickCount() == 2 && tblPatients.getSelectionModel().getSelectedItem() != null) {
			Patient patient = tblPatients.getSelectionModel().getSelectedItem();

			Stage childStage = new Stage();

			PatientRegistrationDialogController dialogController = new PatientRegistrationDialogController();
			childStage.setScene(dialogController.getScene());

			((PatientRegistrationDialogController) dialogController.getLoader().getController())
			.loadSelectedPatient(patient.getPatientDTO(), this);

			childStage.setTitle("Patient Registration");

			childStage.initModality(Modality.APPLICATION_MODAL);

			childStage.initStyle(StageStyle.DECORATED);
			childStage.show();

		}
	}
	
	@FXML
	public void loadPatientsTable() {
		PatientDetailsRegistrationAPIConnector apiConnector = new PatientDetailsRegistrationAPIConnector();
		
		tableData.clear();
		
		try {
		 PatientDTOListWrapper patientDTOListWrapper = apiConnector.getAllSpecialization();
		 
		 for(PatientDTO patientDTO : patientDTOListWrapper.getList()) {
			 Patient patient = new Patient();
			 patient.setPatientId(String.valueOf(patientDTO.getPid()));
			 patient.setPatientName(patientDTO.getFirstName() + " " + patientDTO.getLastName());
			 patient.setNicNumber(patientDTO.getNic());
			 patient.setPatientDTO(patientDTO);
			 tableData.add(patient);
		 }
		 
		 tblPatients.setItems(tableData);
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
	
	@FXML
	public void closeDialog(Event event) {
		Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
		stage.close();
	}

	private class Patient {
		private String patientId, patientName, nicNumber;
		private PatientDTO patientDTO;

		public Patient() {
			// TODO Auto-generated constructor stub
		}

		public String getPatientId() {
			return patientId;
		}

		public void setPatientId(String patientId) {
			this.patientId = patientId;
		}

		public String getPatientName() {
			return patientName;
		}

		public void setPatientName(String patientName) {
			this.patientName = patientName;
		}

		public String getNicNumber() {
			return nicNumber;
		}

		public void setNicNumber(String nicNumber) {
			this.nicNumber = nicNumber;
		}

		public PatientDTO getPatientDTO() {
			return patientDTO;
		}

		public void setPatientDTO(PatientDTO patientDTO) {
			this.patientDTO = patientDTO;
		}

	}

}
