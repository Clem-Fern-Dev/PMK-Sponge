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
				sender.sendMessage(Text.of("Tu ne peux pas te téléporter à toi même"));
			}
			
			for(Iterator<Entry<UUID, TeleportData>> iter = hashmap.entrySet().iterator(); iter.hasNext(); ) {
				Entry<UUID, TeleportData> entry = iter.next();
				TeleportData data = entry.getValue();
				
				if((System.currentTimeMillis()-data.getTimestamp()) >= 90000 ) {
					iter.remove(); // C'est censé remove les request périmées de la hashmap
				}
				else if(data.getSender().equals(sender.getUniqueId())) {
					iter.remove();
					sender.sendMessage(Text.of("Votre requête de téléportation à été envoyée"));
					bool = true;
					break;
				}
				else {
					sender.sendMessage(Text.of("Votre requête de téléportation à été envoyée"));
					bool = true;
					break;
				}
			}
			if(bool = true) {
				hashmap.put(target.getUniqueId(), new TeleportData(sender.getUniqueId(), System.currentTimeMillis()));
			}
			TpaCommandManager.setTpHM(hashmap);
			target.sendMessage(Text.of(sender.getName()+" vous a envoyé une demande de teleportation \n /tpaccept pour accepter"));
			
		}
		else {
			src.sendMessage(Text.of("Tu n\'est pas un joueur"));
		}
		return CommandResult.success();
	}

}
