package edu.bit.hcm.patientregistration;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.omg.CORBA.INITIALIZE;

import edu.bit.hcm.DoctorDTO;
import edu.bit.hcm.PatientDTO;
import edu.bit.hcm.framework.service.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.TextField;

public class PatientRegistrationDialogController implements Controller, Initializable {

	private FXMLLoader loader;

	@FXML
	private TextField txtNicNumber;
	@FXML
	private ComboBox<PatientTitle> cmbTitle;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private DatePicker dtpDateofBirth;
	@FXML
	private TextField txtAge;
	@FXML
	private TextField txtAddressLine1;
	@FXML
	private TextField txtAddressLine2;
	@FXML
	private TextField txtAddressLine3;
	@FXML
	private TextField txtMobile;
	@FXML
	private TextField txtTelephone;
	@FXML
	private TextField txtEmail;
	@FXML
	private ComboBox<PatientGender> cmbGender;
	@FXML
	private ComboBox<PatientBloodGroup> cmbBloodGroup;
	@FXML
	private TextField txtHeight;
	@FXML
	private TextField txtWeight;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cmbBloodGroup.getItems().add(new PatientBloodGroup(1, "A+"));
		cmbBloodGroup.getItems().add(new PatientBloodGroup(2, "A-"));
		cmbBloodGroup.getItems().add(new PatientBloodGroup(3, "B+"));
		cmbBloodGroup.getItems().add(new PatientBloodGroup(4, "B-"));
		cmbBloodGroup.getItems().add(new PatientBloodGroup(5, "AB+"));
		cmbBloodGroup.getItems().add(new PatientBloodGroup(6, "AB-"));
		cmbBloodGroup.getItems().add(new PatientBloodGroup(7, "O+"));
		cmbBloodGroup.getItems().add(new PatientBloodGroup(8, "O-"));

		cmbGender.getItems().add(new PatientGender(1, "Male"));
		cmbGender.getItems().add(new PatientGender(2, "Female"));
		cmbGender.getItems().add(new PatientGender(3, "Undefined"));

		cmbTitle.getItems().add(new PatientTitle(1, "Mr"));
		cmbTitle.getItems().add(new PatientTitle(2, "Mrs"));
		cmbTitle.getItems().add(new PatientTitle(3, "Ms"));
		cmbTitle.getItems().add(new PatientTitle(4, "Miss"));
		cmbTitle.getItems().add(new PatientTitle(5, "Dr"));
		cmbTitle.getItems().add(new PatientTitle(6, "Rev"));
		cmbTitle.getItems().add(new PatientTitle(7, "Ven"));

	}

	@Override
	public Scene getScene() throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/patient-registration/patient_registration_dialog.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}

	private class PatientTitle {
		private int titile_id;
		private String title_name;

		public int getTitile_id() {
			return titile_id;
		}

		public void setTitile_id(int titile_id) {
			this.titile_id = titile_id;
		}

		public String getTitle_name() {
			return title_name;
		}

		public void setTitle_name(String title_name) {
			this.title_name = title_name;
		}

		public PatientTitle(int titile_id, String title_name) {
			super();
			this.titile_id = titile_id;
			this.title_name = title_name;
		}

		@Override
		public String toString() {
			return this.title_name;
		}

	}

	private class PatientGender {
		private int genderCode;
		private String genderName;

		public PatientGender(int genderCode, String genderName) {
			super();
			this.genderCode = genderCode;
			this.genderName = genderName;
		}

		public int getGenderCode() {
			return genderCode;
		}

		public void setGenderCode(int genderCode) {
			this.genderCode = genderCode;
		}

		public String getGenderName() {
			return genderName;
		}

		public void setGenderName(String genderName) {
			this.genderName = genderName;
		}

		@Override
		public String toString() {
			return this.genderName;
		}
	}

	private class PatientBloodGroup {
		private int bloodGroupId;
		private String bloodGroupName;

		public PatientBloodGroup(int bloodGroupId, String bloodGroupName) {
			super();
			this.bloodGroupId = bloodGroupId;
			this.bloodGroupName = bloodGroupName;
		}

		public int getBloodGroupId() {
			return bloodGroupId;
		}

		public void setBloodGroupId(int bloodGroupId) {
			this.bloodGroupId = bloodGroupId;
		}

		public String getBloodGroupName() {
			return bloodGroupName;
		}

		public void setBloodGroupName(String bloodGroupName) {
			this.bloodGroupName = bloodGroupName;
		}

		@Override
		public String toString() {
			return bloodGroupName;
		}

	}

	@FXML
	public void savePatientDetails() {

		if (!validateForm()) {
			return;
		}

		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setPid(0);
		patientDTO.setNic(txtNicNumber.getText());
		if (cmbTitle.getSelectionModel().getSelectedIndex() == -1) {
			patientDTO.setTitleCode(0);
		} else {
			patientDTO.setTitleCode(cmbTitle.getSelectionModel().getSelectedItem().titile_id);
		}

		patientDTO.setFirstName(txtFirstName.getText());
		patientDTO.setLastName(txtLastName.getText());

		LocalDate localDate = dtpDateofBirth.getValue();
		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		patientDTO.setDob(Date.from(instant));

		patientDTO.setAddressLine1(txtAddressLine1.getText());
		patientDTO.setAddressLine2(txtAddressLine2.getText());
		patientDTO.setAddressLine3(txtAddressLine3.getText());
		patientDTO.setMobile(txtMobile.getText());
		patientDTO.setTelephone(txtTelephone.getText());
		patientDTO.setGenderCode(cmbGender.getSelectionModel().getSelectedItem().genderCode);
		if (cmbBloodGroup.getSelectionModel().getSelectedIndex() == -1) {
			patientDTO.setBloodGroupCode(0);
		} else {
			patientDTO.setBloodGroupCode(cmbBloodGroup.getSelectionModel().getSelectedItem().bloodGroupId);
		}
		patientDTO.setEmail(txtEmail.getText());
		patientDTO.setHeight(txtHeight.getText());
		patientDTO.setWeight(txtWeight.getText());

		PatientDetailsRegistrationAPIConnector patientDetailsRegistrationAPIConnector = new PatientDetailsRegistrationAPIConnector();

		try {
			patientDetailsRegistrationAPIConnector.createNewPatient(patientDTO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(patientDTO.getNic());

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

	private boolean validateForm() {

		String alertTitle = "Patient Details Form Validation";
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
		if (dtpDateofBirth.getValue() == null) {
			message.add("Date of Birth can't be empty!");
		}
		if (txtAddressLine1.getText().isEmpty()) {
			message.add("Address Line 1 can't be empty!");
		}
		if (txtAddressLine2.getText().isEmpty()) {
			message.add("Address Line 2 can't be empty!");
		}
		if (txtMobile.getText().isEmpty()) {
			message.add("Mobile number can't be empty!");
		}

		if (cmbGender.getSelectionModel().isSelected(-1)) {
			message.add("Please select the gender");
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

}
