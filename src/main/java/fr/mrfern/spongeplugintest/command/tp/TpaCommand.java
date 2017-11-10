package fr.mrfern.spongeplugintest.command.tp;

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

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if(src instanceof Player) {
			Player sender = (Player) src;
			Player target = (Player) args.getOne("player").get();
				
			HashMap<UUID,TeleportData> hashmap = TpaCommandManager.getTpHM();
			
			for(Iterator<Entry<UUID, TeleportData>> iter = hashmap.entrySet().iterator(); iter.hasNext(); ) {
				Entry<UUID, TeleportData> entry = iter.next();
				TeleportData data = entry.getValue();
				
				if((System.currentTimeMillis()-data.getTimestamp()) >= 90000 ) {
					iter.remove(); // C'est censé remove les request périmées de la hashmap
					break;
				}
				else if(data.getSender().equals(sender.getUniqueId())) {
					sender.sendMessage(Text.of("Votre requête de téléportation à déjà été envoyée"));
					break;
				}
				else {
					//ici je veux faire ce qui est juste en dessous mais j'y ai pas acces donc je sais pas comment faire
				}
			}
			
			hashmap.put(target.getUniqueId(), new TeleportData(sender.getUniqueId(), System.currentTimeMillis()));
			
			
		}
		else {
			src.sendMessage(Text.of("Tu n\'est pas un joueur"));
		}
		return CommandResult.success();
	}

}
