package fr.mrfern.pumpmysponge.command.mute;

import java.util.HashMap;
import java.util.UUID;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class UnmuteCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		Player target = args.<Player>getOne("player").get();
		HashMap<UUID, MuteData> hashmap = MuteCommandManager.getMuteHM();
		
		hashmap.remove(target.getUniqueId());
		target.sendMessage(Text.of("Vous avez été démute"));
		String okmessage = new String(target.getName()+" à été démute");
		src.sendMessage(Text.of(okmessage));
		MuteCommandManager.setMuteHM(hashmap);
		
		return null;
	}

}
