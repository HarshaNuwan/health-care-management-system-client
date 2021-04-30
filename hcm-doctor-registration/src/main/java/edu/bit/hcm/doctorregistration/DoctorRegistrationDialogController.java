package edu.bit.hcm.doctorregistration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.ResourceBundle;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import edu.bit.hcm.DoctorDTO;
import edu.bit.hcm.framework.service.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DoctorRegistrationDialogController implements Controller, Initializable {
	
	private FXMLLoader loader;
	
	private DoctorDTO selectedDoctorDTO;
	
	private DoctorRegistrationController doctorRegistrationController;

	@FXML
	private TextField txtNicNumber;

	@FXML
	private TextField txtFirstName;

	@FXML
	private TextField txtLastName;

	@FXML
	private DatePicker dtmDateOfBirth;

	@FXML
	private TextField txtAddressLine1;

	@FXML
	private TextField txtAddressLine2;

	@FXML
	private TextField txtAddressLine3;

	@FXML
	private ComboBox<DoctorSpecialization> cmbSpecialization;

	@FXML
	private TextField txtMobileNumber;

	@FXML
	private TextField txtTelephoneNumber;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtRegisteredNumber;
	
	public DoctorRegistrationDialogController() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Scene getScene() throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/doctor-registration/doctor-details-dialog.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}
	
	public FXMLLoader getLoader() {
		return this.loader;
	}
	
	public void setParentController(DoctorRegistrationController doctorRegistrationController) {
		this.doctorRegistrationController = doctorRegistrationController;
	}
	
	public void loadSelectedDoctor(DoctorDTO doctorDTO, DoctorRegistrationController doctorRegistrationController) {
		this.selectedDoctorDTO = doctorDTO;
		this.doctorRegistrationController = doctorRegistrationController;
		
		txtNicNumber.setText(doctorDTO.getNic());
		txtFirstName.setText(doctorDTO.getFirstName());
		txtLastName.setText(doctorDTO.getLastName());
		
		if(doctorDTO.getDob() != null) {
			String dob = new SimpleDateFormat("yyyy-MM-dd").format(doctorDTO.getDob());
			LocalDate localDate = LocalDate.parse(dob);
			dtmDateOfBirth.setValue(localDate);
		}
		
		txtTelephoneNumber.setText(doctorDTO.getTelephone());
		txtMobileNumber.setText(doctorDTO.getMobile());
		txtAddressLine1.setText(doctorDTO.getAddressL1());
		txtAddressLine2.setText(doctorDTO.getAddressL2());
		txtAddressLine3.setText(doctorDTO.getAddressL3());
		txtEmail.setText(doctorDTO.getEmail());
		
		txtRegisteredNumber.setText(doctorDTO.getRegNo());
		
		
	}

	@FXML
	public void saveDoctorDetails(Event event) {
		
		if(!validateForm()) {
			return;
		}
		
		DoctorDTO doctorDTO = new DoctorDTO();
		if(this.selectedDoctorDTO != null) {
			doctorDTO.setDoctorId(this.selectedDoctorDTO.getDoctorId());
		}else {
			doctorDTO.setDoctorId(0);
		}
		
		doctorDTO.setFirstName(txtFirstName.getText());
		doctorDTO.setLastName(txtLastName.getText());

		LocalDate localDate = dtmDateOfBirth.getValue();
		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		doctorDTO.setDob(Date.from(instant));
		doctorDTO.setSpecializationCode(cmbSpecialization.getSelectionModel().getSelectedItem().specialization_code);

		doctorDTO.setAddressL1(txtAddressLine1.getText());
		doctorDTO.setAddressL2(txtAddressLine2.getText());
		doctorDTO.setAddressL3(txtAddressLine3.getText());

		doctorDTO.setNic(txtNicNumber.getText());
		doctorDTO.setMobile(txtMobileNumber.getText());
		doctorDTO.setTelephone(txtTelephoneNumber.getText());
		doctorDTO.setEmail(txtEmail.getText());
		doctorDTO.setRegNo(txtRegisteredNumber.getText());

		DoctorDetailsRegistrationAPIConnector apiConnector = new DoctorDetailsRegistrationAPIConnector();
		
		try {
			String response = apiConnector.createNewDoctor(doctorDTO);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(response);
			alert.setHeaderText(response);
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					clearForm();
					alert.close();
					Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
					stage.close();
					
					this.doctorRegistrationController.loadTableData();
					
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
	
	@FXML
	public void closeDialog(Event event) {
		Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
		stage.close();
	}

	private boolean validateForm() {

		String alertTitle = "Doctor Details Form Validation";
		List<String> message = new ArrayList<String>();

		if (txtNicNumber.getText().isEmpty()) {
			message.add("NIC number can't be empty!");
		}
		if (txtFirstName.getText().isEmpty()) {
			message.add("First Name can't be empty!");
		}
		if (txtLastName.getText().isEmpty()) {
			message.add("Last name can't be empty!");
		}
		if (dtmDateOfBirth.getValue() == null) {
			message.add("Date of Birth can't be empty!");
		}
		if (txtAddressLine1.getText().isEmpty()) {
			message.add("Address Line 1 can't be empty!");
		}
		if (txtAddressLine2.getText().isEmpty()) {
			message.add("Address Line 2 can't be empty!");
		}
		if (txtMobileNumber.getText().isEmpty()) {
			message.add("Mobile number can't be empty!");
		}
		if (txtTelephoneNumber.getText().isEmpty()) {
			message.add("Telephone number can't be empty!");
		}
		if (cmbSpecialization.getSelectionModel().isSelected(-1)) {
			message.add("Please select specialization");
		}
		if (txtRegisteredNumber.getText().isEmpty()) {
			message.add("Registration number can't be empty!");
		}

		if (message.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			for (String msg : message) {
				buffer.append(msg);
				buffer.append("\n");
			}

			if (buffer.length() > 0) {
				showAlert("Form Validation", buffer.toString(), AlertType.ERROR);
			}
			
			return false;
		}

		return true;
	}

	private void showAlert(String title, String header, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.showAndWait().ifPresent(rs -> {
			if (rs == ButtonType.OK) {
				alert.close();
			}

		});
	}

	@FXML
	public void clearForm() {

		txtNicNumber.clear();
		txtFirstName.clear();
		txtLastName.clear();
		dtmDateOfBirth.getEditor().clear();
		txtAddressLine1.clear();
		txtAddressLine2.clear();
		txtAddressLine3.clear();
		txtMobileNumber.clear();
		txtTelephoneNumber.clear();
		txtEmail.clear();
		cmbSpecialization.getEditor().clear();
		txtRegisteredNumber.clear();

	}

	private class DoctorSpecialization {
		private int specialization_code;
		private String specialization_name;

		public DoctorSpecialization() {
			// TODO Auto-generated constructor stub
		}

		public DoctorSpecialization(int specialization_code, String specialization_name) {
			super();
			this.specialization_code = specialization_code;
			this.specialization_name = specialization_name;
		}

		public int getSpecialization_code() {
			return specialization_code;
		}

		public void setSpecialization_code(int specialization_code) {
			this.specialization_code = specialization_code;
		}

		public String getSpecialization_name() {
			return specialization_name;
		}

		public void setSpecialization_name(String specialization_name) {
			this.specialization_name = specialization_name;
		}

		@Override
		public String toString() {
			return this.specialization_name;
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		addTextLimiter(txtNicNumber, 10);

		cmbSpecialization.getItems().add(new DoctorSpecialization(1, "Test 1"));
		cmbSpecialization.getItems().add(new DoctorSpecialization(2, "Test 2"));
		cmbSpecialization.getItems().add(new DoctorSpecialization(2, "Test 3"));
		
	}

	public static void addTextLimiter(final TextField tf, final int maxLength) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String oldValue,
					final String newValue) {
				if (tf.getText().length() > maxLength) {
					String s = tf.getText().substring(0, maxLength);
					tf.setText(s);
				}
			}
		});
	}

}
