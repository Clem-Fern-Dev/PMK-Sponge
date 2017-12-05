package fr.mrfern.pumpmysponge.player;

import org.spongepowered.api.Sponge;

import fr.mrfern.pumpmysponge.Main;
import fr.mrfern.pumpmysponge.player.ban.PlayerBanListener;

public class PlayerListenerManager {

	private static PlayerListenerManager instance = new PlayerListenerManager();
	private static Main mainManager;

		public static PlayerListenerManager listener(Main main) {
			mainManager = main;
			return instance;
		}
		 
		public void setup() {
			Sponge.getEventManager().registerListeners(mainManager, new PlayerBanListener(mainManager.getLogger()));
			Sponge.getEventManager().registerListeners(mainManager, new PlayerConfigListener(mainManager.getLogger()));
			Sponge.getEventManager().registerListeners(mainManager, new PlayerChatListener(mainManager.getLogger()));
		}
	
}
