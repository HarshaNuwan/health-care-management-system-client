package test.tt;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class TestTwoController {
	FXMLLoader loader = new FXMLLoader();
	
	private Button button;

	public TestTwoController() {
		
	}

	public Scene getScene() throws IOException {
		loader.setLocation(getClass().getResource("/fxml/TestTwo.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}
	
	public AnchorPane getAnchorPane() throws IOException {
		loader.setLocation(getClass().getResource("/fxml/TestTwo.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return anchorPane;
	}
	
	public AnchorPane getAnchorPane2() throws IOException {
		loader.setLocation(getClass().getResource("/fxml/Patient Registration.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return anchorPane;
	}
	
	public AnchorPane getAnchorPane3() throws IOException {
		loader.setLocation(getClass().getResource("/fxml/Doctor Registration.fxml"));
		AnchorPane anchorPane = loader.<AnchorPane>load();
		return anchorPane;
	}
	


}
