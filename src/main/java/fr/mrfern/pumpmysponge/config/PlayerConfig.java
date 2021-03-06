package fr.mrfern.pumpmysponge.config;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import fr.mrfern.pumpmysponge.Main;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class PlayerConfig implements IConfig{

	protected String name = "player";
	private String pluginName = Main.getPluginName();
	protected String defaultPath = "mods/plugins/"+ pluginName + "/";
	protected String playerPath = defaultPath + "player/";
	
	private boolean isSetup = false;
	
	private static PlayerConfig instance = new PlayerConfig();	
	
	private File defaultFileConfig;
	private ConfigurationLoader<CommentedConfigurationNode> cfgLoader;
	private ConfigurationNode loaderRootNode;
	
	@Deprecated
	public ConfigurationNode loadConfigNode(ConfigurationLoader<CommentedConfigurationNode> cfgConfigBuilderResult) {
		// TODO Auto-generated method stub
		return IConfig.super.loadConfigNode(cfgConfigBuilderResult);
	}
	
	public void setup() {
		
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
				
		loaderRootNode.getNode(name,"UUID").setValue("none");
		loaderRootNode.getNode(name,"name").setValue("none");
		
		//loaderRootNode.getNode(name,"chunkOwner").setValue(Arrays.asList("none"));
		
		loaderRootNode.getNode(name,"IP").setValue("0.0.0.0 /./ 0/0/0 0:0 AM");
		loaderRootNode.getNode(name,"IP-list").setValue(Arrays.asList("0.0.0.0 /./ 0/0/0 0:0 AM"));		
		
		loaderRootNode.getNode(name,"forum","profile-link").setValue("none");
		loaderRootNode.getNode(name,"forum","name").setValue("none");
		loaderRootNode.getNode(name,"forum","isBan").setValue(false);
		
		loaderRootNode.getNode(name,"grade","prim-grade").setValue("none");
		loaderRootNode.getNode(name,"grade","sub-grade").setValue(Arrays.asList("none"));
		
		loaderRootNode.getNode(name,"ban","enable").setValue(false);
		loaderRootNode.getNode(name,"ban","raison").setValue("none");
		
		loaderRootNode.getNode(name,"ban","time","day").setValue(0);
		loaderRootNode.getNode(name,"ban","time","hour").setValue(0);
		loaderRootNode.getNode(name,"ban","time","minut").setValue(0);
		
		loaderRootNode.getNode(name,"ban","time_end","year").setValue(0);
		loaderRootNode.getNode(name,"ban","time_end","month").setValue(0);
		loaderRootNode.getNode(name,"ban","time_end","day").setValue(0);
		loaderRootNode.getNode(name,"ban","time_end","hour").setValue(0);
		loaderRootNode.getNode(name,"ban","time_end","minute").setValue(0);
		
		loaderRootNode.getNode(name,"ban","time_begin","year").setValue(0);
		loaderRootNode.getNode(name,"ban","time_begin","month").setValue(0);
		loaderRootNode.getNode(name,"ban","time_begin","month-max-day").setValue(0);		
		loaderRootNode.getNode(name,"ban","time_begin","day").setValue(0);
		loaderRootNode.getNode(name,"ban","time_begin","hour").setValue(0);
		loaderRootNode.getNode(name,"ban","time_begin","minute").setValue(0);
		
		
		loaderRootNode.getNode(name,"ban","author","UUID").setValue("none");
		loaderRootNode.getNode(name,"ban","author","name").setValue("none");
		loaderRootNode.getNode(name,"ban","author","grade").setValue("none");
				
		loaderRootNode.getNode(name,"last-ban-list").setValue(Arrays.asList("|.|UUID-author-of-ban=none|.|name-of-athor=none|.|date_begin=YY:MM:DD:HH:mm|.|date_end=YY:MM:DD:HH:mm|.|time=YY:MM:DD:HH:mm|.|player-name=none|.|raison=none|.|"));
	
		loaderRootNode.getNode(name,"config","langage").setValue("default");
		
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
		if(!playerConfigExist(uuid)) {
			// création du fichier
			File file = new File(playerPath + uuid.toString() + extensionFile); 	// Instancie new file
			
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
			return new File(playerPath + uuid.toString() + extensionFile);		// Return la file du nom de l'UUID
		}
		return null;
	}
	
	public PlayerNode getPlayerConfigNode(UUID uuid) {
		if(playerConfigExist(uuid)) {			
			File file = new File(playerPath + uuid.toString() + extensionFile); 	// Instancie new file		
			ConfigurationLoader<CommentedConfigurationNode> loader = builderConfigLoader(file);		// build file loader
			
			PlayerNode plyNode = new PlayerNode();	//load file / return loader
			plyNode.setCfgNode(loader);
			return plyNode;
			
		}
		return null;
	}
	
	public PlayerNode getPlayerConfigNode(File file) {			
		ConfigurationLoader<CommentedConfigurationNode> loader = builderConfigLoader(file);		// build file loader
			
		PlayerNode plyNode = new PlayerNode();	//load file / return loader
		plyNode.setCfgNode(loader);
		return plyNode;
	}
	
	
	public boolean playerConfigExist(UUID uuid) {
		String uid = uuid.toString();	
		File playerFile = new File(playerPath + uid + extensionFile);	// Instancie new file
		if(playerFile.exists()){	// check si file exist
			return true;
		}
		return false;
	}
	
}
