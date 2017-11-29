package fr.mrfern.pumpmysponge.player.ban.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.CommandBlockSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

public class UnBanCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		@SuppressWarnings("unused")
		boolean all = false;
		
		if(src instanceof Player) {
		    
			if (args.hasAny("all")) {
			   // alors ban all
			}
			
		}
		else if(src instanceof ConsoleSource | src instanceof CommandBlockSource){
			
		}
		
		return CommandResult.empty();
		
	}

}
