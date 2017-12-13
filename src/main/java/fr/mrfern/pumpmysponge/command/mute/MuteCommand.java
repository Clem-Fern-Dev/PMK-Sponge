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

public class MuteCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		Player target = args.<Player>getOne("player").get();
		int hour = args.<Integer>getOne("hour").get();
		int minute = args.<Integer>getOne("minute").get();
		String raison = args.<String>getOne("raison").get();
		HashMap<UUID, MuteData> hashmap = MuteCommandManager.getMuteHM();
		
		long unmuteTimestamp = System.currentTimeMillis()+(hour*60+minute)*60000;
		MuteData data = new MuteData(unmuteTimestamp, raison);
		
		Text targetMessage = Text.builder("Vous avez été mute "+hour+"h "+minute+"min pour : "+raison)
								 .color(TextColors.RED)
								 .build();
		target.sendMessage(targetMessage);
		Text srcMessage = Text.builder(target.getName()+" à été mute")
							  .color(TextColors.RED)
							  .build();
		src.sendMessage(srcMessage);
		
		hashmap.put(target.getUniqueId(), data);
		MuteCommandManager.setMuteHM(hashmap);
		
		
		return null;
	}

}
