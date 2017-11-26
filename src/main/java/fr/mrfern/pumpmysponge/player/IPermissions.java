package fr.mrfern.pumpmysponge.player;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public interface IPermissions {

	public default boolean checkPermissions(Player p,String perm) {
		if(!p.hasPermission(perm)) {
			
			Text text = Text.builder("[ PumpMyStaff ]").color(TextColors.GOLD).append(Text.builder(" Vous n'avez pas l'autorisation " + perm + " pour faire celà").color(TextColors.RED).build()).build();
			p.sendMessage(text );
			return false;
			
		}
		return true;
	}
	
}
