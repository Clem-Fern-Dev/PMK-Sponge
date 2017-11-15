package fr.mrfern.spongeplugintest.chunk;

import org.spongepowered.api.Sponge;

import fr.mrfern.spongeplugintest.Main;

public class ChunkListenerManager {

	private static ChunkListenerManager instance = new ChunkListenerManager();
	private static Main mainManager;

		public static ChunkListenerManager listener(Main main) {
			mainManager = main;
			return instance;
		}
		 
		public void setup() {
			Sponge.getEventManager().registerListeners(mainManager, new ChunkDiscoverListener());			
		}
	
}
