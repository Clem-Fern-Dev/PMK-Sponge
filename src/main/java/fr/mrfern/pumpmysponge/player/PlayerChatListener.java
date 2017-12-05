package fr.mrfern.pumpmysponge.player;

import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.text.Text;

public class PlayerChatListener {

	@SuppressWarnings("unused")
	private Logger logger;
	
	public PlayerChatListener(Logger logger) {
		this.logger = logger;
	}
	
	@Listener
	public void onPlayerSendMessage(MessageChannelEvent.Chat event) {
		event.setMessage(Text.of("concel message"));
	}
	

}
