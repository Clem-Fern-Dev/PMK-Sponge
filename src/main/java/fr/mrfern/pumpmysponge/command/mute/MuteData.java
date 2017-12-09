package fr.mrfern.pumpmysponge.command.mute;

public class MuteData {
	private long unmuteTimestamp;
	private String raison;
	
	public MuteData(long unmuteTimestamp, String raison){
		setUnmuteTimestamp(unmuteTimestamp);
		setRaison(raison);
	}
	public long getUnmuteTimestamp() {
		return unmuteTimestamp;
	}
	public String getRaison() {
		return raison;
	}
	public void setUnmuteTimestamp(long timeRemaining) {
		this.unmuteTimestamp = timeRemaining;
	}
	public void setRaison(String raison) {
		this.raison = raison;
	}

}
