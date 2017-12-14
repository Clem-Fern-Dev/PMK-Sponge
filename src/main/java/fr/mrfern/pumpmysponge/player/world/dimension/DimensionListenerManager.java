package fr.mrfern.pumpmysponge.player.world.dimension;

import java.util.HashMap;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import fr.mrfern.pumpmysponge.Main;

public class DimensionListenerManager {
	
	private static DimensionListenerManager instance = new DimensionListenerManager();
	private static Main mainManager;
	

	public static DimensionListenerManager listener(Main main) {
		mainManager = main;
		return instance;
	}
		 
	public void setup() {
		Sponge.getEventManager().registerListeners(mainManager, new DimensionListener(mainManager));
	}
}
