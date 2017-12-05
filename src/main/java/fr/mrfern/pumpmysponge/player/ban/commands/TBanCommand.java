package fr.mrfern.pumpmysponge.player.ban.commands;

import java.util.Calendar;
import java.util.Date;
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
			
			if(args.<Player>getOne("player").isPresent()) {
				target = args.<Player>getOne("player").get();
				if(target.getUniqueId().equals(ply.getUniqueId())) {
					ply.kick(Text.of("Ouf je viens de te sauvé la vie, heuresement que je suis là !"));
					return CommandResult.success();
				}else if(PlayerConfig.getInstance().getPlayerConfigNode(target.getUniqueId()).getIsBanned()){
					
					Text textClaimed = Text.builder("Le joueur spécifié est déjà banni ").color(TextColors.RED).build();
			    	Text textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textClaimed).build();
		    		ply.sendMessage(textEnTete);
				    return CommandResult.empty();
				    
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
				
				if(day < 0| hour < 0 | hour > 23 | minute < 0 | minute > 59) {
					Text textClaimed = Text.builder("Les heures doivent être compris entre 0 et 23, les minutes doivent être comprises entre 0 et 59 !").color(TextColors.RED).build();
			    	Text textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textClaimed).build();
		    		ply.sendMessage(textEnTete);
				    return CommandResult.empty();
				}
				
				if(args.<String>getOne("raison").isPresent()) {
					
					raison = args.<String>getOne("raison").get();
					
					if(raison.isEmpty() | raison.trim().isEmpty() | raison.length() < 8) {
						
						Text textClaimed = Text.builder("Il faut une raison valide ( non vide, plus de 8 caractères )").color(TextColors.RED).build();
				    	Text textEnTete = Text.builder("[ PumpMyStaff ] ").color(TextColors.GOLD).append(textClaimed).build();
			    		ply.sendMessage(textEnTete);
					    return CommandResult.empty();
					    
					}	
					
					PlayerNode targetNode = PlayerConfig.getInstance().getPlayerConfigNode(target.getUniqueId());
					
					// joueur ban
					targetNode.setPlayerIsBanned(true);	 
					targetNode.setPlayerBanRaison(raison);
					
					// ajout date de début bannissement A FAIRE
					Date now = new Date();				
					Calendar c = Calendar.getInstance();
					c.setTime(now);
					
					targetNode.setBeginTimeBanYear(c.get(Calendar.YEAR));
					targetNode.setBeginTimeBanMonth(c.get(Calendar.MONTH));
					targetNode.setBeginTimeBanDay(c.get(Calendar.DATE));
					targetNode.setBeginTimeBanHour(c.get(Calendar.HOUR_OF_DAY));
					targetNode.setBeginTimeBanMinute(c.get(Calendar.MINUTE));
					
					// temps de bannissement
					targetNode.setPlayerBanTimeDay(day);
					targetNode.setPlayerBanTimeHour(hour);
					targetNode.setPlayerBanTimeMinut(minute);
					
					// ajout date de fin bannissement
					//targetNode.calculEndTime();
											
					// info du bannisseur
					targetNode.setPlayerBanAuthorName(ply.getName());
					targetNode.setPlayerBanAuthorUUID(ply.getUniqueId());
						
					targetNode.save();
					
					targetNode.calculEndTime();
					
					targetNode.save();
						
					Text header,body,footer,text_raison,ban,time_year,time_month,time_Day,time_Hour,time_Minut,time_year_format,time_month_format,time_Day_format,time_Hour_format,time_Minut_format;
						
					header = Text.builder("\n▂▄▅▆▇█ Vous avez été banni █▇▆▅▄▂").color(TextColors.GOLD).build();
					text_raison = Text.builder(targetNode.getPlayerBanRaison()).color(TextColors.BLUE).build();	
							
					time_year_format = Text.builder(" Y / ").color(TextColors.GOLD).build();	
					time_month_format = Text.builder(" M / ").color(TextColors.GOLD).build();	
					time_Day_format = Text.builder(" D    ").color(TextColors.GOLD).build();	
					time_Hour_format = Text.builder(" H : ").color(TextColors.GOLD).build();	
					time_Minut_format = Text.builder(" m ").color(TextColors.GOLD).build();	
					
					time_year = Text.builder(targetNode.getEndTimeBanYear()+"").color(TextColors.AQUA).append(time_year_format).build();	
					time_month = Text.builder((targetNode.getEndTimeBanMonth() + 1)+"").color(TextColors.AQUA).append(time_month_format).build();	
					time_Day = Text.builder(targetNode.getEndTimeBanDay()+"").color(TextColors.AQUA).append(time_Day_format).build();	
					time_Hour = Text.builder(targetNode.getEndTimeBanHour()+"").color(TextColors.AQUA).append(time_Hour_format).build();	
					time_Minut = Text.builder(targetNode.getEndTimeBanMinute()+"").color(TextColors.AQUA).append(time_Minut_format).build();
						
					ban = Text.builder(ply.getName() + " [ " + ply.getUniqueId() + " ]").color(TextColors.DARK_RED).build();	
							
					body = Text.builder("\n[ Raison ] ").color(TextColors.GOLD).append(text_raison).build();
						
					Text head_footer = Text.builder("\n[Banni par : ").color(TextColors.GOLD).append(ban).build();
					Text second_head_footer = Text.builder(" ]\n[ Date de débannissement : ").color(TextColors.GOLD).append(time_year,time_month,time_Day,time_Hour,time_Minut,Text.builder(" ]").color(TextColors.GOLD).build()).build();
						
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
