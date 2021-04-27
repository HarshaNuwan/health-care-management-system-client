package edu.bit.hcm.dashboard;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class DashboardLeftPaneController {
	private FXMLLoader leftPaneLoader;

	private Pane dashboardLeftPane;

	public DashboardLeftPaneController() {
		leftPaneLoader = new FXMLLoader();
		leftPaneLoader.setLocation(getClass().getResource("/fxml/dashboard/dashboardLeftPane.fxml"));
	}

	public Pane getLeftPane() throws IOException {

		if (null == dashboardLeftPane) {
			dashboardLeftPane = leftPaneLoader.<Pane>load();
		}
		return dashboardLeftPane;
	}
	
	public void testButton(ActionEvent event) {
		System.out.println(":D");
	}
}
