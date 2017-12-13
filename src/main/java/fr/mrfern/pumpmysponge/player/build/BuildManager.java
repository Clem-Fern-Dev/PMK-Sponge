package fr.mrfern.pumpmysponge.player.build;

import java.util.HashMap;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import fr.mrfern.pumpmysponge.Main;

public class BuildManager {
	private static BuildManager instance = new BuildManager();
	private static Main mainManager;
	
	private static HashMap<Player, BuildData> hashBuild = new HashMap<Player, BuildData>();

		public static BuildManager listener(Main main) {
			mainManager = main;
			return instance;
		}
		 
		public void setup() {
			Sponge.getEventManager().registerListeners(mainManager, new BuildListener(mainManager.getLogger()));
		}

	static public HashMap<Player, BuildData> getHashBuild() {
			return hashBuild;
	}

	static public void setHashBuild(HashMap<Player, BuildData> hashBuild) {
			BuildManager.hashBuild = hashBuild;
	}
}
