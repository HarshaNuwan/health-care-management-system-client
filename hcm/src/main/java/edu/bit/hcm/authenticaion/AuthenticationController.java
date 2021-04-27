package edu.bit.hcm.authenticaion;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import edu.bit.hcm.core.DashboardService;
import edu.bit.hcm.framework.module.ModuleFactory;
import edu.bit.hcm.framework.service.Controller;
import edu.bit.hcm.framework.service.Module;
import edu.bit.hcm.framework.service.ModuleType;
import edu.bit.hcm.framework.util.StageMap;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class AuthenticationController implements Initializable, Controller {

	private FXMLLoader loader;

	@FXML
	private Button login_button;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Label lbl_username;

	private AnchorPane anchorPane;

	public AuthenticationController() throws IOException {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/authentication/login_screen.fxml"));

	}

	public void setUserName() {
		username.setText("Test");
	}

	@FXML
	public void exit(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("Are you sure you need to exit?");
		alert.showAndWait().ifPresent(rs -> {
			if (rs == ButtonType.OK) {
				Platform.exit();
			} else if (rs == ButtonType.CANCEL) {
				alert.close();
			}
		});

	}

	@FXML
	private void loginButtonAction(ActionEvent event) throws IOException {

		if (login(username.getText(), password.getText())) {
			Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
			stage.hide();

			Module dashboardModule = ModuleFactory.getInstance().getModule(ModuleType.DASHBOARD_MODULE);

			/*
			 * if( null != StageMap.getInstance().getStage("MAIN_STAGE")) {
			 * StageMap.getInstance().removeStage("MAIN_STAGE"); }
			 */

			Stage mainStage = new Stage();
			// if there is an instance of MAIN)_STAGE in the StageMap already; it will be
			// replaced by the newly created stage above.
			StageMap.getInstance().putStage("MAIN_STAGE", mainStage);
			mainStage.setTitle("Health Care Management System - Western Medical Centre");
			mainStage.setScene(dashboardModule.getController().getScene());

			Module module = ModuleFactory.getInstance().getModule(ModuleType.DOCTOR_REGISTRATION_MODULE);
			Tab tab = new Tab(module.getModuleTitle());
			tab.setContent(module.getController().getScene().getRoot());
			((DashboardService) dashboardModule.getController()).addTab(dashboardModule.getModuleName(), tab);

			mainStage.initStyle(StageStyle.DECORATED);
			mainStage.setMaximized(true);

			// handle the window closing event when user clicks the cross button for exit
			// the application
			mainStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::handleExit);

			mainStage.show();
			resetScreen();
		}

	}

	private void resetScreen() {
		username.clear();
		password.clear();
	}

	private boolean login(String userName, String password) {
		AuthenticationConnector authenticationConnector = new AuthenticationConnector();
		try {
			if (authenticationConnector.doLogin(userName, password)) {

				return true;
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Authentication Failed");
				alert.setHeaderText("Username or Password is invalid");
				alert.showAndWait().ifPresent(rs -> {
					if (rs == ButtonType.OK) {
						alert.close();
					}
				});
			}
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	private void handleExit(WindowEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("Are you sure you need to exit?");
		alert.showAndWait().ifPresent(rs -> {
			if (rs == ButtonType.OK) {
				Platform.exit();
			} else if (rs == ButtonType.CANCEL) {
				event.consume();
				alert.close();
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@Override
	public Scene getScene() throws IOException {

		anchorPane = loader.<AnchorPane>load();
		// ((AuthenticationController)loader.getController()).setUserName();
		return new Scene(anchorPane);
	}
}
