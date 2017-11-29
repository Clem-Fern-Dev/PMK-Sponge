package fr.mrfern.pumpmysponge.config.ban;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import fr.mrfern.pumpmysponge.Main;
import fr.mrfern.pumpmysponge.config.IConfig;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class BanListConfig implements IConfig{
	
	String parent_name = "player", name_path = "ban" , name = "banList";
	
	private String pluginName = Main.getPluginName();
	protected String defaultPath = "./mods/plugins/"+ pluginName + "/" + parent_name;
	protected String banPath = defaultPath + "/" + name_path + "/";
	
	private boolean isSetup = false;
	
	private static BanListConfig instance = new BanListConfig();	
	
	private File defaultFileConfig;
	private ConfigurationLoader<CommentedConfigurationNode> cfgLoader;
	private ConfigurationNode loaderRootNode;
	
	public void setup() {
		
		pluginName = Main.getPluginName();
		
		defaultFileConfig = new File(banPath + name + IConfig.extensionFile);
		
		checkPath(banPath, true);		
		if(checkFile(defaultFileConfig, true)) {
			cfgLoader = HoconConfigurationLoader.builder().setFile(defaultFileConfig).build();
			try {
			    loaderRootNode = cfgLoader.load();
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			set(cfgLoader,loaderRootNode); 
			
		}else {
			
			cfgLoader = HoconConfigurationLoader.builder().setFile(defaultFileConfig).build();
			try {
			    loaderRootNode = cfgLoader.load();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
				
		setIsSetup(true);		
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
		
		// set empty list
		
		loaderRootNode.getNode(name).setValue(Arrays.asList(""));
		
		save();
	}

	public static BanListConfig getInstance() {
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
	
	public void save() {
		try {
			cfgLoader.save(loaderRootNode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// methods d'édition du fichier de config
	
	public List<String> getBanList(){
		
		Function<Object,String> stringTransformer = new Function<Object,String>() {
		    @Override
		    public String apply(Object input) {
		        if (input instanceof String) {
		            return (String) input;
		        } else {
		            return null;
		        }
		    }
		};
		
		return loaderRootNode.getNode(name).getList(stringTransformer);
	}
	
	public void setBanList(List<String> list){
		loaderRootNode.getNode(name).setValue(list);
	}
	
	public boolean contains(UUID uuidTarget) {
		return new ArrayList<>(getBanList()).contains(uuidTarget.toString());
	}

	public void add(UUID uuidTarget) {
		List<String> list = new ArrayList<>(getBanList());				    	
    	list.add(uuidTarget.toString());
    	setBanList(list);				    	
    	save();	
	}
	
	public void remove(UUID uuidTarget) {
		if(contains(uuidTarget)) {
			
			List<String> list = new ArrayList<>(getBanList());				    	
	    	list.remove(uuidTarget.toString());
	    	setBanList(list);				    	
	    	save();	
	    	
		}
	}

}
