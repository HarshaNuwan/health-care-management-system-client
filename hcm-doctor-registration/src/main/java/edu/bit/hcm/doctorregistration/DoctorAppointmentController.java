package edu.bit.hcm.doctorregistration;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import edu.bit.hcm.ChannelingDTO;
import edu.bit.hcm.PatientDTO;
import edu.bit.hcm.framework.service.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DoctorAppointmentController implements Controller, Initializable {

	private FXMLLoader loader;

	@FXML
	private TableView<Appointment> tblAppoinment;

	@FXML
	private TableColumn<Appointment, String> clmPID;

	@FXML
	private TableColumn<Appointment, String> clmPatientName;

	@FXML
	private TableColumn<Appointment, String> clmAge;

	@FXML
	private TableColumn<Appointment, String> clmGender;

	@FXML
	private TableColumn<Appointment, String> clmReason;

	final ObservableList<Appointment> tableData = FXCollections.observableArrayList();

	// ====================================

	@FXML
	private Button btnRefresh;

	public DoctorAppointmentController() {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/appointment/appointments.fxml"));

	}

	private class Appointment {
		private StringProperty clmPID, clmPatientName, clmAge, clmGender, clmReason;
		private PatientDTO patientDTO;
		private int doctorId;
		private Date appointmentDate;

		@SuppressWarnings("unused")
		public Appointment(String clmPID, String clmPatientName, String clmAge, String clmGender, String clmReason) {
			super();
			this.clmPID = new SimpleStringProperty(clmPID);
			this.clmPatientName = new SimpleStringProperty(clmPatientName);
			this.clmAge = new SimpleStringProperty(clmAge);
			this.clmGender = new SimpleStringProperty(clmGender);
			this.clmReason = new SimpleStringProperty(clmReason);
		}

		public StringProperty getClmPID() {
			return clmPID;
		}

		public void setClmPID(String clmPID) {
			this.clmPID.set(clmPID);
		}

		public StringProperty getClmPatientName() {
			return clmPatientName;
		}

		public void setClmPatientName(String clmPatientID) {
			this.clmPatientName.set(clmPatientID);
		}

		public StringProperty getClmAge() {
			return clmAge;
		}

		public void setClmAge(String clmAge) {
			this.clmAge.set(clmAge);
		}

		public StringProperty getClmGender() {
			return clmGender;
		}

		public void setClmGender(String clmGender) {
			this.clmGender.set(clmGender);
		}

		public StringProperty getClmReason() {
			return clmReason;
		}

		public void setClmReason(String clmReason) {
			this.clmReason.set(clmReason);
		}

		public void setPatientDTO(PatientDTO patientDTO) {
			this.patientDTO = patientDTO;
		}

		public PatientDTO getPatientDTO() {
			return patientDTO;
		}
		
		public int getDoctorId() {
			return doctorId;
		}
		
		public void setDoctorId(int doctorId) {
			this.doctorId = doctorId;
		}
		
		public Date getAppointmentDate() {
			return appointmentDate;
		}
		
		public void setAppointmentDate(Date appointmentDate) {
			this.appointmentDate = appointmentDate;
		}

	}

	@Override
	public Scene getScene() throws IOException {
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clmPID.setCellValueFactory(cellData -> cellData.getValue().getClmPID());
		clmPatientName.setCellValueFactory(cellData -> cellData.getValue().getClmPatientName());
		clmAge.setCellValueFactory(cellData -> cellData.getValue().getClmAge());
		clmGender.setCellValueFactory(cellData -> cellData.getValue().getClmGender());
		clmReason.setCellValueFactory(cellData -> cellData.getValue().getClmReason());

		loadAppointments();

		tblAppoinment.setOnMouseClicked(event -> {

			if (event.getClickCount() == 2 && tblAppoinment.getSelectionModel().getSelectedItem() != null) {
				Appointment patient = tblAppoinment.getSelectionModel().getSelectedItem();

				Stage childStage = new Stage();

				DoctorConsultationDialogController dialogController = new DoctorConsultationDialogController();

				childStage.setTitle("Diagnostic Card | PID :" + patient.getClmPID().get() + " |  Name :"
						+ patient.getClmPatientName().get());
				try {
					childStage.setScene(dialogController.getScene());
				} catch (IOException e) {
					e.printStackTrace();
				}

				childStage.setMaximized(false);
				childStage.setResizable(false);

				((DoctorConsultationDialogController) dialogController.getLoader().getController())
				.setDoctorId(patient.getDoctorId());
				
				((DoctorConsultationDialogController) dialogController.getLoader().getController())
						.loadConsultationDialogData(patient.getPatientDTO());
				
				((DoctorConsultationDialogController) dialogController.getLoader().getController())
				.setDiagnosisDate(patient.getAppointmentDate());

				childStage.initModality(Modality.APPLICATION_MODAL);

				childStage.initStyle(StageStyle.DECORATED);
				childStage.show();
			}
		});

	}

	@FXML
	public void loadAppointments() {
		tableData.clear();
		DoctorDetailsRegistrationAPIConnector apiConnector = new DoctorDetailsRegistrationAPIConnector();
		try {
			List<ChannelingDTO> channelingDTOs = apiConnector.getChannelingDetailsByDateAndDoctorId(new Date(), 3)
					.getList();
			for (ChannelingDTO channelingDTO : channelingDTOs) {
				PatientDTO patientDTO = channelingDTO.getPatientDTO();
				Appointment appointment = null;

				if (patientDTO != null) {
					appointment = new Appointment(String.valueOf(channelingDTO.getPid()),
							patientDTO.getFirstName() + " " + patientDTO.getLastName(), "-",
							patientDTO.getGenderCode() == 1 ? "Male" : "Female", channelingDTO.getReason());
				} else {
					appointment = new Appointment(String.valueOf(channelingDTO.getPid()), "-", "-", "-",
							channelingDTO.getReason());
				}

				appointment.setAppointmentDate(channelingDTO.getDate());
				appointment.setDoctorId(channelingDTO.getDoctorId());
				appointment.setPatientDTO(patientDTO);
				tableData.add(appointment);
			}

			tblAppoinment.setItems(tableData);

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
