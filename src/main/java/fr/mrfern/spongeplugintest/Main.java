package fr.mrfern.spongeplugintest;

import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

@Plugin(id = "spongeplugintest", name = "spongeplugintest", version = "1.0")
public class Main {
	
	@Inject
	private Logger logger;
	
	@Listener
    public void onServerStart(GameStartedServerEvent event) {
				
    }
	
	@Listener
	public void onLoadPlugin(GamePreInitializationEvent event) {
		logger.info("Lancement plugin");
	}

}
