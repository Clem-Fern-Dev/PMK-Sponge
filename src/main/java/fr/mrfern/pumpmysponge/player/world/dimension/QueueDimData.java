package fr.mrfern.pumpmysponge.player.world.dimension;

import java.util.ArrayList;
import org.spongepowered.api.entity.living.player.Player;

public class QueueDimData {

	
	
	private Player creatorPlayer;
	private ArrayList<Player> plyList;

	public QueueDimData(Player creator) {
		setCreatorPlayer(creator);
		plyList = new ArrayList<>();
	}

	public Player getCreatorPlayer() {
		return creatorPlayer;
	}

	public void setCreatorPlayer(Player creatorPlayer) {
		this.creatorPlayer = creatorPlayer;
	}

	public ArrayList<Player> getPlyList() {
		return plyList;
	}

	public void setPlyList(ArrayList<Player> plyList) {
		this.plyList = plyList;
	}
	
}
