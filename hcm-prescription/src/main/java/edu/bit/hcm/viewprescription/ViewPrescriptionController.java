package edu.bit.hcm.viewprescription;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import edu.bit.hcm.DiagnosisDTO;
import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.wrapper.DiagnosisDTOListWrapper;
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

public class ViewPrescriptionController implements Controller, Initializable {

	private FXMLLoader loader;

	@FXML
	private TableView<PendingPrescription> tblPendingPrescription;

	final ObservableList<PendingPrescription> tableData = FXCollections.observableArrayList();

	@FXML
	private TableColumn<PendingPrescription, String> clmPID;

	@FXML
	private TableColumn<PendingPrescription, String> clmPatientName;

	@FXML
	private TableColumn<PendingPrescription, String> clmPrescriptionID;

	@FXML
	private TableColumn<PendingPrescription, String> cmlStatus;

	private String fDate;

	@Override
	public Scene getScene() throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/view-prescription/view_prescription.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clmPID.setCellValueFactory(cellData -> cellData.getValue().getClmPID());
		clmPatientName.setCellValueFactory(cellData -> cellData.getValue().getClmPatientName());
		clmPrescriptionID.setCellValueFactory(cellData -> cellData.getValue().getClmPrescriptionID());
		cmlStatus.setCellValueFactory(cellData -> cellData.getValue().getCmlStatus());

		tableData.add(new PendingPrescription("PID0003342", "K. Kumara", "PRID003", "Pending"));
		tableData.add(new PendingPrescription("PID0003323", "Janet Perera", "PRID034", "Done"));
		tableData.add(new PendingPrescription("PID0003356", "Adam Kwong", "PRID045", "Pending"));

		tblPendingPrescription.setItems(tableData);

		/*
		 * tblPendingPrescription.setRowFactory(tv -> new
		 * TableRow<PendingPrescription>() {
		 * 
		 * @Override public void updateItem(PendingPrescription item, boolean empty) {
		 * // TODO Auto-generated method stub super.updateItem(item, empty); if (item ==
		 * null || empty) { setStyle(""); } else if (item.getCmlStatus().equals("Done"))
		 * { for(int i=0; i<getChildren().size();i++){ ((Labeled)
		 * getChildren().get(i)).setTextFill(Color.RED); ((Labeled)
		 * getChildren().get(i)).setStyle("-fx-background-color: yellow"); }
		 * //setStyle("-fx-background-color: #baffba;"); } } });
		 */

		tblPendingPrescription.setOnMouseClicked(event -> {

			if (event.getClickCount() == 2 && tblPendingPrescription.getSelectionModel().getSelectedItem() != null) {
				PendingPrescription patient = tblPendingPrescription.getSelectionModel().getSelectedItem();
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);

				stage.setTitle("Diagnostic Card | PID :" + patient.getClmPID().get() + " |  Name :"
						+ patient.getClmPatientName().get());

				stage.setMaximized(true);

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/fxml/view-prescription/consultation.fxml"));
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

	private class PendingPrescription {
		private StringProperty clmPID, clmPatientName, clmPrescriptionID, cmlStatus;
		
		public PendingPrescription() {
			// TODO Auto-generated constructor stub
		}

		public PendingPrescription(String clmPID, String clmPatientName, String clmPrescriptionID, String cmlStatus) {
			super();
			this.clmPID = new SimpleStringProperty(clmPID);
			this.clmPatientName = new SimpleStringProperty(clmPatientName);
			this.clmPrescriptionID = new SimpleStringProperty(clmPrescriptionID);
			this.cmlStatus = new SimpleStringProperty(cmlStatus);
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

		public void setClmPatientName(String clmPatientName) {
			this.clmPatientName.set(clmPatientName);
		}

		public StringProperty getClmPrescriptionID() {
			return clmPrescriptionID;
		}

		public void setClmPrescriptionID(String clmPrescriptionID) {
			this.clmPrescriptionID.set(clmPrescriptionID);
		}

		public void setCmlStatus(String cmlStatus) {
			this.cmlStatus.set(cmlStatus);
		}

		public StringProperty getCmlStatus() {
			return cmlStatus;
		}
	}

	private void loadPrescriptionDataforPharmacy() {
		ViewPrescriptionConnector apiConnector = new ViewPrescriptionConnector();
		tableData.clear();

		try {

			DiagnosisDTOListWrapper dtoListWrapper = apiConnector.getPrescriptionDetaislByDate(this.fDate);
			for (DiagnosisDTO diagnosisDTO : dtoListWrapper.getList()) {
				
				

				PendingPrescription prescription = new PendingPrescription();
				
				

				prescription.setClmPID(String.valueOf(diagnosisDTO.getPid()));
				prescription.setClmPatientName(diagnosisDTO.getReports());
				prescription.setClmPrescriptionID(String.valueOf(diagnosisDTO.getDiagnosisId()));
				prescription.setCmlStatus(String.valueOf(diagnosisDTO.isPrescriptionStatus()));

				
				tableData.add(prescription);
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

		tblPendingPrescription.setItems(tableData);
	}

}