package fr.mrfern.spongeplugintest.command.tp;

import java.util.UUID;

public class TeleportData {
	private UUID sender;
	private long timestamp;
	
	public TeleportData(UUID player, long time) {
		setSender(player);
		setTimestamp(time);
	}

	public UUID getSender() {
		return sender;
	}

	public void setSender(UUID target) {
		this.sender = target;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long time) {
		this.timestamp = time;
	}

}
