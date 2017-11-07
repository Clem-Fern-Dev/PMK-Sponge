package fr.mrfern.spongeplugintest.config;

import java.io.File;
import java.io.IOException;

import fr.mrfern.spongeplugintest.Main;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class ChunkConfig implements IConfig{

	protected String name = "chunk";
	private String pluginName = Main.getPluginName();
	protected String defaultPath = "./mods/plugins/"+ pluginName + "/";
	protected String chunkPath = defaultPath + "chunk/";
	
	private boolean isSetup = false;
	
	private static ChunkConfig instance = new ChunkConfig();	
	
	private File defaultFileConfig;
	private ConfigurationLoader<CommentedConfigurationNode> cfgLoader;
	private ConfigurationNode loaderRootNode;
	
	
	public void setup() {
		
		defaultFileConfig = new File(chunkPath + name + IConfig.extensionFile);
		
		checkPath(chunkPath, true);		
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
				
		/*
		loaderRootNode.getNode(name,"UUID").setValue("none");
		loaderRootNode.getNode(name,"ban").setValue("none");
		loaderRootNode.getNode(name,"config").setValue("test");
		loaderRootNode.getNode(name,"config","langage").setValue("fr");
		*/
		try {  
			cfgLoader.save(loaderRootNode); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ChunkConfig getInstance() {
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
	
	public boolean createChunkConfig(String world, int posX , int posZ) {
		if(getChunkConfigFile(world,posX,posZ) != null) {
			// création du fichier
			//File file = new File(pluginName + playerPath + uuid.toString() + extensionFile); 	// Instancie new file
			
			/*try {
				//FileUtils.copyFile(defaultFileConfig, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			return true;
		}
		return false;
	}
	
	public File getChunkConfigFile(String world, int posX , int posZ) {
		if(ChunkConfigExist(world,posX,posZ)) {	// check si un fichier du nom de l'UUID existe
			//return new File(pluginName + playerPath + uuid.toString() + extensionFile);		// Return la file du nom de l'UUID
		}
		return null;
	}
	
	public ChunkNode getChukConfigNode(String world, int posX , int posZ) {
		if(ChunkConfigExist(world,posX,posZ)) {			
			//File file = new File(pluginName + playerPath + uuid.toString() + extensionFile); 	// Instancie new file		
			//ConfigurationLoader<CommentedConfigurationNode> loader = builderConfigLoader(file);		// build file loader
			
			ChunkNode plyNode = new ChunkNode();	//load file / return loader
			//plyNode.setCfgNode(loader);
			return plyNode;
			
		}
		return null;
	}
	
	@SuppressWarnings("unused")
	public boolean ChunkConfigExist(String world, int posX , int posZ) {
		//File playerFile = new File(pluginName + playerPath + uuid.toString() + extensionFile);	// Instancie new file
		if(/*playerFile.exists()*/true){	// check si file exist
			return true;
		}
		return false;
	}

}
