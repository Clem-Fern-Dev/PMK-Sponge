package fr.mrfern.spongeplugintest.config;

public abstract class PluginConfig {
	
	private static PluginConfig instance = new PluginConfig(){};

	public static PluginConfig getInstance() {
		return instance;
	}
	
	public boolean baseConfig_Exist(String path) {
		
		return true;
	}
	

}
