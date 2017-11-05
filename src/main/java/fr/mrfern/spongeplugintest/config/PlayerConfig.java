package fr.mrfern.spongeplugintest.config;

import java.io.File;

import org.spongepowered.api.entity.living.player.Player;

import fr.mrfern.spongeplugintest.Main;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class PlayerConfig implements IConfig{

	protected String name = "config";
	protected String defaultpath = "./mods/plugins/spongeplugintest/";
	protected String gAuthNode = "google-authentificator";
	protected String bddNode = "bdd";
	protected String configNode = "config";
	
	@SuppressWarnings("unused")
	private boolean isSetup = false;
	
	private static PlayerConfig instance = new PlayerConfig();	
	
	private File defaultFileConfig;
	private ConfigurationLoader<CommentedConfigurationNode> cfgLoader;
	private ConfigurationNode loaderRootNode;
	private String pluginName;
	
	@Override
	public void setup() {
		setPluginName(Main.getPluginName());
		
		setDefaultFileConfig(new File(defaultpath + name + IConfig.extensionFile));
		
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSetup() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsSetup(boolean isSetup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(ConfigurationLoader<CommentedConfigurationNode> cfgLoader, ConfigurationNode loaderRootNode) {
		// TODO Auto-generated method stub
		
	}

	public void setSetup(boolean isSetup) {
		this.isSetup = isSetup;
	}

	public static PlayerConfig getInstance() {
		return instance;
	}

	public static void setInstance(PlayerConfig instance) {
		PlayerConfig.instance = instance;
	}

	public File getDefaultFileConfig() {
		return defaultFileConfig;
	}

	public void setDefaultFileConfig(File defaultFileConfig) {
		this.defaultFileConfig = defaultFileConfig;
	}

	public ConfigurationLoader<CommentedConfigurationNode> getCfgLoader() {
		return cfgLoader;
	}

	public void setCfgLoader(ConfigurationLoader<CommentedConfigurationNode> cfgLoader) {
		this.cfgLoader = cfgLoader;
	}

	public ConfigurationNode getLoaderRootNode() {
		return loaderRootNode;
	}

	public void setLoaderRootNode(ConfigurationNode loaderRootNode) {
		this.loaderRootNode = loaderRootNode;
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}

}
