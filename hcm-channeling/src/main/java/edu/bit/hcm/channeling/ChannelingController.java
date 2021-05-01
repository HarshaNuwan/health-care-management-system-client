package edu.bit.hcm.channeling;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import edu.bit.hcm.ChannelingDTO;
import edu.bit.hcm.DoctorDTO;
import edu.bit.hcm.SpecializationDTO;
import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.wrapper.ChannelingDTOWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChannelingController implements Controller, Initializable {

	private FXMLLoader loader;

	@FXML
	private ComboBox<Specialization> cmbChannelSpecial;

	@FXML
	private ComboBox<Doctor> cmbDoctor;

	@FXML
	DatePicker datChannelDate;

	@FXML
	private TableView<ChannelAppointment> tblChannelings;

	@FXML
	private TableColumn<ChannelAppointment, String> colChannelingId, colDate, colTime, colAppointmentId;

	@FXML
	private Button btnChannelNew;

	final ObservableList<ChannelAppointment> tableData = FXCollections.observableArrayList();

	@Override
	public Scene getScene() throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/channeling/channeling_list.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}

	@FXML
	public void refreshChannelingList()
			throws ParseException, JsonGenerationException, JsonMappingException, IOException {
		ChannelingConnector channelingConnector = new ChannelingConnector();
		ChannelingDTO channelingDTO = new ChannelingDTO();

		LocalDate localDate = datChannelDate.getValue();
		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));

		channelingDTO.setDate(new SimpleDateFormat("yyyy-MM-dd")
				.parse(new SimpleDateFormat("yyyy-MM-dd").format(Date.from(instant))));
		channelingDTO.setDoctorId(cmbDoctor.getSelectionModel().getSelectedItem().doctorId);
		ChannelingDTOWrapper channelingDTOWrapper = channelingConnector
				.getChannelingDetailsByDateAndDoctorId(channelingDTO);

		System.out.println(datChannelDate.getValue() + " " + cmbDoctor.getSelectionModel().getSelectedItem().doctorId);

		tableData.clear();

		for (ChannelingDTO dto : channelingDTOWrapper.getList()) {
			ChannelAppointment appointment = new ChannelAppointment();
			appointment.setChannelingId(new SimpleStringProperty(String.valueOf(dto.getChannelingId())));
			appointment.setDate(new SimpleStringProperty(new SimpleDateFormat("yyyy-MM-yy").format(dto.getDate())));
			appointment.setTime(new SimpleStringProperty(dto.getTime()));
			appointment.setAppointmentNo(new SimpleStringProperty(String.valueOf(dto.getAppointmentNo())));

			tableData.add(appointment);

		}

		tblChannelings.setItems(tableData);

	}

	@FXML
	public void showBookAppointmentDialog() throws IOException {

		Stage childStage = new Stage();

		BookAppointmentController appointmentController = new BookAppointmentController();
		childStage.setTitle("Book Appointment");
		childStage.setScene(appointmentController.getScene());

		BookAppointmentController controller = ((BookAppointmentController) appointmentController.getLoader().getController());
				
		controller.setDoctorId(cmbDoctor.getSelectionModel().getSelectedItem().doctorId);
		
		controller.setDoctorName(cmbDoctor.getSelectionModel().getSelectedItem().doctorName);
		
		controller.setParentController(this);

		LocalDate localDate = datChannelDate.getValue();
		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		Date appointmentDate = Date.from(instant);
		
		controller.setDate(appointmentDate);

		childStage.initModality(Modality.APPLICATION_MODAL);

		childStage.initStyle(StageStyle.DECORATED);
		childStage.show();
	}

	private class ChannelAppointment {
		private StringProperty channelingId;
		private StringProperty date;
		private StringProperty time;
		private StringProperty appointmentNo;

		public ChannelAppointment() {
			// TODO Auto-generated constructor stub
		}

		public StringProperty getChannelingId() {
			return channelingId;
		}

		public void setChannelingId(StringProperty channelingId) {
			this.channelingId = channelingId;
		}

		public StringProperty getDate() {
			return date;
		}

		public void setDate(StringProperty date) {
			this.date = date;
		}

		public StringProperty getTime() {
			return time;
		}

		public void setTime(StringProperty time) {
			this.time = time;
		}

		public StringProperty getAppointmentNo() {
			return appointmentNo;
		}

		public void setAppointmentNo(StringProperty appointmentNo) {
			this.appointmentNo = appointmentNo;
		}

	}

	private class Specialization {
		private int specializationCode;
		private String specializationName;

		public int getSpecializationCode() {
			return specializationCode;
		}

		public void setSpecializationCode(int specializationCode) {
			this.specializationCode = specializationCode;
		}

		public String getSpecializationName() {
			return specializationName;
		}

		public void setSpecializationName(String specializationName) {
			this.specializationName = specializationName;
		}

		public Specialization() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.specializationName;
		}

	}

	private class Doctor {
		private int doctorId;
		private String doctorName;

		public Doctor() {
			// TODO Auto-generated constructor stub
		}

		public int getDoctorId() {
			return doctorId;
		}

		public void setDoctorId(int doctorId) {
			this.doctorId = doctorId;
		}

		public String getDoctorName() {
			return doctorName;
		}

		public void setDoctorName(String doctorName) {
			this.doctorName = doctorName;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.doctorName;
		}
	}

	@FXML
	public void onSpecializtionChanged(ActionEvent event) {
		ChannelingConnector connector = new ChannelingConnector();
		cmbDoctor.getItems().clear();
		try {
			List<DoctorDTO> doctorDTOs = connector.getDoctorsBySpecializationCode(
					cmbChannelSpecial.getSelectionModel().getSelectedItem().specializationCode).getDoctors();

			for (DoctorDTO doctorDTO : doctorDTOs) {
				Doctor doctor = new Doctor();
				doctor.setDoctorId(doctorDTO.getDoctorId());
				doctor.setDoctorName(doctorDTO.getFirstName() + " " + doctorDTO.getLastName());

				cmbDoctor.getItems().add(doctor);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadSpecialization();

		colChannelingId.setCellValueFactory(cellData -> cellData.getValue().channelingId);
		colDate.setCellValueFactory(cellData -> cellData.getValue().date);
		colTime.setCellValueFactory(cellData -> cellData.getValue().time);
		colAppointmentId.setCellValueFactory(cellData -> cellData.getValue().appointmentNo);

	}

	private void loadSpecialization() {
		SpecializationConnector connector = new SpecializationConnector();
		try {
			List<SpecializationDTO> specializationDTOs = connector.getAllSpecialization().getSpecializationLists();
			for (SpecializationDTO dto : specializationDTOs) {
				Specialization specialization = new Specialization();
				specialization.setSpecializationCode(dto.getSpecializationCode());
				specialization.setSpecializationName(dto.getSpecializationName());
				cmbChannelSpecial.getItems().add(specialization);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
