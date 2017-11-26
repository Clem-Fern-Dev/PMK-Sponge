package fr.mrfern.pumpmysponge.player.commands;

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

import fr.mrfern.pumpmysponge.player.IPermissions;

public class TBanCommand implements CommandExecutor,IPermissions {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		boolean all = false;
		
		if(src instanceof Player) {
		    
			Player ply = (Player) src;
			
			if(!checkPermissions(ply, "")) {
				
				Text text = Text.builder("[ PumpMyStaff ]").color(TextColors.GOLD).build();
				ply.sendMessage(text );
				
			}
			
			Player player;
			if(args.<Player>getOne("player").isPresent()) {
				player = args.<Player>getOne("player").get();
			}else {
				//Text textClaimed = Text.builder("Le joueur spécifié n'est pas valide ").color(TextColors.RED).build();
		    	//Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.DARK_BLUE).append(textPosX,textSlasher,textPosZ,textEnd,textClaimed).build();
	    		//ply.sendMessage(textEnTete);
			    return CommandResult.empty();
			}
			
			
			
			
		}
		else if(src instanceof ConsoleSource | src instanceof CommandBlockSource){
			
		}
		
		return CommandResult.empty();
		
	}

}
