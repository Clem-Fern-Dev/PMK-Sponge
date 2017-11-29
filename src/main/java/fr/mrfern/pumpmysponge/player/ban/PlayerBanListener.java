package fr.mrfern.pumpmysponge.player.ban;

import java.util.UUID;

import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import fr.mrfern.pumpmysponge.config.PlayerConfig;
import fr.mrfern.pumpmysponge.config.PlayerNode;


public class PlayerBanListener{

	/* ClientConnectionEvent.Auth
	 * ClientConnectionEvent.Disconnect
	 * ClientConnectionEvent.Join
	 * ClientConnectionEvent.Login
	 */
	
	@SuppressWarnings("unused")
	private Logger logger;
	
	public PlayerBanListener(Logger logger) {
		this.logger = logger;
	}

	@Listener
	public void OnClientAuth(ClientConnectionEvent.Login e) {		
		if(isBanned(e.getProfile().getUniqueId())) {
			
			//logger.info(e.getConnection().getAddress().getAddress().getHostAddress() + " auth");
			//System.out.println(e.getConnection().getAddress().getAddress().getHostAddress() + " auth");
			
			Text header,body,footer,raison,ban,time_Day,time_Hour,time_Minut,time_Day_format,time_Hour_format,time_Minut_format;
			
			PlayerNode plyNode = PlayerConfig.getInstance().getPlayerConfigNode(e.getProfile().getUniqueId());
			
			header = Text.builder("▂▄▅▆▇█ Vous avez été banni █▇▆▅▄▂").color(TextColors.GOLD).build();
			raison = Text.builder(plyNode.getPlayerBanRaison()).color(TextColors.BLUE).build();	
			
			
			time_Day_format = Text.builder(" D / ").color(TextColors.GOLD).build();	
			time_Hour_format = Text.builder(" H / ").color(TextColors.GOLD).build();	
			time_Minut_format = Text.builder(" M ").color(TextColors.GOLD).build();	
			
			time_Day = Text.builder(plyNode.getPlayerBanTimeDay()+"").color(TextColors.AQUA).append(time_Day_format).build();	
			time_Hour = Text.builder(plyNode.getPlayerBanTimeHour()+"").color(TextColors.AQUA).append(time_Hour_format).build();	
			time_Minut = Text.builder(plyNode.getPlayerBanTimeMinut()+"").color(TextColors.AQUA).append(time_Minut_format).build();	
			
			ban = Text.builder(plyNode.getBanAuthorName() + " [ " + plyNode.getBanAuthorUUIDString() + " ]").color(TextColors.DARK_RED).build();	
			
			
			
			body = Text.builder("\n[ Raison ] ").color(TextColors.GOLD).append(raison).build();
			
			Text head_footer = Text.builder("\n[Banni par : ").color(TextColors.GOLD).append(ban).build();
			Text second_head_footer = Text.builder(" ][ Temps restant : ").color(TextColors.GOLD).append(time_Day,time_Hour,time_Minut,Text.builder(" ]").color(TextColors.GOLD).build()).build();
			
			footer = Text.builder().append(head_footer,second_head_footer).build();
			
			e.clearMessage();
			e.setMessage(header,body,footer);	
			e.setCancelled(true);
			
		}
	}
	
	private boolean isBanned(UUID uniqueId) {
		if(PlayerConfig.getInstance().getPlayerConfigNode(uniqueId) != null) {
			return PlayerConfig.getInstance().getPlayerConfigNode(uniqueId).getIsBanned();
		}
		return false;
		
	}
	
}
