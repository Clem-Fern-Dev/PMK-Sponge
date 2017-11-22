package fr.mrfern.spongeplugintest.player;

import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.text.LiteralText;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TextRepresentable;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

public class PlayerInstancieManager{

	/* ClientConnectionEvent.Auth
	 * ClientConnectionEvent.Disconnect
	 * ClientConnectionEvent.Join
	 * ClientConnectionEvent.Login
	 */
	
	private Logger logger;
	
	public PlayerInstancieManager(Logger logger) {
		this.logger = logger;
	}

	@Listener
	public void OnClientAuth(ClientConnectionEvent.Auth e) {
		logger.info(e.getConnection().getAddress().getAddress().getHostAddress() + " auth");
		System.out.println(e.getConnection().getAddress().getAddress().getHostAddress() + " auth");
		
		Text header,body,footer,raison,ban,time_Day,time_Hour,time_Minut,time_Day_format,time_Hour_format,time_Minut_format;
		
		header = Text.builder("▂▄▅▆▇█ Vous avez été banni █▇▆▅▄▂").color(TextColors.GOLD).build();
		raison = Text.builder("Vous etes moche !").color(TextColors.BLUE).build();	
		
		
		time_Day_format = Text.builder(" D / ").color(TextColors.GOLD).build();	
		time_Hour_format = Text.builder(" H / ").color(TextColors.GOLD).build();	
		time_Minut_format = Text.builder(" M ").color(TextColors.GOLD).build();	
		
		time_Day = Text.builder("90").color(TextColors.AQUA).append(time_Day_format).build();	
		time_Hour = Text.builder("11").color(TextColors.AQUA).append(time_Hour_format).build();	
		time_Minut = Text.builder("22").color(TextColors.AQUA).append(time_Minut_format).build();	
		
		ban = Text.builder("Sufike").color(TextColors.DARK_RED).build();	
		
		
		
		body = Text.builder("\n[ Raison ] ").color(TextColors.GOLD).append(raison).build();
		
		Text head_footer = Text.builder("\n[Banni par : ").color(TextColors.GOLD).append(ban).build();
		Text second_head_footer = Text.builder(" ][ Temps restant : ").color(TextColors.GOLD).append(time_Day,time_Hour,time_Minut,Text.builder(" ]").color(TextColors.GOLD).build()).build();
		
		footer = Text.builder().append(head_footer,second_head_footer).build();
		
		e.clearMessage();
		e.setMessage(header,body,footer);	
		e.setCancelled(true);
	}
	
	@Listener
	public void OnClientLogin(ClientConnectionEvent.Login e) {
		logger.info(e.getProfile().getName().get() + " / " + e.getConnection().getAddress().getAddress().getHostAddress() + " login");
		System.out.println(e.getProfile().getName().get() + " / " + e.getConnection().getAddress().getAddress().getHostAddress() + " login");
	}
	
	@Listener
	public void OnClientDisconnect(ClientConnectionEvent.Disconnect e) {
		logger.info(e.getTargetEntity().getName() + " disconnect");
		System.out.println(e.getTargetEntity().getName() + " disconnect");
	}

	@Listener
	public void OnClientJoin(ClientConnectionEvent.Join e) {
		logger.info(e.getTargetEntity().getName() + " join");
		System.out.println(e.getTargetEntity().getName() + " join");
	}
	
}
