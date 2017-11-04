package fr.mrfern.spongeplugintest.config;

import java.io.File;
import java.util.HashMap;

import org.slf4j.Logger;

import fr.mrfern.spongeplugintest.Main;

public abstract class PluginConfig {
	
	@SuppressWarnings("unused")
	private static Main main;
	private static Logger logger;
	private static HashMap<String, String> fileList = new HashMap<>();
	
	private static PluginConfig instance = new PluginConfig() {};
	
	public void addConfig(String name, String path) {
		fileList.put(name, path);
	}
	
	public void setup() {
		
	}
	
	public boolean baseConfig_Exist(String name, String path) {
		
		File configFolder = new File(path);
		if(!configFolder.exists()) {
			configFolder.mkdirs();
			logger.info("Création de " + path );
		}
		
		return true;
	}

	public static void setMain(Main main) {
		PluginConfig.main = main;
		setLogger(main.getLogger());
	}
	
	public static void setLogger(Logger logger) {
		PluginConfig.logger = logger;
	}
	
	public static PluginConfig getInstance() {
		return instance;
	}

	public static HashMap<String, String> getFileList() {
		return fileList;
	}
	
	public static void setFileList(HashMap<String, String> fileList) {
		PluginConfig.fileList = fileList;
	}

}
