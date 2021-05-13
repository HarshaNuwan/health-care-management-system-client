package edu.bit.hcm.channeling;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventObject;
import java.util.ResourceBundle;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import edu.bit.hcm.ChannelingDTO;
import edu.bit.hcm.framework.service.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BookAppointmentController implements Controller, Initializable {

	private FXMLLoader loader;

	private ChannelingController parentController;

	private int doctorId;

	private Date appointmentDate;

	@FXML
	private Label lblToDoctor;

	@FXML
	private Label lblDate;

	@FXML
	private ComboBox<TimeAmPm> cmbAmPm;

	@FXML
	private Button btnSave;

	@FXML
	private TextField txtTime;

	@FXML
	private TextField txtPatientId;

	@FXML
	private TextArea txtReason;

	@Override
	public Scene getScene() throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/channeling/channeling.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}

	private class TimeAmPm {
		private String timeType;

		public TimeAmPm() {
			// TODO Auto-generated constructor stub
		}

		public TimeAmPm(String t) {
			this.timeType = t;
		}

		public String getTimeType() {
			return timeType;
		}

		public void setTimeType(String timeType) {
			this.timeType = timeType;
		}

		@Override
		public String toString() {
			return this.timeType;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cmbAmPm.getItems().add(new TimeAmPm("AM"));
		cmbAmPm.getItems().add(new TimeAmPm("PM"));
		cmbAmPm.getSelectionModel().select(0);
	}

	@FXML
	public void saveChannleBooking(ActionEvent event) {
		ChannelingDTO channelingDTO = new ChannelingDTO();
		channelingDTO.setChannelingId(0);
		channelingDTO.setDate(appointmentDate);
		channelingDTO.setTime(txtTime.getText() + " " + cmbAmPm.getSelectionModel().getSelectedItem().timeType);
		channelingDTO.setDoctorId(doctorId);
		channelingDTO.setPid(Integer.valueOf(txtPatientId.getText()));
		channelingDTO.setReason(txtReason.getText());

		ChannelingConnector channelingConnector = new ChannelingConnector();

		try {
			String response = channelingConnector.saveChannleBooking(channelingDTO);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(alert.getTitle());
			alert.setHeaderText(null);
			alert.setContentText(response.substring(12, 49));
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					clearForm();
					alert.close();
					Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
					stage.close();
				}

			});

			parentController.refreshChannelingList();
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void clearForm() {
		txtReason.clear();
		txtTime.clear();
		cmbAmPm.getSelectionModel().select(0);
		txtPatientId.clear();

	}

	public FXMLLoader getLoader() {
		return loader;
	}

	public void setParentController(ChannelingController parentController) {
		this.parentController = parentController;
	}

	public void setDate(Date date) {
		lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
		this.appointmentDate = date;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public void setDoctorName(String dName) {
		lblToDoctor.setText(dName);
	}

}
