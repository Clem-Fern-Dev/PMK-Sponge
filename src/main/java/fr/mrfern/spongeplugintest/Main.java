package fr.mrfern.spongeplugintest;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "spongeplugintest", name = "spongeplugintest", version = "1.0")
public class Main {
	
	@Listener
    public void onServerStart(GameStartedServerEvent event) {
		
    }

}
