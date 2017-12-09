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
		
		String targetMessage = new String("Vous avez été mute "+hour+"h "+minute+"min");
		target.sendMessage(Text.of(targetMessage));
		String srcMessage = new String(target.getName()+" à été mute");
		src.sendMessage(Text.of(srcMessage));
		
		hashmap.put(target.getUniqueId(), data);
		MuteCommandManager.setMuteHM(hashmap);
		
		
		return null;
	}

}
