package fr.mrfern.pumpmysponge.player.build;

import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.living.humanoid.ChangeGameModeEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.w3c.dom.events.EventException;

public class BuildListener {
	
	@SuppressWarnings("unused")
	private Logger logger;

	public BuildListener(Logger logger) {
		this.logger = logger;
	}
	
	@Listener
	public void OnChangeGmSecure(ChangeGameModeEvent.TargetPlayer event,@First Player ply) {
		
		Player target = event.getTargetEntity();
		
		if(event.getGameMode().equals(GameModes.ADVENTURE) & event.getGameMode().equals(GameModes.NOT_SET)) {
			event.setCancelled(true);			
		}else {
			
			if(event.getGameMode().equals(GameModes.CREATIVE) | event.getOriginalGameMode().equals(GameModes.SURVIVAL)) {
				
				if(target.hasPermission("group.build") | target.hasPermission("pumpmysponge.build.safe")) {
					
					if(target.getUniqueId().toString().equals(ply.getUniqueId().toString())) {
						
						Text textClaimed = Text.builder("Changement de gamemod accordé").color(TextColors.YELLOW).build();
				    	Text textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
			    		ply.sendMessage(textEnTete);
			    		
					}else {
						
						Text textClaimed = Text.builder("Changement de gamemod accordé pour le joueur " + target.getName()).color(TextColors.YELLOW).build();
				    	Text textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
			    		ply.sendMessage(textEnTete);
			    		
			    		textClaimed = Text.builder("Changement de gamemod accordé, fait par le joueur " + ply.getName()).color(TextColors.YELLOW).build();
				    	textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
			    		target.sendMessage(textEnTete);
			    		
					}
		    		
		    		//ajout du BuildData
		    		
		    		if(BuildManager.getHashBuild().containsKey(event.getTargetEntity())) {
		    			
		    			if(target.getUniqueId().toString().equals(ply.getUniqueId().toString())) {
							
							Text textClaimed = Text.builder("Changement de gamemod accordé").color(TextColors.YELLOW).build();
					    	Text textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
				    		ply.sendMessage(textEnTete);
				    		
						}else {
							
							Text textClaimed = Text.builder("Changement de gamemod accordé pour le joueur " + target.getName()).color(TextColors.YELLOW).build();
					    	Text textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
				    		ply.sendMessage(textEnTete);
				    		
				    		textClaimed = Text.builder("Changement de gamemod accordé, fait par le joueur " + ply.getName()).color(TextColors.YELLOW).build();
					    	textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
				    		target.sendMessage(textEnTete);
				    		
						}
		    			// erreur evenement report
		    		}else {		    			
		    			// ajout de la permission de build avec spawn protection
		    			
		    			// message
		    			if(target.getUniqueId().toString().equals(ply.getUniqueId().toString())) {
							
							Text textClaimed = Text.builder("Changement de gamemod accordé").color(TextColors.YELLOW).build();
					    	Text textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
				    		ply.sendMessage(textEnTete);
				    		
						}else {
							
							Text textClaimed = Text.builder("Changement de gamemod accordé pour le joueur " + target.getName()).color(TextColors.YELLOW).build();
					    	Text textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
				    		ply.sendMessage(textEnTete);
				    		
				    		textClaimed = Text.builder("Changement de gamemod accordé, fait par le joueur " + ply.getName()).color(TextColors.YELLOW).build();
					    	textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
				    		target.sendMessage(textEnTete);
				    		
						}
		    			
		    			// inventaire sauvegardé
		    			BuildManager.getHashBuild().put(event.getTargetEntity(), new BuildData(event.getTargetEntity()));
		    		}
		    		
				}else {
					
					Text textClaimed = Text.builder("Changement de gamemod non accordé").color(TextColors.RED).build();
			    	Text textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
		    		ply.sendMessage(textEnTete);
					event.setCancelled(true);
					
				}
				
			}else if(event.getGameMode().equals(GameModes.SURVIVAL) | event.getOriginalGameMode().equals(GameModes.CREATIVE)) {
				
				if(!BuildManager.getHashBuild().containsKey(event.getTargetEntity())) {
	    			// erreur evenement report
	    		}else {
	    			BuildManager.getHashBuild().remove(event.getTargetEntity());
	    		}
				
			}
		}
	}
	
}
