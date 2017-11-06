package fr.mrfern.spongeplugintest;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameLoadCompleteEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import com.google.inject.Inject;
import fr.mrfern.spongeplugintest.config.Config;
import fr.mrfern.spongeplugintest.config.PlayerConfig;


@Plugin(id = "spongeplugintest", name = "spongeplugintest", version = "1.0.0")
public class Main {
	
	protected static String pluginName = "spongeplugintest";
	
	protected static String defaultpath = "./mods/plugins/"+ pluginName +"/";
	protected static String playerpath = "./mods/plugins/"+ pluginName +"/player";
	protected static String chunkpath = "./mods/plugins/"+ pluginName +"/chunk";
	
	
	public static String getPluginName() {		return pluginName;	}
	
	@Inject
	private Logger logger;
	
	public Logger getLogger() {
		return this.logger;
	}
	
	@Listener
	public void onPreInit(GamePreInitializationEvent event) {

		logger.info("Plugin preInit " + defaultpath);
		
		Config.getInstance().setup();
		PlayerConfig.getInstance().setup();
	
    
		/*L’événement GamePreInitializationEvent est levé. Durant cet état, le plugin se prépare à l’initialisation. 
		 * Les accès à l’instance du logger par défaut et aux informations concernant les localisations de fichiers de configurations préférées 
		 * sont disponibles.
		 */		
		
		// Création du répertoire de config de base
		
		
		// Création du répertoire de config de base
		/*File config = new File(path + "config.yml");
		if(!config.exists()) {
			config.mkdirs();
			logger.info("Création de " + path );
		}
		*/	
	}
	
	@Listener
	public void onInit(GameInitializationEvent event) {
		
		logger.info("Plugin Init");
				
		/*L’événement GameInitializationEvent est levé. Durant cet état, le plugin devrait avoir finit tout ce qu’il avait à faire afin de fonctionner. 
		 * Les gestionnaires d’événements sont traités à ce moment là.
		 */
		
	}
	
	@Listener
	public void onPostInit(GamePostInitializationEvent event) {
		
		logger.info("Plugin Post Init");
		
		/* L’événement GamePostInitializationEvent est levé. Par cet état, les communications inter-plugin devraient être prêtes à se produire. 
		 * Les plugins fournissant une API devraient être prêts à accepter des requêtes de base.
		 */
		
	}
	
	@Listener
	public void onInitComplete(GameLoadCompleteEvent event) {
		
		logger.info("Plugin Load complete");
		
		//L’événement GameLoadCompleteEvent est levé. Par cet état, toutes les initialisations des plugins devraient être terminées.
		
	}
	
	@Listener
	public void onStartServer(GameStartingServerEvent event) {
		
		CommandSpec myCommandSpec = CommandSpec.builder()
			    .description(Text.of("Hello World Command"))
			    .permission("myplugin.command.helloworld")
			    .executor(new HelloWorldCommand())
			    .build();
		
		Sponge.getCommandManager().register(this, myCommandSpec, "helloworld");
	}
}

