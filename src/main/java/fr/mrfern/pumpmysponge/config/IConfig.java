package fr.mrfern.pumpmysponge.config;

import java.io.File;
import java.io.IOException;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public interface IConfig {

	String extensionFile = ".json";
	
	public void setup();
	
	public default ConfigurationLoader<CommentedConfigurationNode> builderConfigLoader(File file) {
		return HoconConfigurationLoader.builder().setFile(file).build();		
	}
	public default ConfigurationNode loadConfigNode(ConfigurationLoader<CommentedConfigurationNode> cfgConfigBuilderResult) {
		try {
		    return cfgConfigBuilderResult.load();
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public default boolean checkFile(File file,boolean b) {
		if(!file.exists()) {
			if(b) {
				//file.getParentFile().mkdirs();
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//setFile(file);
				return true;
			}
			return false;
		}else {
			return true;
		}
	}
	
	public default boolean checkPath(String path, boolean b) {
		File filepath = new File(path);
		if(!filepath.exists()) {
			if(b) {
				filepath.mkdirs();
				return true;
			}
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isActive();
	
	public boolean isSetup();
	public void setIsSetup(boolean isSetup);

	void set(ConfigurationLoader<CommentedConfigurationNode> cfgLoader, ConfigurationNode loaderRootNode);
	
}
