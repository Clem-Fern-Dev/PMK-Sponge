package fr.mrfern.pumpmysponge.player.ban.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.CommandBlockSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;

import fr.mrfern.pumpmysponge.player.IPermissions;

public class TBanCommand implements CommandExecutor,IPermissions {

	private Object AcceptOrNo = true;
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		boolean all = false;
		
		if(src instanceof Player) {
		    
			Player ply = (Player) src;
			
			Player target;
			String time;
			HashMap<TimeEnum, String> hashTime = new HashMap<>();
			
			if(args.<Player>getOne("player").isPresent()) {
				target = args.<Player>getOne("player").get();
			}else {
				
				Text textClaimed = Text.builder("Le joueur spécifié n'est pas valide ").color(TextColors.RED).build();
		    	Text textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textClaimed).build();
	    		ply.sendMessage(textEnTete);
			    return CommandResult.empty();
			}
			
			if(args.<Player>getOne("time").isPresent()) {
				
				time = args.<String>getOne("time").get();
				
				ply.sendMessage(Text.of(time));
				
				String[] listTime = time.split("");
				
				for (String splitedTime : listTime) {
					if(!splitedTime.endsWith("Y") & !splitedTime.endsWith("M") & !splitedTime.endsWith("D") & !splitedTime.endsWith("H") &!splitedTime.endsWith("M")) {
						
						Text textClaimed = Text.builder("Le temps spécifié n'est pas valide, faite /bhelp pour voir les commandes , le temps").color(TextColors.RED).build();
				    	Text textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textClaimed).build();
			    		ply.sendMessage(textEnTete);
					    return CommandResult.empty();
						
					}
				}
				
				
			}else{
				
				Text textClaimed = Text.builder("Le temps spécifié n'est pas valide, faite /bhelp pour voir les commandes").color(TextColors.RED).build();
		    	Text textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textClaimed).build();
	    		ply.sendMessage(textEnTete);
			    return CommandResult.empty();
			}
			
			
			
						
			
			return CommandResult.empty();
			
		}
		else if(src instanceof ConsoleSource | src instanceof CommandBlockSource){
			
		}
		
		return CommandResult.empty();
		
	}
	
	public HashMap<TimeEnum, String> SplitTimer(String timer) {
		return null;		
	}
}
