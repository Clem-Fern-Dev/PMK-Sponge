package fr.mrfern.spongeplugintest.config;

import java.io.File;

import org.slf4j.Logger;

import fr.mrfern.spongeplugintest.Main;

public abstract class PluginConfig {
	
	@SuppressWarnings("unused")
	private static Main main;
	private static Logger logger;
	private static PluginConfig instance = new PluginConfig() {
	};
	
	public boolean baseConfig_Exist(String path) {
		
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

}
