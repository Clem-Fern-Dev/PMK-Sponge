package fr.mrfern.pumpmysponge.player.build;

import java.util.Optional;

import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.living.humanoid.ChangeGameModeEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.entity.Hotbar;
import org.spongepowered.api.item.inventory.entity.PlayerInventory;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.w3c.dom.events.EventException;

import fr.mrfern.pumpmysponge.Main;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.api.User;

public class BuildListener {
	
	@SuppressWarnings("unused")
	private Logger logger;

	public BuildListener(Logger logger) {
		this.logger = logger;
	}
	
	@Listener
	public void OnChangeGmSecure(ChangeGameModeEvent.TargetPlayer event,@First Player ply) {
		
		Player target = event.getTargetEntity();
		
		Text textClaimed;
    	Text textEnTete; 
		
		if(event.getGameMode().equals(GameModes.ADVENTURE) & event.getGameMode().equals(GameModes.NOT_SET)) {
			event.setCancelled(true);			
		}else {
			
			if(event.getGameMode().equals(GameModes.CREATIVE) | event.getOriginalGameMode().equals(GameModes.SURVIVAL)) {
				
				// passage de survie à créatif
				
				if(target.hasPermission("group.build") & target.hasPermission("pumpmysafe.build")) {
					
					if(target.getUniqueId().toString().equals(ply.getUniqueId().toString())) {
						
						textClaimed = Text.builder("Changement de gamemod accordé").color(TextColors.YELLOW).build();
					    textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
				    	ply.sendMessage(textEnTete);
				    		
					}else {
							
						textClaimed = Text.builder("Changement de gamemod accordé pour le joueur " + target.getName()).color(TextColors.YELLOW).build();
					    textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
				    	ply.sendMessage(textEnTete);
				    		
				    	textClaimed = Text.builder("Changement de gamemod accordé, fait par le joueur " + ply.getName()).color(TextColors.YELLOW).build();
					    textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
				    	target.sendMessage(textEnTete);
				    		
					}
			    		
			    	//ajout du BuildData 
			    		
			    	if(BuildManager.getHashBuild().containsKey(event.getTargetEntity())) {
			    		// erreur evenement report	
			    		event.setCancelled(true);
			    		target.kick(Text.of("BuildManager Secure Mod"));
			    		BuildManager.getHashBuild().remove(target);
			    		return;
			    	
			    			
			    	}else {	
			    			
			    		target.getInventory().clear();
			    		BuildManager.getHashBuild().put(target, new BuildData(target));		    			
			    		// Ajout de la permission
			    		final LuckPermsApi api = Main.getPermsAPI();		    			
			    		Optional<User> user = api.getUserSafe(target.getUniqueId());
			    			
			    		user.get().setPermission(api.buildNode("minecraft.spawn-protection.override").build());
			    		return;
			    	}
					
				}else {
					
					if(target.getUniqueId().toString().equals(ply.getUniqueId().toString())) {
						
						textClaimed = Text.builder("Changement de gamemod non accordé").color(TextColors.RED).build();
					    textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
				    	ply.sendMessage(textEnTete);
				    		
					}else {
							
						textClaimed = Text.builder("Changement de gamemod non accordé pour le joueur " + target.getName()).color(TextColors.RED).build();
					    textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
				    	ply.sendMessage(textEnTete);
				    		
				    	textClaimed = Text.builder("Changement de gamemod non accordé, fait par le joueur " + ply.getName()).color(TextColors.RED).build();
					    textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
				    	target.sendMessage(textEnTete);
				    	
					}
					
					event.setCancelled(true);
					
				}
				
			}else if(event.getGameMode().equals(GameModes.SURVIVAL) | event.getOriginalGameMode().equals(GameModes.CREATIVE)) {
				
				if(target.hasPermission("group.build") & target.hasPermission("pumpmysafe.build")) {
					
					if(target.getUniqueId().toString().equals(ply.getUniqueId().toString())) {
						
						textClaimed = Text.builder("Changement de gamemod accordé").color(TextColors.YELLOW).build();
					    textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
				    	ply.sendMessage(textEnTete);
				    		
					}else {
							
						textClaimed = Text.builder("Changement de gamemod accordé pour le joueur " + target.getName()).color(TextColors.YELLOW).build();
					    textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
				    	ply.sendMessage(textEnTete);
				    		
				    	textClaimed = Text.builder("Changement de gamemod accordé, fait par le joueur " + ply.getName()).color(TextColors.YELLOW).build();
					    textEnTete = Text.builder("[ PumpMySafe ] ").color(TextColors.GOLD).append(textClaimed).build();
				    	target.sendMessage(textEnTete);
				    		
					}
			    		
			    	//ajout du BuildData 
			    		
			    	if(!BuildManager.getHashBuild().containsKey(event.getTargetEntity())) {
			    		// erreur evenement report
			    		target.getInventory().clear();
			    		target.kick(Text.of("BuildManager Secure Mod"));
			    		BuildManager.getHashBuild().remove(target);
			    		return;			    	
			    			
			    	}else {	
			    			
			    		target.getInventory().clear();
			    		target.getInventory().set(BuildManager.getHashBuild().get(target).getInventory().peek().get());
			    		target.getInventory().intersect(BuildManager.getHashBuild().get(target).getInventory());
			    		BuildManager.getHashBuild().remove(target);		    			
			    		// Ajout de la permission
			    		final LuckPermsApi api = Main.getPermsAPI();		    			
			    		Optional<User> user = api.getUserSafe(target.getUniqueId());
			    			
			    		user.get().unsetPermission(api.buildNode("minecraft.spawn-protection.override").build());
			    		return;
			    	}
					
				}else {
					// report 
					target.getInventory().clear();
		    		target.kick(Text.of("BuildManager Secure Mod"));
		    		BuildManager.getHashBuild().remove(target);
		    		return;	
					
				}				
			}
		}
	}
}
