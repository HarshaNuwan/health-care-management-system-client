package edu.bit.hcm.core;

import edu.bit.hcm.framework.module.ModuleFactory;
import edu.bit.hcm.framework.service.Module;
import edu.bit.hcm.framework.service.ModuleType;
import edu.bit.hcm.framework.util.StageMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Module authenticatorModule = ModuleFactory.getInstance().getModule(ModuleType.AUTHENTICATION_MODULE);

			Platform.setImplicitExit(false);

			StageMap.getInstance().putStage("PRIMARY_STAGE", primaryStage);
			// primaryStage.setOnHidden(event -> Platform.exit());
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(authenticatorModule.getController().getScene());
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
