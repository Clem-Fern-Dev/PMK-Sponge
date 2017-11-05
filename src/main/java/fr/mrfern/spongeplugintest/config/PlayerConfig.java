package fr.mrfern.spongeplugintest.config;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import fr.mrfern.spongeplugintest.Main;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class PlayerConfig implements IConfig{

	protected String name = "player";
	protected String defaultPath = "./mods/plugins/spongeplugintest/";
	protected String playerPath = defaultPath + "player/";
	
	private boolean isSetup = false;
	
	private static PlayerConfig instance = new PlayerConfig();	
	
	private File defaultFileConfig;
	private ConfigurationLoader<CommentedConfigurationNode> cfgLoader;
	private ConfigurationNode loaderRootNode;
	private String pluginName;
	
	public void setup() {
		
		pluginName = Main.getPluginName();
		
		defaultFileConfig = new File(playerPath + name + IConfig.extensionFile);
		
		
			
		checkPath(playerPath, true);		
		if(checkFile(defaultFileConfig, true)) {
			cfgLoader = HoconConfigurationLoader.builder().setFile(defaultFileConfig).build();
			try {
				loaderRootNode = cfgLoader.load();
			} catch(IOException e) {
				e.printStackTrace();
			}
				
			set(cfgLoader,loaderRootNode); 
		}			
				
	}

	@Override
	public void set(ConfigurationLoader<CommentedConfigurationNode> cfgLoader, ConfigurationNode loaderRootNode) {
				
		loaderRootNode.getNode(pluginName,"bdd","backup-config").setValue(false);
		loaderRootNode.getNode(pluginName,"bdd","host").setValue("none");
		loaderRootNode.getNode(pluginName,"bdd","port").setValue(0);
		loaderRootNode.getNode(pluginName,"bdd","user").setValue("none");
		loaderRootNode.getNode(pluginName,"bdd","mdp").setValue("none");
		
		try {
			cfgLoader.save(loaderRootNode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static PlayerConfig getInstance() {
		return instance;
	}

	public boolean isSetup() {
		return isSetup;
	}

	public void setIsSetup(boolean isSetup) {
		this.isSetup = isSetup;
	}

	public ConfigurationNode getLoaderRootNode() {
		return loaderRootNode;
	}

	public ConfigurationLoader<CommentedConfigurationNode> getCfgLoader() {
		return cfgLoader;
	}

	@Override
	public boolean isActive() {
		return true;
	}
	
	public boolean createPlayerConfig(UUID uuid) {
		if(getPlayerConfigFile(uuid) != null) {
			// création du fichier
			File file = new File(pluginName + playerPath + uuid.toString() + extensionFile); 	// Instancie new file
			
			try {
				FileUtils.copyFile(defaultFileConfig, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return true;
		}
		return false;
	}
	
	public File getPlayerConfigFile(UUID uuid) {
		if(playerConfigExist(uuid)) {	// check si un fichier du nom de l'UUID existe
			return new File(pluginName + playerPath + uuid.toString() + extensionFile);		// Return la file du nom de l'UUID
		}
		return null;
	}
	
	public PlayerNode getPlayerConfigNode(UUID uuid) {
		if(playerConfigExist(uuid)) {			
			File file = new File(pluginName + playerPath + uuid.toString() + extensionFile); 	// Instancie new file		
			ConfigurationLoader<CommentedConfigurationNode> loader = builderConfigLoader(file);		// build file loader
			
			PlayerNode plyNode = new PlayerNode();	//load file / return loader
			plyNode.setCfgNode(loader);
			return plyNode;
			
		}
		return null;
	}
	
	public boolean playerConfigExist(UUID uuid) {
		File playerFile = new File(pluginName + playerPath + uuid.toString() + extensionFile);	// Instancie new file
		if(playerFile.exists()){	// check si file exist
			return true;
		}
		return false;
	}
	
}
