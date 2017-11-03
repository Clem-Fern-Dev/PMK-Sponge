package fr.mrfern.spongeplugintest;

import java.awt.Color;
import java.io.File;

import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameLoadCompleteEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

@Plugin(id = "spongeplugintest", name = "spongeplugintest", version = "1.0")
public class Main {
	
	protected static String path = "./plugins";
	@Inject
	private Logger logger;
	
	@Listener
	public void onPreInit(GamePreInitializationEvent event) {
		
		logger.info("Plugin preInit " + path);
		
		/*L’événement GamePreInitializationEvent est levé. Durant cet état, le plugin se prépare à l’initialisation. 
		 * Les accès à l’instance du logger par défaut et aux informations concernant les localisations de fichiers de configurations préférées 
		 * sont disponibles.
		 */
		
		File file = new File(path);
		if(!file.exists()) {
			
		}else {
			logger.info(path + " existe déjà");
		}
		
		File file1 = new File("./mods");
		if(!file1.exists()) {
			logger.info(" mod n'existe pas");
		}else {
			logger.info("mod existe");
		}
		
		File file2 = new File("./config");
		if(!file2.exists()) {
			logger.info(" mod n'existe pas");
		}else {
			logger.info("mod existe");
		}
			
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
	
	public static boolean returnBool(boolean b) {
		return b;		
	}
}

