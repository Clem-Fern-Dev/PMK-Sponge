package fr.mrfern.pumpmysponge.player.build;

import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.living.humanoid.ChangeGameModeEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class BuildListener {
	
	@SuppressWarnings("unused")
	private Logger logger;

	public BuildListener(Logger logger) {
		this.logger = logger;
	}
	
	@Listener
	public void OnChangeGmSecure(ChangeGameModeEvent.TargetPlayer event,@First Player ply) {
				
		if(event.getGameMode().equals(GameModes.CREATIVE) | !event.getOriginalGameMode().equals(GameModes.CREATIVE)) {
			
			if(ply.hasPermission("group.build") | ply.hasPermission("pumpmysponge.build.safe")) {
				
				Text textClaimed = Text.builder("Changement de gamemod accordé").color(TextColors.YELLOW).build();
		    	Text textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
	    		ply.sendMessage(textEnTete);
	    		
	    		//ajout du BuildData
	    		
			}else {
				
				Text textClaimed = Text.builder("Changement de gamemod non accordé").color(TextColors.RED).build();
		    	Text textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
	    		ply.sendMessage(textEnTete);
				event.setCancelled(true);
				
			}
			
		}else if(event.getGameMode().equals(GameModes.SURVIVAL) | event.getOriginalGameMode().equals(GameModes.CREATIVE)) {
			
			
			
		}
	}
	
}
