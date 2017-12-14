package fr.mrfern.pumpmysponge.player.world.dimension;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;

import fr.mrfern.pumpmysponge.Main;

public class DimensionListener {
	
	@SuppressWarnings("unused")
	private static Main main;

	public DimensionListener(Main mainManager) {
		main = mainManager;
	}
	
	@Listener
	public void InitDimensionEnd(GameStartedServerEvent event) {
		
	}

}
