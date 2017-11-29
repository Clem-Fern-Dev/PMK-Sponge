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
			
			Integer day = 0, hour = 0,minute = 0;
			String raison;
			
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
			
			if(!args.<Integer>getOne("day").isPresent() | !args.<Integer>getOne("hour").isPresent() | !args.<Integer>getOne("minute").isPresent()) {
								
				
				Text textClaimed = Text.builder("Aucun temps n'a été spécifié ou le format D H M n'est pas respcté !").color(TextColors.RED).build();
		    	Text textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textClaimed).build();
	    		ply.sendMessage(textEnTete);
			    return CommandResult.empty();
				
			}else {
				
				day = args.<Integer>getOne("day").get();
				hour = args.<Integer>getOne("hour").get();
				minute = args.<Integer>getOne("minute").get();
				
				hashTime.put(TimeEnum.Day, day);
				hashTime.put(TimeEnum.Hour, hour);
				hashTime.put(TimeEnum.Minute, minute);
				
				if(args.<String>getOne("raison").isPresent()) {
					
					raison = args.<String>getOne("raison").get();
					
					if(raison.isEmpty() | raison.trim().isEmpty() | raison.length() < 8) {
						
						Text textClaimed = Text.builder("Il faut une raison valide ( non vide, plus de 8 caractères )").color(TextColors.RED).build();
				    	Text textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textClaimed).build();
			    		ply.sendMessage(textEnTete);
					    return CommandResult.empty();
					    
					}	
					
					PlayerNode targetNode = PlayerConfig.getInstance().getPlayerConfigNode(target.getUniqueId());
					
					/*		
					loaderRootNode.getNode(name,"ban","author","UUID").setValue("none");
					loaderRootNode.getNode(name,"ban","author","name").setValue("none");
					 */
					
					// joueur ban
					targetNode.setPlayerIsBanned(true);	 
					targetNode.setPlayerBanRaison(raison);
					
					// ajout date de fin bannissement A FAIRE
					
					targetNode.setEndTimeBanYear(0);
					targetNode.setEndTimeBanMonth(0);
					targetNode.setEndTimeBanDay(0);
					targetNode.setEndTimeBanHour(0);
					targetNode.setEndTimeBanMinute(0);
					
					// temps de bannissement
					targetNode.setPlayerBanTimeDay(hashTime.get(TimeEnum.Day));
					targetNode.setPlayerBanTimeHour(hashTime.get(TimeEnum.Hour));
					targetNode.setPlayerBanTimeMinut(hashTime.get(TimeEnum.Minute));
											
					// info du bannisseur
					targetNode.setPlayerBanAuthorName(ply.getName());
					targetNode.setPlayerBanAuthorUUID(ply.getUniqueId());
						
					targetNode.save();
						
					Text header,body,footer,text_raison,text_ban,text_time_Day,text_time_Hour,text_time_Minut,time_Day_format,time_Hour_format,time_Minut_format;
						
					header = Text.builder("▂▄▅▆▇█ Vous avez été banni █▇▆▅▄▂").color(TextColors.GOLD).build();
					text_raison = Text.builder(raison).color(TextColors.BLUE).build();	
						
						
					time_Day_format = Text.builder(" D / ").color(TextColors.GOLD).build();	
					time_Hour_format = Text.builder(" H / ").color(TextColors.GOLD).build();	
					time_Minut_format = Text.builder(" M ").color(TextColors.GOLD).build();	
						
					text_time_Day = Text.builder(hashTime.get(TimeEnum.Day)+"").color(TextColors.AQUA).append(time_Day_format).build();	
					text_time_Hour = Text.builder(hashTime.get(TimeEnum.Hour)+"").color(TextColors.AQUA).append(time_Hour_format).build();	
					text_time_Minut = Text.builder(hashTime.get(TimeEnum.Minute)+"").color(TextColors.AQUA).append(time_Minut_format).build();	
						
					text_ban = Text.builder(ply.getName() + " [ " + ply.getUniqueId() + " ]").color(TextColors.DARK_RED).build();	
						
						
						
					body = Text.builder("\n[ Raison ] ").color(TextColors.GOLD).append(text_raison).build();
						
					Text head_footer = Text.builder("\n[Banni par : ").color(TextColors.GOLD).append(text_ban).build();
					Text second_head_footer = Text.builder(" ][ Temps restant : ").color(TextColors.GOLD).append(text_time_Day,text_time_Hour,text_time_Minut,Text.builder(" ]").color(TextColors.GOLD).build()).build();
						
					footer = Text.builder().append(head_footer,second_head_footer).build();
						
					target.kick(Text.builder().append(header,body,footer).build());
					
					// AJout au logs ( ban )
					
					return CommandResult.success();
		
				}else {
					
					Text textClaimed = Text.builder("Il faut une raison valide ( non vide, plus de 8 caractères )").color(TextColors.RED).build();
			    	Text textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textClaimed).build();
		    		ply.sendMessage(textEnTete);
				    return CommandResult.empty();
				    
				}			
				
			}
			
		}
		else if(src instanceof ConsoleSource | src instanceof CommandBlockSource){
			
		}
		
		return CommandResult.empty();
		
	}
}
