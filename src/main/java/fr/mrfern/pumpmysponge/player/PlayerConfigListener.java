package fr.mrfern.pumpmysponge.player;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import fr.mrfern.pumpmysponge.Main;
import fr.mrfern.pumpmysponge.config.PlayerConfig;
import fr.mrfern.pumpmysponge.config.PlayerNode;


public class PlayerConfigListener {

	@SuppressWarnings("unused")
	private Logger logger;
	
	public PlayerConfigListener(Logger logger) {
		this.logger = logger;
	}
	
	//@Listener
	public void OnClientJoin(ClientConnectionEvent.Join e) throws Exception {
		
		e.setMessageCancelled(true);
		e.clearMessage();
		Player p = e.getTargetEntity();
		UUID uuid = p.getUniqueId();
		PlayerNode plyNode;
		
		DateFormat date = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
		String newIP = p.getConnection().getAddress().getAddress().getHostAddress();
		
		if(!PlayerConfig.getInstance().playerConfigExist(p.getUniqueId())) {
			
			PlayerConfig.getInstance().createPlayerConfig(uuid);			
			plyNode = PlayerConfig.getInstance().getPlayerConfigNode(uuid);
			
			if(plyNode == null) {
				
				e.setMessage(Text.of("Erreur de création, PlayerConfig.getInstance().getPlayerConfigNode(e.getProfile().getUniqueId()) return null"));
				p.getConnection().getPlayer().kick(Text.of("Erreur de création, PlayerConfig.getInstance().getPlayerConfigNode(e.getProfile().getUniqueId()) return null"));
				throw new Exception("Erreur de création, PlayerConfig.getInstance().getPlayerConfigNode(e.getProfile().getUniqueId()) return null");
				
			}
			
			plyNode.setPlayerUUIDString(p.getUniqueId().toString());
			plyNode.setPlayerName(p.getName());
			
			plyNode.setIP(newIP + " /./ " + date.format(new Date()).toString());
			
			plyNode.save();
			
			welcomeMessage(p);
			
		}else {
			
			plyNode = PlayerConfig.getInstance().getPlayerConfigNode(uuid);
			
			String lastIP = plyNode.getIP();
			
			plyNode.setIP(newIP + " /./ " + date.format(new Date()).toString());
			
			List<String> list = new ArrayList<>(plyNode.getIPList());				    	
	    	list.add(lastIP);
	    	plyNode.setIPList(list);				    	
	    	plyNode.save();
			
			
			
			joinMessage(p);
		}
		
	}

	private void welcomeMessage(Player p) {
		
		Text name = Text.builder(p.getName()).color(TextColors.YELLOW).build();
		Text message = Text.builder(" !!! Première connection sur le serveur.").color(TextColors.GOLD).build();
		Text finalMessage = Text.builder("[ PumpMyKins ] Bienvenue à ").color(TextColors.GOLD).append(name,message).build();
		
		
		Sponge.getServer().getBroadcastChannel().send(finalMessage);	
	}
	
	private void joinMessage(Player p) {
		
		if(p.hasPermission(Main.getPluginName()+".messages.join")) {
			switch (PlayerConfig.getInstance().getPlayerConfigNode(p.getUniqueId()).getPrimGroup()) {
				
				case "staff":
					
					Sponge.getServer().getBroadcastChannel().send(Text.of("staff"));
					
					break;
				
				case "donateur":
				
					Sponge.getServer().getBroadcastChannel().send(Text.of("donateur"));
					
					break;
				case "vip":
	
					Sponge.getServer().getBroadcastChannel().send(Text.of("vip"));
					
					break;
				case "vip+":
	
					Sponge.getServer().getBroadcastChannel().send(Text.of("vip+"));
					
					break;
					
				case "none":
					
					Sponge.getServer().getBroadcastChannel().send(Text.of("none"));
					
					break;
			}
		}
		
	}
	
	
}
