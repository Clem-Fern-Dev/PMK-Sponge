package fr.mrfern.spongeplugintest.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import fr.mrfern.spongeplugintest.Main;

public abstract class PluginConfig {
	
	@SuppressWarnings("unused")
	private static Main main;
	private static Logger logger;
	private static HashMap<String, String> fileList = new HashMap<>();
	
	public static void addConfig(String name, String path) {
		fileList.put(name, path);			// Ajout d'un fichier de config à la map
	}
	
	public void setup() {
		
		if(!fileList.isEmpty()) {
			
			logger.info("List des models de fichier de configuration non vide");
			
			for (Map.Entry<String, String> entry : fileList.entrySet()) {
				String name = entry.getKey(), path = entry.getValue();
				
				logger.info("Vérification : "+ path + entry.getKey());
				baseConfig_Exist( name , path , true);				
			}
			
		}else {
			logger.info("List des models de fichier de configuration vide");
		}		
	}
	
	private boolean baseConfig_Exist(String name, String path,boolean b) {
		
		// Test path
		File configFile = new File(path + name);
		
		if(!configFile.exists()) {
			configFile.mkdirs();
			logger.warn("Création de " + path + name);
		}else {
			logger.info(path + name + " existe déjà");
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

	public static HashMap<String, String> getFileList() {
		return fileList;
	}
	
	public static void setFileList(HashMap<String, String> fileList) {
		PluginConfig.fileList = fileList;
	}

}
