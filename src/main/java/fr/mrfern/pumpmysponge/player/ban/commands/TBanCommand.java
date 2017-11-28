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
import fr.mrfern.pumpmysponge.player.IPermissions;

public class TBanCommand implements CommandExecutor,IPermissions {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		if(src instanceof Player) {
		    
			Player ply = (Player) src;
			Player target;
			
			String time;
			HashMap<TimeEnum, Integer> hashTime = new HashMap<>();
			
			if(args.<Player>getOne("player").isPresent()) {
				target = args.<Player>getOne("player").get();
				if(target.getUniqueId().equals(ply.getUniqueId())) {
					ply.kick(Text.of("Ouf je viens de te sauvé la vie, heuresement que je suis là !"));
					return CommandResult.success();
				}
			}else {
				
				Text textClaimed = Text.builder("Le joueur spécifié n'est pas valide ").color(TextColors.RED).build();
		    	Text textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textClaimed).build();
	    		ply.sendMessage(textEnTete);
			    return CommandResult.empty();
			}
			
			if(args.<Player>getOne("time").isPresent()) {
				
				time = args.<String>getOne("time").get();
			
				hashTime.clear();
				
				String[] listTime = time.split(" ");
				
				for (String splitedTime : listTime) {
					if(!splitedTime.endsWith("D") & !splitedTime.endsWith("H") &!splitedTime.endsWith("M")) {
						
						Text textClaimed = Text.builder("Le temps spécifié n'est pas valide, faite /bhelp pour voir les commandes , le temps").color(TextColors.RED).build();
				    	Text textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textClaimed).build();
			    		ply.sendMessage(textEnTete);
					    return CommandResult.empty();
						
					}else {
						switch (splitedTime.charAt(splitedTime.length()-1)) {
							
							case 'D':
									hashTime.putIfAbsent(TimeEnum.Day, Integer.parseInt(splitedTime.substring(0, splitedTime.length()-1)));
								break;
							case 'H':
									hashTime.putIfAbsent(TimeEnum.Hour, Integer.parseInt(splitedTime.substring(0, splitedTime.length()-1)));
								break;
							case 'M':
								hashTime.putIfAbsent(TimeEnum.Minute, Integer.parseInt(splitedTime.substring(0, splitedTime.length()-1)));
								break;
						}
					}
				}
				
				PlayerNode targetNode = PlayerConfig.getInstance().getPlayerConfigNode(target.getUniqueId());
				
				// joueur ban
				targetNode.setPlayerIsBanned(true);	 
				
				// ajout des infos
				targetNode.setPlayerBanRaison("raison");	// raison du bannissement
				
				// temps de bannissement
				targetNode.setPlayerBanTimeDay(hashTime.get(TimeEnum.Day));
				targetNode.setPlayerBanTimeHour(hashTime.get(TimeEnum.Hour));
				targetNode.setPlayerBanTimeMinut(hashTime.get(TimeEnum.Minute));
				
				// info du bannisseur
				targetNode.setPlayerBanAuthorName(ply.getName());
				targetNode.setPlayerBanAuthorUUID(ply.getUniqueId());
				targetNode.setPlayerBanAuthorUUID(ply.getUniqueId());
				
				//ply.sendMessage(Text.of(target.getName() + "  " + hashTime.get(TimeEnum.Day) + "  " + hashTime.get(TimeEnum.Hour) + "  " + hashTime.get(TimeEnum.Minute)));
				return CommandResult.success();
				
			}else{
				
				Text textClaimed = Text.builder("Le temps spécifié n'est pas valide, faite /bhelp pour voir les commandes").color(TextColors.RED).build();
		    	Text textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textClaimed).build();
	    		ply.sendMessage(textEnTete);
			    return CommandResult.empty();
			}
			
		}
		else if(src instanceof ConsoleSource | src instanceof CommandBlockSource){
			
		}
		
		return CommandResult.empty();
		
	}
	
	public HashMap<TimeEnum, String> SplitTimer(String timer) {
		return null;		
	}
}
