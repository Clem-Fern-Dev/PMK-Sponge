package fr.mrfern.pumpmysponge.player.ban;

import java.util.Calendar;
import java.util.Date;
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
			
			PlayerNode plyNode = PlayerConfig.getInstance().getPlayerConfigNode(e.getProfile().getUniqueId());
			
			// verification de la date de fin
			
			Date now = new Date();				
			Calendar c = Calendar.getInstance();
			c.setTime(now);
			
			int today_year = c.get(Calendar.YEAR);
			int today_month = c.get(Calendar.MONTH);
			int today_day = c.get(Calendar.DAY_OF_MONTH);
			int today_hour = c.get(Calendar.HOUR_OF_DAY);
			int today_minute = c.get(Calendar.MINUTE);
			
			//si toujours ban alors affiche temps restant
			
			if(today_year <= plyNode.getEndTimeBanYear() & today_month <= plyNode.getEndTimeBanMonth() 
			   & today_day <= plyNode.getEndTimeBanDay() &today_hour <= plyNode.getEndTimeBanHour() & today_minute <= plyNode.getEndTimeBanMinute()) {
				
				// si déban alors déban
				
				plyNode.revokeBanANDAddToList();
				plyNode.save();
				//e.setCancelled(false);
				
				// et connexion autorisé
				
				return;		
				
			}else {
				
				Text header,body,footer,raison,ban,time_year,time_month,time_Day,time_Hour,time_Minut,time_year_format,time_month_format,time_Day_format,time_Hour_format,time_Minut_format;
				
				header = Text.builder("\n▂▄▅▆▇█ Vous avez été banni █▇▆▅▄▂").color(TextColors.GOLD).build();
				raison = Text.builder(plyNode.getPlayerBanRaison()).color(TextColors.BLUE).build();	
				
				time_year_format = Text.builder(" Y / ").color(TextColors.GOLD).build();	
				time_month_format = Text.builder(" M / ").color(TextColors.GOLD).build();	
				time_Day_format = Text.builder(" D    ").color(TextColors.GOLD).build();	
				time_Hour_format = Text.builder(" H : ").color(TextColors.GOLD).build();	
				time_Minut_format = Text.builder(" m ").color(TextColors.GOLD).build();	
				
				time_year = Text.builder(plyNode.getEndTimeBanYear()+"").color(TextColors.AQUA).append(time_year_format).build();	
				time_month = Text.builder((plyNode.getEndTimeBanMonth() + 1)+"").color(TextColors.AQUA).append(time_month_format).build();	
				time_Day = Text.builder(plyNode.getEndTimeBanDay()+"").color(TextColors.AQUA).append(time_Day_format).build();	
				time_Hour = Text.builder(plyNode.getEndTimeBanHour()+"").color(TextColors.AQUA).append(time_Hour_format).build();	
				time_Minut = Text.builder(plyNode.getEndTimeBanMinute()+"").color(TextColors.AQUA).append(time_Minut_format).build();	
				
				ban = Text.builder(plyNode.getBanAuthorName() + " [ " + plyNode.getBanAuthorUUIDString() + " ]").color(TextColors.DARK_RED).build();	
				
				
				
				body = Text.builder("\n[ Raison ] ").color(TextColors.GOLD).append(raison).build();
				
				Text head_footer = Text.builder("\n[Banni par : ").color(TextColors.GOLD).append(ban).build();
				Text second_head_footer = Text.builder(" ]\n[ Date de débannissement : ").color(TextColors.GOLD).append(time_year,time_month,time_Day,time_Hour,time_Minut,Text.builder(" ]").color(TextColors.GOLD).build()).build();
				
				footer = Text.builder().append(head_footer,second_head_footer).build();
				
				e.clearMessage();
				e.setMessage(header,body,footer);	
				e.setCancelled(true);
				return;
				
			}
			
		}
	}
	
	private boolean isBanned(UUID uniqueId) {
		if(PlayerConfig.getInstance().getPlayerConfigNode(uniqueId) != null) {
			return PlayerConfig.getInstance().getPlayerConfigNode(uniqueId).getIsBanned();
		}
		return false;
		
	}
	
}
