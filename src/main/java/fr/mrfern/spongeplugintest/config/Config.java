package fr.mrfern.spongeplugintest.config;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import fr.mrfern.spongeplugintest.Main;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class Config implements IConfig{

	protected String name = "config";
	protected String defaultpath = "./mods/plugins/spongeplugintest/";
	
	private boolean isSetup = false;
	
	private static Config instance = new Config();	
	
	private File defaultFileConfig;
	private ConfigurationLoader<CommentedConfigurationNode> cfgLoader;
	private ConfigurationNode loaderRootNode;
	private String pluginName;
	
	public void setup() {
		
		pluginName = Main.getPluginName();
		
		defaultFileConfig = new File(defaultpath + name + IConfig.extensionFile);
		
		checkPath(defaultpath, true);		
		if(checkFile(defaultFileConfig, true)) {
			cfgLoader = builderConfigLoader(defaultFileConfig);
			loaderRootNode = loadConfigNode(cfgLoader);
			
			set(cfgLoader,loaderRootNode); 
		}else {
			cfgLoader = builderConfigLoader(defaultFileConfig);
			loaderRootNode = loadConfigNode(cfgLoader);
		}
		
				
		setIsSetup(true);		
	}
	
	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public void set(ConfigurationLoader<CommentedConfigurationNode> cfgLoader, ConfigurationNode loaderRootNode) {
				
		loaderRootNode.getNode(pluginName,"bdd","backup-config").setValue(false);
		loaderRootNode.getNode(pluginName,"bdd","host").setValue("none");
		loaderRootNode.getNode(pluginName,"bdd","port").setValue(0);
		loaderRootNode.getNode(pluginName,"bdd","user").setValue("none");
		loaderRootNode.getNode(pluginName,"bdd","mdp").setValue("none");
		
		loaderRootNode.getNode(pluginName,"config","extension-list").setValue(Arrays.asList("none", Arrays.asList("test","test")));
		
		loaderRootNode.getNode(pluginName,"google-authentificator","enable").setValue(false);
		loaderRootNode.getNode(pluginName,"google-authentificator","force-op-user").setValue(false);
		loaderRootNode.getNode(pluginName,"google-authentificator", "user-UUID-list").setValue(Arrays.asList(""));
		
		
		
		try {
			cfgLoader.save(loaderRootNode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Config getInstance() {
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
	
	public String getBddHost() {
		return loaderRootNode.getNode(pluginName,"bdd","host").getString();
	}
	
	public int getBddPort() {
		return loaderRootNode.getNode(pluginName,"bdd","port").getInt();
	}
	
	public String getBddUser() {
		return loaderRootNode.getNode(pluginName,"bdd","user").getString();
	}
	
}
