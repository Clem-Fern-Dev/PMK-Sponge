package fr.mrfern.pumpmysponge.chunk;

import org.spongepowered.api.Sponge;

import fr.mrfern.pumpmysponge.Main;

@Deprecated
public class ChunkListenerManager {

	private static ChunkListenerManager instance = new ChunkListenerManager();
	private static Main mainManager;

		public static ChunkListenerManager listener(Main main) {
			mainManager = main;
			return instance;
		}
		 
		@Deprecated
		public void setup() {
			Sponge.getEventManager().registerListeners(mainManager, new ChunkDiscoverListener());
			Sponge.getEventManager().registerListeners(mainManager, new ProtectChunk());//HandInteractEvent
		}
	
}
