package fr.mrfern.spongeplugintest.config;

import java.io.File;

public class Config implements IConfig{

	protected String name = "config";
	protected String defaultpath = "./mods/plugins/spongeplugintest/";
	
	private boolean isSetup = false;
	
	private static Config instance = new Config();	
	
	public void setup() {
		
		File defaultFile = new File(defaultpath + name + IConfig.extensionFile);
		
		checkPath(defaultpath, true);		
		checkFile(defaultFile, true);
		
		set(defaultFile); 
		
		setIsSetup(true);
		
	}

	@Override
	public void load() {
		
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
				
}
