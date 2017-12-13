package fr.mrfern.pimpmysponge.command.tp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.UUID;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class TpaCommand implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private static Boolean bool;
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if(src instanceof Player) {
			Player sender = (Player) src;
			Player target = (Player) args.<Player>getOne("target").get();
			// recuperation de la cible
				
			HashMap<UUID,TeleportData> hashmap = TpaCommandManager.getTpHM();
			if(sender.equals(target)) {
				Text message = Text.builder("Tu ne peux pas te téléporter à toi même").color(TextColors.RED).build();
				sender.sendMessage(message);
			}
			
			for(Iterator<Entry<UUID, TeleportData>> iter = hashmap.entrySet().iterator(); iter.hasNext(); ) {
				Entry<UUID, TeleportData> entry = iter.next();
				TeleportData data = entry.getValue();
				
				if((System.currentTimeMillis()-data.getTimestamp()) >= 90000 ) {
					iter.remove(); // C'est censé remove les request périmées de la hashmap
				}
				else if(data.getSender().equals(sender.getUniqueId())) {
					iter.remove();
					Text message = Text.builder("Votre reqûete de téléportation à été envoyée").color(TextColors.AQUA).build();
					sender.sendMessage(message);
					bool = true;
					break;
				}
				else {
					Text message = Text.builder("Votre reqûete de téléportation à été envoyée").color(TextColors.AQUA).build();
					sender.sendMessage(message);
					bool = true;
					break;
				}
			}
			if(bool = true) {
				hashmap.put(target.getUniqueId(), new TeleportData(sender.getUniqueId(), System.currentTimeMillis()));
			}
			TpaCommandManager.setTpHM(hashmap);
			Text message = Text.builder(sender.getName()+" vous a envoyé une demande de téléportation. \n /tpaccept pour accepter").color(TextColors.AQUA).build();
			target.sendMessage(message);
			
		}
		else {
			src.sendMessage(Text.of("Tu n\'est pas un joueur"));
		}
		return CommandResult.success();
	}

}
