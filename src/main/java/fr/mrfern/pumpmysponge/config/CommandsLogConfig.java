package fr.mrfern.pumpmysponge.config;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import fr.mrfern.pumpmysponge.Main;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class CommandsLogConfig implements IConfig{

	protected String name = "CommandsLogs";
	protected String nameNode = name.toLowerCase();
	private String pluginName = Main.getPluginName();
	protected String defaultPath = "./mods/plugins/"+ pluginName + "/";
	protected String playerPath = defaultPath + name +"/";
	
	
	private boolean isSetup = false;
	
	private static CommandsLogConfig instance = new CommandsLogConfig();	
	
	private File defaultFileConfig;
	private ConfigurationLoader<CommentedConfigurationNode> cfgLoader;
	private ConfigurationNode loaderRootNode;
	
	public void setup() {
		
		
	}
	
	@Deprecated
	public ConfigurationLoader<CommentedConfigurationNode> builderConfigLoader(File file) {
		new Exception(this.getClass().getName() + "Fichier de config unique, impossible d'en builder un autre");
		return null;
	}
	
	@Deprecated
	public ConfigurationNode loadConfigNode(ConfigurationLoader<CommentedConfigurationNode> cfgConfigBuilderResult) {
		new Exception(this.getClass().getName() + "Fichier de config unique, impossible d'en builder un autre");
		return null;
	}
	
	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public void set(ConfigurationLoader<CommentedConfigurationNode> cfgLoader, ConfigurationNode loaderRootNode) {
				
		/*loaderRootNode.getNode(pluginName,"bdd","backup-config").setValue(false);
		loaderRootNode.getNode(pluginName,"bdd","host").setValue("none");
		loaderRootNode.getNode(pluginName,"bdd","port").setValue(0);
		loaderRootNode.getNode(pluginName,"bdd","user").setValue("none");
		loaderRootNode.getNode(pluginName,"bdd","mdp").setValue("none");
		
		loaderRootNode.getNode(pluginName,"config","extension-list").setValue(Arrays.asList(""));
		
		loaderRootNode.getNode(pluginName,"google-authentificator","enable").setValue(false);
		loaderRootNode.getNode(pluginName,"google-authentificator","force-op-user").setValue(false);
		loaderRootNode.getNode(pluginName,"google-authentificator", "user-UUID-list").setValue(Arrays.asList(""));
		
		*/
		
		try {
			cfgLoader.save(loaderRootNode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static CommandsLogConfig getInstance() {
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
