package fr.mrfern.spongeplugintest.config;

import java.io.File;
import java.io.IOException;

public interface IConfig {

	String extensionFile = ".json";
	public void setup();
	public void load();
	
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
				setFile(filepath);
				return true;
			}
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isActive();
	
	public default void setFile(File file) {
		set(file);
	}
	
	public default void setFilePath(String filePath) {
		set(new File(filePath));
	}
	
	public void set(File file);
	
	public boolean isSetup();
	public void setIsSetup(boolean isSetup);
	
}
