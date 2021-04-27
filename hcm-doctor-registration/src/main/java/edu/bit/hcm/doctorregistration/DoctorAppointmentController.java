package edu.bit.hcm.doctorregistration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.bit.hcm.framework.service.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	
	

	public DoctorAppointmentController() {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/appointment/appointments.fxml"));

	}

	private class Appointment {
		private StringProperty clmPID, clmPatientName, clmAge, clmGender, clmReason;

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

		tableData.add(new Appointment("PID0003322", "N. Peries", "35", "Male", "Fever"));
		tableData.add(new Appointment("PID0033442", "Kamal Kumara", "25", "Male", "Sore throat"));
		tableData.add(new Appointment("PID0004433", "Nuwani Perera", "30", "Female", "Fever"));
		System.out.println(tableData.size());
		tblAppoinment.setItems(tableData);
		
		tblAppoinment.setOnMouseClicked(event ->{
			
			if(event.getClickCount() ==2 && tblAppoinment.getSelectionModel().getSelectedItem() != null) {
				Appointment patient = tblAppoinment.getSelectionModel().getSelectedItem();
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				
				stage.setTitle("Diagnostic Card | PID :" + patient.getClmPID().get() + " |  Name :" + patient.getClmPatientName().get());
				
				stage.setMaximized(true);
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/fxml/consultation/consultation.fxml"));
				AnchorPane anchorPane;
				try {
					anchorPane = loader.<AnchorPane>load();
					Scene scene = new Scene(anchorPane);
					stage.setScene(scene);
					stage.showAndWait();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}


}
