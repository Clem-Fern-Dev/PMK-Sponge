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
import org.spongepowered.api.text.format.TextColors;

public class UnmuteCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		Player target = args.<Player>getOne("player").get();
		HashMap<UUID, MuteData> hashmap = MuteCommandManager.getMuteHM();
		
		hashmap.remove(target.getUniqueId());
		target.sendMessage(Text.builder("Vous avez été démute").color(TextColors.RED).build());
		Text okmessage = Text.builder(target.getName()+" à été démute").color(TextColors.RED).build();
		src.sendMessage(Text.of(okmessage));
		MuteCommandManager.setMuteHM(hashmap);
		
		return null;
	}

}
