package fr.mrfern.pumpmysponge.command.mute;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.UUID;

import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.message.MessageChannelEvent;

public class MuteListener {
	
	HashMap<UUID,MuteData> hashmap = MuteCommandManager.getMuteHM();
	
	@SuppressWarnings("unused")
	private Logger logger;
	
	public MuteListener(Logger logger) {
		this.logger = logger;
	}
	@Listener
	static void onPlayerMessage(MessageChannelEvent.Chat e, @First Player player) {
		
	}
	
	private boolean isMute(UUID uuid) {	
		for(Iterator<Entry<UUID, MuteData>> iter = hashmap.entrySet().iterator(); iter.hasNext(); ) {
			Entry<UUID, MuteData> entry = iter.next();
			MuteData data = entry.getValue();
			
			if(System.currentTimeMillis() > data.getUnmuteTimestamp() ) {
				iter.remove(); // C'est censé remove les mute périmés
				return false;
			}
			else if(entry.getKey().equals(uuid) && System.currentTimeMillis() < data.getUnmuteTimestamp()) {
				return true;
			}
		}
		return false;
	}

}
