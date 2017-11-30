package fr.mrfern.pumpmysponge.player.ban.commands;

import java.util.HashMap;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.CommandBlockSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import fr.mrfern.pumpmysponge.config.PlayerConfig;
import fr.mrfern.pumpmysponge.config.PlayerNode;

public class UnBanCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
if(src instanceof Player) {
		    
			Player ply = (Player) src;
			Player target;
			
			Integer day = 0, hour = 0,minute = 0;
			String raison;
			
			HashMap<TimeEnum, Integer> hashTime = new HashMap<>();
			
			if(args.<Player>getOne("player").isPresent()) {
				target = args.<Player>getOne("player").get();
			}else {
				 
				Text textClaimed = Text.builder("Le joueur spécifié n'est pas valide ").color(TextColors.RED).build();
		    	Text textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textClaimed).build();
	    		ply.sendMessage(textEnTete);
			    return CommandResult.empty();
			}	
					
			PlayerNode targetNode = PlayerConfig.getInstance().getPlayerConfigNode(target.getUniqueId());
			
			if(targetNode.getIsBanned()) {
				
				targetNode.revokeBanANDAddToList();
				targetNode.save();
				
			}else {
				
				Text textClaimed = Text.builder("Le joueur spécifié n'est pas banni ").color(TextColors.RED).build();
		    	Text textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textClaimed).build();
	    		ply.sendMessage(textEnTete);
			    return CommandResult.empty();
			    
			}
				
			
		}
		else if(src instanceof ConsoleSource | src instanceof CommandBlockSource){
			
		}
		
		return CommandResult.empty();
		
	}

}
