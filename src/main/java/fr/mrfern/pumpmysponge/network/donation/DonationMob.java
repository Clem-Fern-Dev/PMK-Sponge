package fr.mrfern.pumpmysponge.network.donation;

import org.spongepowered.api.Sponge;

import fr.mrfern.pumpmysponge.Main;

public class DonationMob {
	
	private static DonationMob instance = new DonationMob();
	private static Main mainManager;

		public static DonationMob listener(Main main) {
			mainManager = main;
			return instance;
		}
		 
		public void setup() {
			Sponge.getEventManager().registerListeners(mainManager, new  LoadedMapComplete(mainManager));
		}
}
