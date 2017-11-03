package fr.mrfern.spongeplugintest;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameLoadCompleteEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

@Plugin(id = "spongeplugintest", name = "spongeplugintest", version = "1.0")
public class Main {
	
	@Inject
	private Logger logger;
	
	@Inject
	@DefaultConfig(sharedRoot = false)
	private Path defaultConfig;
	
	@Listener
	public void onPreInit(GamePreInitializationEvent event) {
		
		logger.info("Lancement plugin");
		
		/*L��v�nement GamePreInitializationEvent est lev�. Durant cet �tat, le plugin se pr�pare � l�initialisation. 
		 * Les acc�s � l�instance du logger par d�faut et aux informations concernant les localisations de fichiers de configurations pr�f�r�es 
		 * sont disponibles.
		 */
		
		
		
		
	}
	
	@Listener
	public void onInit(GameInitializationEvent event) {
		
		logger.info("Plugin Init");
		
		/*L��v�nement GameInitializationEvent est lev�. Durant cet �tat, le plugin devrait avoir finit tout ce qu�il avait � faire afin de fonctionner. 
		 * Les gestionnaires d��v�nements sont trait�s � ce moment l�.
		 */
		
	}
	
	@Listener
	public void onPostInit(GamePostInitializationEvent event) {
		
		logger.info("Plugin Post Init");
		
		/* L��v�nement GamePostInitializationEvent est lev�. Par cet �tat, les communications inter-plugin devraient �tre pr�tes � se produire. 
		 * Les plugins fournissant une API devraient �tre pr�ts � accepter des requ�tes de base.
		 */
		
	}
	
	@Listener
	public void onInitComplete(GameLoadCompleteEvent event) {
		
		logger.info("Plugin Load complete");
		
		//L��v�nement GameLoadCompleteEvent est lev�. Par cet �tat, toutes les initialisations des plugins devraient �tre termin�es.
		
	}
}
