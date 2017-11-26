package fr.mrfern.pumpmysponge.config;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;

import fr.mrfern.pumpmysponge.Main;
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
	
	@Deprecated
	public ConfigurationNode loadConfigNode(ConfigurationLoader<CommentedConfigurationNode> cfgConfigBuilderResult) {
		// TODO Auto-generated method stub
		return IConfig.super.loadConfigNode(cfgConfigBuilderResult);
	}
	
	public void setup() {
		
		defaultFileConfig = new File(chunkPath + name + IConfig.extensionFile);
		
		checkPath(chunkPath, true);	
		
		/*
		 * Création de dossier pour chaque monde
		 */
		
		// Récupération des mondes à faire
		
		if(checkFile(defaultFileConfig, true)) {
			cfgLoader = HoconConfigurationLoader.builder().setFile(defaultFileConfig).build();
			try {
				loaderRootNode = cfgLoader.load();
				set(cfgLoader,loaderRootNode); 
			} catch(IOException e) {
				e.printStackTrace();
			}		

		}			
				
	}

	@Override
	public void set(ConfigurationLoader<CommentedConfigurationNode> cfgLoader, ConfigurationNode loaderRootNode) {		
		
		loaderRootNode.getNode(name,"chunk-info","posX").setValue(0);
		loaderRootNode.getNode(name,"chunk-info","posZ").setValue(0);	
		loaderRootNode.getNode(name,"chunk-info","world").setValue("none");
		
		loaderRootNode.getNode(name,"chunk-config","blocked").setValue(false);
		loaderRootNode.getNode(name,"chunk-config","author-of-blocked","UUID").setValue("none");
		loaderRootNode.getNode(name,"chunk-config","author-of-blocked","name").setValue("none");
		loaderRootNode.getNode(name,"chunk-config","author-of-blocked","grade").setValue("none");
		loaderRootNode.getNode(name,"chunk-config","blocked","raison").setValue("none");
		loaderRootNode.getNode(name,"chunk-config","blocked","timer","enable").setValue(false);
		loaderRootNode.getNode(name,"chunk-config","blocked","timer","time","days").setValue(0);
		loaderRootNode.getNode(name,"chunk-config","blocked","timer","time","hour").setValue(0);
		loaderRootNode.getNode(name,"chunk-config","blocked","timer","time","minute").setValue(0);		
		
		loaderRootNode.getNode(name,"chunk-player-info","discover-by").setValue("none");
		loaderRootNode.getNode(name,"chunk-player-claim","claim-by").setValue("none");
		loaderRootNode.getNode(name,"chunk-player-perm","group-overpass").setValue(Arrays.asList("none"));
		loaderRootNode.getNode(name,"chunk-player-perm","user").setValue(Arrays.asList("none"));	
		
		// user
		loaderRootNode.getNode(name,"chunk-group-perm","user","block").setValue(true);
		loaderRootNode.getNode(name,"chunk-group-perm","user","vault").setValue(true);
		loaderRootNode.getNode(name,"chunk-group-perm","user","door").setValue(true);
		loaderRootNode.getNode(name,"chunk-group-perm","user","button").setValue(true);
		loaderRootNode.getNode(name,"chunk-group-perm","user","promote-user").setValue(false);
		
		loaderRootNode.getNode(name,"chunk-group-perm","user","command-chunk","unclaim").setValue(false);
		loaderRootNode.getNode(name,"chunk-group-perm","user","command-chunk","add-co-owner").setValue(false);
		loaderRootNode.getNode(name,"chunk-group-perm","user","command-chunk","adduser").setValue(true);
		loaderRootNode.getNode(name,"chunk-group-perm","user","command-chunk","addinvite-prim").setValue(true);
		loaderRootNode.getNode(name,"chunk-group-perm","user","command-chunk","addinvite-temp").setValue(true);
		
		/*perm:
		    co_owner:
		      block: true
		      vault: true
		      door: true
		      button: true
		      commandChunk:
		        unclaim: false
		        addco_owner: false
		        adduser: true
		        addinvite_prim: true
		        addinvite_temp: true
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
		if(getChunkConfigFile(world,posX,posZ) == null) {
			// création du fichier
			File file = new File(chunkPath + "c_" + posX +"_"+ posZ + extensionFile); 	// Instancie new file
			
			try {
				FileUtils.copyFile(defaultFileConfig, file);
				
				ChunkNode chunkNode = getChunkConfigNode(world, posX, posZ);
				chunkNode.setLocationZ(posZ);
				chunkNode.setLocationX(posX);
				chunkNode.save();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return true;
		}
		return false;
	}
	
	public File getChunkConfigFile(String world, int posX , int posZ) {
		if(ChunkConfigExist(world,posX,posZ)) {	// check si un fichier du nom de l'UUID existe
			return new File(chunkPath + "c_" + posX +"_"+ posZ + extensionFile);		// Return la file du nom de l'UUID
		}
		return null;
	}
	
	public ChunkNode getChunkConfigNode(String world, int posX , int posZ) {
		if(ChunkConfigExist(world,posX,posZ)) {			
			File file = new File(chunkPath + "c_" + posX + "_" + posZ + extensionFile); 	// Instancie new file		
			ConfigurationLoader<CommentedConfigurationNode> loader = builderConfigLoader(file);		// build file loader
			
			ChunkNode plyNode = new ChunkNode();	//load file / return loader
			plyNode.setCfgNode(loader);
			return plyNode;
			
		}
		return null;
	}
	
	public boolean ChunkConfigExist(String world, int posX , int posZ) {
		File chunkFile = new File(chunkPath + "c_" + posX + "_" + posZ + extensionFile);	// Instancie new file
		if(checkFile(chunkFile, false)){	// check si file exist
			return true;
		}
		return false;
	}

}
