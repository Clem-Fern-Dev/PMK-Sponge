package fr.mrfern.pumpmysponge.player;

import org.spongepowered.api.entity.living.player.Player;

public interface IPermissions {

	public default boolean checkPermissions(Player p,String perm) {
		if(!p.hasPermission(perm)) {
			
		}
		return false;
	}
	
}
