package fr.mrfern.spongeplugintest.config;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import fr.mrfern.spongeplugintest.Main;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class Config implements IConfig{

	protected String name = "config";
	protected String defaultpath = "./mods/plugins/spongeplugintest/";
	protected String gAuthNode = "google-authentificator";
	protected String bddNode = "bdd";
	protected String configNode = "config";
	
	private boolean isSetup = false;
	
	private static Config instance = new Config();	
	
	private File defaultFileConfig;
	private ConfigurationLoader<CommentedConfigurationNode> cfgLoader;
	private ConfigurationNode loaderRootNode;
	private String pluginName;
	
	public void setup() {
		
		pluginName = Main.getPluginName();
		
		defaultFileConfig = new File(defaultpath + name + IConfig.extensionFile);
		
		checkPath(defaultpath, true);		
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
		return IConfig.super.loadConfigNode(cfgConfigBuilderResult);
	}
	
	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public void set(ConfigurationLoader<CommentedConfigurationNode> cfgLoader, ConfigurationNode loaderRootNode) {
				
		loaderRootNode.getNode(pluginName,"bdd","backup-config").setValue(false);
		loaderRootNode.getNode(pluginName,"bdd","host").setValue("none");
		loaderRootNode.getNode(pluginName,"bdd","port").setValue(0);
		loaderRootNode.getNode(pluginName,"bdd","user").setValue("none");
		loaderRootNode.getNode(pluginName,"bdd","mdp").setValue("none");
		
		loaderRootNode.getNode(pluginName,"config","extension-list").setValue(Arrays.asList(""));
		
		loaderRootNode.getNode(pluginName,"google-authentificator","enable").setValue(false);
		loaderRootNode.getNode(pluginName,"google-authentificator","force-op-user").setValue(false);
		loaderRootNode.getNode(pluginName,"google-authentificator", "user-UUID-list").setValue(Arrays.asList(""));
		
		
		
		try {
			cfgLoader.save(loaderRootNode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public ConfigurationNode getLoaderRootNode() {
		return loaderRootNode;
	}

	public ConfigurationLoader<CommentedConfigurationNode> getCfgLoader() {
		return cfgLoader;
	}
	
	/* Methods de récuperation rapide des veleurs de nodes
	 * A ajouté pour chaque Node
	 */
	
	// Bdd
	
	public String getBddHost() {
		// Adresse de connexion à la bdd
		return loaderRootNode.getNode(pluginName,bddNode,"host").getString();
	}
	
	public int getBddPort() {
		// Port de connexion à la bdd
		return loaderRootNode.getNode(pluginName,bddNode,"port").getInt();
	}
	
	public String getBddUser() {
		// Récupération de l'utilisateur de connection à la bdd
		return loaderRootNode.getNode(pluginName,bddNode,"user").getString();
	}
	
	public String getBddMdp() {
		// Récupération du mot de passe de la bdd
		return loaderRootNode.getNode(pluginName,bddNode,"mdp").getString();
	}
	
	public boolean getBddBackUpState() {
		// Récupération de la valeur d'activation ou non des backups
		return loaderRootNode.getNode(pluginName,bddNode,"backup-config").getBoolean();
	}
	
	// google-authentificator	
	
	public boolean getGAuthState() {
		// Récupération de la valeur boolean si la double auth est acitve ou non
		return loaderRootNode.getNode(pluginName,gAuthNode,"enable").getBoolean();
	}
	
	public boolean getGAuthForceUseToOpUser() {
		// Récuperation de la valeur boolean si les op sont obligé d'utilisé la double auth
		return loaderRootNode.getNode(pluginName,gAuthNode,"force-op-user").getBoolean();
	}
	
	public List<? extends ConfigurationNode> getGAuthUserList() {
		// Récuperation de la liste des utilisateur utilisant la double auth
		return loaderRootNode.getNode(pluginName,gAuthNode,"user-UUID-list").getChildrenList();
	}

	
}
