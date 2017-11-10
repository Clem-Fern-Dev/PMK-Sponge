package fr.mrfern.spongeplugintest.command.tp;

import java.util.HashMap;
import java.util.UUID;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import fr.mrfern.spongeplugintest.command.tp.TeleportData;

public class TpaCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if(src instanceof Player) {
			Player sender = (Player) src;
			Player target = (Player) args.getOne("player").get();
				
			HashMap<UUID,TeleportData> hashmap = TpaCommandManager.getTpHM();
			hashmap.put(target.getUniqueId(), new TeleportData(sender.getUniqueId(), System.currentTimeMillis()));
			
			
		}
		else {
			src.sendMessage(Text.of("Tu n\'est pas un joueur"));
		}
		return CommandResult.success();
	}

}
