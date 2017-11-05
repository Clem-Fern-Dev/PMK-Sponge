package fr.mrfern.spongeplugintest.config;

import java.io.File;

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
	
	public void setup() {
		
		defaultFileConfig = new File(defaultpath + name + IConfig.extensionFile);
		
		checkPath(defaultpath, true);		
		checkFile(defaultFileConfig, true);
		
		cfgLoader = builderConfigLoader(defaultFileConfig);
		loaderRootNode = loadConfigNode(cfgLoader);
		
		set(defaultFileConfig); 
		
		setIsSetup(true);
		
	}
	
	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public void set(File file) {
		
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
}
