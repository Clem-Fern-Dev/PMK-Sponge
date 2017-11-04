package fr.mrfern.spongeplugintest.config;

import java.io.File;

import org.slf4j.Logger;

public class Config implements IConfig{

	protected String name = "config";
	protected String defaultpath = "./mods/plugins/spongeplugintest/";
	
	private boolean isSetup = false;
	private Logger logger;
	
	private static Config instance = new Config();	
	
	public void setup(Logger logger) {
		
		this.logger = logger;
	
		logger.info("Config setup " + this.getClass().getName());
		File defaultFile = new File(defaultpath + name + IConfig.extensionFile);
		
		checkPath(defaultpath, true);
		
		checkFile(defaultFile, true);
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
		// TODO Auto-generated method stub
		
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

	public Logger getLogger() {
		return logger;
	}
				
}
