package edu.bit.hcm.dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import edu.bit.hcm.core.DashboardService;
import edu.bit.hcm.framework.module.ModuleFactory;
import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.framework.service.Module;
import edu.bit.hcm.framework.service.ModuleType;
import edu.bit.hcm.framework.util.LoggedUser;
import edu.bit.hcm.framework.util.StageMap;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class DashboardController implements Initializable, Controller, DashboardService {

	private Map<String, Tab> tabs = new HashMap<String, Tab>();

	private FXMLLoader loader;

	@FXML
	private BorderPane dashboardBorderPane;

	@FXML
	private TabPane dashboardTabPane;

	@FXML
	private Label lbl_username;
	
	private boolean isLeftMenuOpen;

	private DashboardLeftPaneController dashboardLeftPaneController;

	public DashboardController() {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/dashboard/dashboard.fxml"));

		dashboardLeftPaneController = new DashboardLeftPaneController();

	}

	public void showHideLeftMenu(ActionEvent event) throws IOException {

		if (!isLeftMenuOpen) {
			dashboardBorderPane.setLeft(dashboardLeftPaneController.getLeftPane());
			isLeftMenuOpen = true;
		} else {
			dashboardBorderPane.setLeft(null);
			isLeftMenuOpen = false;
		}
	}

	@Override
	public Scene getScene() throws IOException {
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}

	public void loadPatientRegistraionPanel(ActionEvent event) throws IOException {
		/*
		 * TestTwoController t2c = new TestTwoController(); Tab tab = new
		 * Tab("Patient Registration"); tab.setContent(t2c.getAnchorPane2());
		 * dashboardTabPane.getTabs().add(tab);
		 * dashboardTabPane.getSelectionModel().select(tab);
		 */
	}

	/*
	 * public void loadSomeThing(ActionEvent event) throws IOException {
	 * TestTwoController t2c = new TestTwoController(); Tab tab = new Tab("Sample");
	 * tab.setContent(t2c.getAnchorPane()); dashboardTabPane.getTabs().add(tab);
	 * dashboardTabPane.getSelectionModel().select(tab); }
	 */

	public void doctorRegistration(ActionEvent event) {
		try {
			addTab(ModuleType.DOCTOR_REGISTRATION_MODULE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void closeTab(Tab tab, ModuleType moduleType) {
		EventHandler<Event> handler = tab.getOnClosed();
		if (null != handler) {
			handler.handle(null);
		} else {
			dashboardTabPane.getTabs().remove(tab);
			tabs.remove(moduleType.getModuleName());
		}
	}

	public void patientRegistration(ActionEvent event) throws IOException {
		try {
			addTab(ModuleType.PATIENT_REGISTRATION_MODULE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void laboratoryTesting(ActionEvent event) throws IOException {
		try {
			addTab(ModuleType.LABORATORY_TESTING_MODULE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void viewPrescription(ActionEvent event) throws IOException {
		try {
			addTab(ModuleType.VIEW_PRESCRIPTION_MODULE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void loadUserManagementPanel(ActionEvent event) {
		try {
			addTab(ModuleType.USER_MANAGEMENT_MODULE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void loadAppointmentsPanel(ActionEvent event) {
		try {
			addTab(ModuleType.DOCTOR_APPOINTMENT_MODULE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void loadChannelingPanel(ActionEvent event) {
		try {
			addTab(ModuleType.CHANNELING_MODULE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addTab(ModuleType moduleType) throws IOException {
		Tab t = null;
		if (null != tabs.get(moduleType.getModuleName())) {
			t = tabs.get(moduleType.getModuleName());
		} else {

			Module module = ModuleFactory.getInstance().getModule(moduleType);
			t = new Tab(module.getModuleTitle());
			closeTab(t, moduleType);
			ScrollPane scrollPane = new ScrollPane();
			AnchorPane anchorPane = (AnchorPane)module.getController().getScene().getRoot();
			anchorPane.prefHeightProperty().bind(scrollPane.heightProperty());
			anchorPane.prefWidthProperty().bind(scrollPane.widthProperty());
			scrollPane.setContent(anchorPane);
			t.setContent(scrollPane);
			tabs.put(moduleType.getModuleName(), t);
		}

		if (dashboardTabPane.getTabs().indexOf(t) == -1) {
			dashboardTabPane.getTabs().add(t);
		}
		dashboardTabPane.getSelectionModel().select(t);
		
		System.out.println(dashboardTabPane.getTabs());
	}
	

	@FXML
	void logOut(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("Are you sure you need to logout?");
		alert.showAndWait().ifPresent(rs -> {
			if (rs == ButtonType.OK) {
				StageMap.getInstance().getStage("MAIN_STAGE").hide();
				StageMap.getInstance().getStage("PRIMARY_STAGE").show();
				//clear JWT 
				LoggedUser.getInstance().logout();
				
			} else if (rs == ButtonType.CANCEL) {
				alert.close();
			}
		});
	}

	@Override
	public void addTab(String modulename, Tab tab) {
		this.tabs.put(modulename, tab);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lbl_username.setText("User : "+LoggedUser.getInstance().getUserName());
	}

}
