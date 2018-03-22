package fr.pmk_social;

import java.util.logging.Logger;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameLoadCompleteEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

import fr.pmk_social.commands.BasicCommandManager;
import fr.pmk_social.commands.tp.TpaCommandManager;


@Plugin(id = "pmk-social", name = "PMK-Social", version = "1.0.0")
public class MainSocial {
	
	protected static String pluginName = "PMK-Social";
	
	public static String getPluginName() {		return pluginName;	}
	
	@Inject
	private Logger logger;
	
	public Logger getLogger() {
		return this.logger;
	}
	
	@Listener
	public void onPreInit(GamePreInitializationEvent event) {
			
	}
	
	@Listener
	public void onInit(GameInitializationEvent event) {
		
	}
	
	@Listener
	public void onPostInit(GamePostInitializationEvent event) {
		
	}
	
	@Listener
	public void onInitComplete(GameLoadCompleteEvent event) {
		
	}
	
	@Listener
	public void onStartServer(GameStartingServerEvent event) {
		
		// Tu crée ton commands manager
		CommandManager cmdManager = Sponge.getCommandManager();
		//Tu appelles ta classe, tu l'instancie en y ajoutant le command manager par la methods commands, et enfin tu fait un setup pour build les commands
		//ChunkCommandManager.commands(this).setupCommands();
		BasicCommandManager.commands(this,cmdManager).setupCommands();
		TpaCommandManager.commands(this, cmdManager).setupCommands();
	}
}

