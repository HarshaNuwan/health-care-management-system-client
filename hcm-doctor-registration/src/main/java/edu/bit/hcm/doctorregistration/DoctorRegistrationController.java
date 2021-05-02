package edu.bit.hcm.doctorregistration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import edu.bit.hcm.DoctorDTO;
import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.wrapper.DoctorDTOListWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DoctorRegistrationController implements Controller, Initializable {

	private FXMLLoader loader;

	@FXML
	private TableView<DoctorTableRecord> tblDoctorTable;

	@FXML
	private TableColumn<DoctorTableRecord, String> clmDoctorId, clmDoctorName, clmSpecialization, clmMobileNumber,
			clmTelephone;

	@FXML
	private TextField txtDocRegSearch;

	final ObservableList<DoctorTableRecord> tableData = FXCollections.observableArrayList();

	@Override
	public Scene getScene() throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/doctor-registration/doctor_registration.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}

	@FXML
	public void openDoctorRegistrationDialog() throws IOException {
		Stage childStage = new Stage();

		DoctorRegistrationDialogController dialogController = new DoctorRegistrationDialogController();

		childStage.setTitle("Doctor Registration");
		childStage.setScene(dialogController.getScene());

		((DoctorRegistrationDialogController) dialogController.getLoader().getController()).setParentController(this);
		//((DoctorRegistrationDialogController) dialogController.getLoader().getController()).fillSpecializationCombobox();

		childStage.initModality(Modality.APPLICATION_MODAL);

		childStage.initStyle(StageStyle.DECORATED);
		childStage.show();
	}

	@FXML
	public void deleteSelectedDoctor() {

		if (tblDoctorTable.getSelectionModel().getSelectedItem() == null) {
			return;
		}

		Alert alert = new Alert(AlertType.WARNING, "Are you sure you need to delete selected doctor?",
				new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE),
				new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE));
		alert.setTitle("Delete Doctor");
		// alert.setHeaderText("Are you sure you need to delete selected doctor?");
		alert.showAndWait().ifPresent(rs -> {
			if (rs.getButtonData() == ButtonData.OK_DONE) {

				DoctorDetailsRegistrationAPIConnector apiConnector = new DoctorDetailsRegistrationAPIConnector();

				try {
					apiConnector.deleteDoctor(tblDoctorTable.getSelectionModel().getSelectedItem().getDoctorDTO());
					loadDoctorsIntoTable();
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

				alert.close();

			} else if (rs.getButtonData() == ButtonData.CANCEL_CLOSE) {
				alert.close();
			}

		});
	}

	@FXML
	public void onDoctorsTableClicked(MouseEvent event) throws IOException {
		if (event.getClickCount() == 2 && tblDoctorTable.getSelectionModel().getSelectedItem() != null) {
			DoctorTableRecord selectedUserAccount = tblDoctorTable.getSelectionModel().getSelectedItem();

			Stage childStage = new Stage();

			DoctorRegistrationDialogController dialogController = new DoctorRegistrationDialogController();
			childStage.setScene(dialogController.getScene());

			((DoctorRegistrationDialogController) dialogController.getLoader().getController())
					.loadSelectedDoctor(selectedUserAccount.doctorDTO, this);

			childStage.setTitle("Doctor Registration");

			childStage.initModality(Modality.APPLICATION_MODAL);

			childStage.initStyle(StageStyle.DECORATED);
			childStage.show();

		}
	}

	@FXML
	public void loadTableData() {
		loadDoctorsIntoTable();
	}

	private void loadDoctorsIntoTable() {
		tableData.clear();

		DoctorDetailsRegistrationAPIConnector apiConnector = new DoctorDetailsRegistrationAPIConnector();

		try {

			DoctorDTOListWrapper doctorsListWrapper = apiConnector.getAllDoctors();
			List<DoctorDTO> doctors = doctorsListWrapper.getDoctors();

			for (DoctorDTO doctorDTO : doctors) {
				DoctorTableRecord tableRecord = new DoctorTableRecord();
				tableRecord.setDoctorDTO(doctorDTO);
				tableRecord.setDoctorId(new SimpleStringProperty(String.valueOf(doctorDTO.getDoctorId())));
				tableRecord.setDoctorName(
						new SimpleStringProperty(doctorDTO.getFirstName() + " " + doctorDTO.getLastName()));
				tableRecord.setSpecialization(
						new SimpleStringProperty(doctorDTO.getSpecializationDTO().getSpecializationName()));
				tableRecord.setMobileNumber(new SimpleStringProperty(doctorDTO.getMobile()));
				tableRecord.setTelephone(new SimpleStringProperty(doctorDTO.getTelephone()));

				tableData.add(tableRecord);
			}

		} catch (JsonGenerationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		tblDoctorTable.setItems(tableData);
		
		FilteredList<DoctorTableRecord> filteredData = new FilteredList<>(tableData, p -> true);

		txtDocRegSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(doctor -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (doctor.getDoctorName().toString().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (doctor.getMobileNumber().toString().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				return false;
			});
		});

		SortedList<DoctorTableRecord> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(tblDoctorTable.comparatorProperty());

		tblDoctorTable.setItems(sortedData);
		
	}

	private class DoctorTableRecord {
		private StringProperty doctorId, doctorName, specialization, mobileNumber, telephone;
		private DoctorDTO doctorDTO;

		@SuppressWarnings("unused")
		public DoctorTableRecord() {
			// TODO Auto-generated constructor stub
		}

		@SuppressWarnings("unused")
		public DoctorTableRecord(StringProperty doctorId, StringProperty doctorName, StringProperty specialization,
				StringProperty mobileNumber, StringProperty telephone, DoctorDTO doctorDTO) {
			super();
			this.doctorId = doctorId;
			this.doctorName = doctorName;
			this.specialization = specialization;
			this.mobileNumber = mobileNumber;
			this.telephone = telephone;
			this.doctorDTO = doctorDTO;
		}

		public StringProperty getDoctorId() {
			return doctorId;
		}

		public void setDoctorId(StringProperty doctorId) {
			this.doctorId = doctorId;
		}

		public StringProperty getDoctorName() {
			return doctorName;
		}

		public void setDoctorName(StringProperty doctorName) {
			this.doctorName = doctorName;
		}

		public StringProperty getSpecialization() {
			return specialization;
		}

		public void setSpecialization(StringProperty specialization) {
			this.specialization = specialization;
		}

		public StringProperty getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(StringProperty mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public StringProperty getTelephone() {
			return telephone;
		}

		public void setTelephone(StringProperty telephone) {
			this.telephone = telephone;
		}

		public DoctorDTO getDoctorDTO() {
			return doctorDTO;
		}

		public void setDoctorDTO(DoctorDTO doctorDTO) {
			this.doctorDTO = doctorDTO;
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadDoctorsIntoTable();
		clmDoctorId.setCellValueFactory(cellData -> cellData.getValue().doctorId);
		clmDoctorName.setCellValueFactory(cellData -> cellData.getValue().doctorName);
		clmSpecialization.setCellValueFactory(cellData -> cellData.getValue().specialization);
		clmMobileNumber.setCellValueFactory(cellData -> cellData.getValue().mobileNumber);
		clmTelephone.setCellValueFactory(cellData -> cellData.getValue().telephone);

	}

}
