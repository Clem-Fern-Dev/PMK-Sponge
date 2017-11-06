package fr.efkabe.spongeplugintest.command;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class MessageCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if( src instanceof Player) {
	    	Player sender = (Player) src;
	    	Player player = (Player) args.getOne("player").get();
	    	String message = (String) args.getOne("message").get();
	    	
	    	sender.sendMessage(Text.of("[me->"+ player.getName()+"] " + message));
	    	player.sendMessage(Text.of("["+sender.getName()+" -> me] " + message));
	    	}
	        return CommandResult.success();
	}

}
