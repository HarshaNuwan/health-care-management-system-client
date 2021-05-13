package edu.bit.hcm.laboratorytesting;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import edu.bit.hcm.DiagnosisDTO;
import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.wrapper.DiagnosisDTOListWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LaboratoryTestingController implements Controller, Initializable {

	private FXMLLoader loader;

	@FXML
	private DatePicker datePicker;

	@FXML
	private TableView<LabTestingTableRecord> tblPendingLabtestings;

	final ObservableList<LabTestingTableRecord> tableData = FXCollections.observableArrayList();

	@FXML
	private TableColumn<LabTestingTableRecord, String> clmPID, clmPatientName, clmPrescriptionID, cmlStatus;

	@Override
	public Scene getScene() throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/laboratory-testing/laboratary_testing.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		clmPID.setCellValueFactory(cellData -> cellData.getValue().pId);
		clmPatientName.setCellValueFactory(cellData -> cellData.getValue().patientName);
		clmPrescriptionID.setCellValueFactory(cellData -> cellData.getValue().prescriptionId);
		cmlStatus.setCellValueFactory(cellData -> cellData.getValue().status);

		LocalDate localDate = datePicker.getValue();

		if (localDate != null) {
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			loadLabTableRecords(new SimpleDateFormat("yyyy-MM-dd").format(Date.from(instant)));
		} else {
			loadLabTableRecords(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		}

		tblPendingLabtestings.setOnMouseClicked(event -> {

			if (event.getClickCount() == 2 && tblPendingLabtestings.getSelectionModel().getSelectedItem() != null) {
				LabTestingTableRecord labTestingTableRecord = tblPendingLabtestings.getSelectionModel()
						.getSelectedItem();

				LabTestDialogController labTestDialogController = new LabTestDialogController();

				Stage childStage = new Stage();
				childStage.setTitle("Diagnostic Card | PID :" + labTestingTableRecord.getpId().get() + " |  Name :"
						+ labTestingTableRecord.getPatientName().get());

				try {
					childStage.setScene(labTestDialogController.getScene());
				} catch (IOException e) {
					e.printStackTrace();
				}

				((LabTestDialogController) labTestDialogController.getLoader().getController())
						.loadConsultationDialogData(labTestingTableRecord.getDiagnosisDTO());
				((LabTestDialogController) labTestDialogController.getLoader().getController())
				.setParentController(this);

				childStage.setMaximized(false);
				childStage.setResizable(false);

				childStage.initModality(Modality.APPLICATION_MODAL);

				childStage.initStyle(StageStyle.DECORATED);
				childStage.show();
			}
		});
	}

	public void loadLabTableRecords(String date) {
		LaboratoryTestingAPIConnector apiConnector = new LaboratoryTestingAPIConnector();
		tableData.clear();
		try {
			DiagnosisDTOListWrapper dtoListWrapper = apiConnector.getDiagnosisDetaislByDate(date);

			List<DiagnosisDTO> diagnosisDTOs = dtoListWrapper.getList();
			for (DiagnosisDTO diagnosisDTO : diagnosisDTOs) {
				LabTestingTableRecord labTestingTableRecord = new LabTestingTableRecord();
				labTestingTableRecord.setpId(new SimpleStringProperty(String.valueOf(diagnosisDTO.getPid())));
				labTestingTableRecord
						.setPatientName(new SimpleStringProperty(diagnosisDTO.getPatientDTO().getFirstName() + " "
								+ diagnosisDTO.getPatientDTO().getLastName()));
				labTestingTableRecord
						.setPrescriptionId(new SimpleStringProperty(String.valueOf(diagnosisDTO.getDiagnosisId())));
				labTestingTableRecord
						.setStatus(new SimpleStringProperty(diagnosisDTO.isReportStatus() ? "Issued" : "Pending"));
				labTestingTableRecord.setDiagnosisDTO(diagnosisDTO);
				tableData.add(labTestingTableRecord);
			}
			tblPendingLabtestings.setItems(tableData);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void btnRefreshClicked() {
		LocalDate localDate = datePicker.getValue();

		if (localDate != null) {
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			loadLabTableRecords(new SimpleDateFormat("yyyy-MM-dd").format(Date.from(instant)));
		} else {
			loadLabTableRecords(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		}
	}

	private class LabTestingTableRecord {
		private SimpleStringProperty pId, patientName, prescriptionId, status;
		private DiagnosisDTO diagnosisDTO;

		public SimpleStringProperty getpId() {
			return pId;
		}

		public void setpId(SimpleStringProperty pId) {
			this.pId = pId;
		}

		public SimpleStringProperty getPatientName() {
			return patientName;
		}

		public void setPatientName(SimpleStringProperty patientName) {
			this.patientName = patientName;
		}

		public SimpleStringProperty getPrescriptionId() {
			return prescriptionId;
		}

		public void setPrescriptionId(SimpleStringProperty prescriptionId) {
			this.prescriptionId = prescriptionId;
		}

		public SimpleStringProperty getStatus() {
			return status;
		}

		public void setStatus(SimpleStringProperty status) {
			this.status = status;
		}

		public void setDiagnosisDTO(DiagnosisDTO diagnosisDTO) {
			this.diagnosisDTO = diagnosisDTO;
		}

		public DiagnosisDTO getDiagnosisDTO() {
			return diagnosisDTO;
		}

	}
}
