package edu.bit.hcm.framework.util;

import java.util.HashMap;
import java.util.Map;

import javafx.stage.Stage;

public class StageMap {

	private static StageMap instance;

	private Map<String, Stage> stageMap = new HashMap<String, Stage>();

	private StageMap() {
		// TODO Auto-generated constructor stub
	}

	public static StageMap getInstance() {
		if (null == instance) {
			instance = new StageMap();
		}
		return instance;
	}

	public void putStage(String key, Stage stage) {
		stageMap.put(key, stage);
	}
	
	public void removeStage(String key) {
		stageMap.remove(key);
	}

	public Stage getStage(String key) {
		return stageMap.get(key);
	}
}
