package fr.mrfern.spongeplugintest.command.tp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.Optional;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class TpacceptCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		if (src instanceof Player) {
			
			Player sender = (Player) src;
			HashMap<UUID, TeleportData> hm = TpaCommandManager.getTpHM();
			UUID uuid = sender.getUniqueId();
			@SuppressWarnings("unused")
			boolean tp = false;
			
			for(Iterator<Entry<UUID, TeleportData>> iter = hm.entrySet().iterator(); iter.hasNext(); ) {
				Entry<UUID, TeleportData> entry = iter.next();
				TeleportData data = entry.getValue();
				
				if((System.currentTimeMillis()-data.getTimestamp()) >= 90000 ) {
					iter.remove(); // C'est censé remove les request périmées de la hashmap
				}
				
				if(entry.getKey().equals(uuid)) {
					Optional<Player> target = Sponge.getServer().getPlayer(data.getSender());
					Location<World> tppos = sender.getLocation();
					target.get().setLocation(tppos);
					iter.remove();
					tp = true;
					break;
				}
			}
			if(tp = false) {
				sender.sendMessage(Text.of("Vous n'avez pas reçu de requête de téléportation ou cette dernière à expiré"));
			}
			TpaCommandManager.setTpHM(hm);
		}
		return CommandResult.success();
	}

}
