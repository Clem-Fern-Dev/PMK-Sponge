package fr.mrfern.spongeplugintest.player;

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

import fr.mrfern.spongeplugintest.Main;
import fr.mrfern.spongeplugintest.config.PlayerConfig;
import fr.mrfern.spongeplugintest.config.PlayerNode;

public class PlayerConfigListener {

	@SuppressWarnings("unused")
	private Logger logger;
	
	public PlayerConfigListener(Logger logger) {
		this.logger = logger;
	}
	
	@Listener
	public void OnClientJoin(ClientConnectionEvent.Join e) throws Exception {
		
		e.setMessageCancelled(true);
		e.clearMessage();
		Player p = e.getTargetEntity();
		UUID uuid = p.getUniqueId();
		PlayerNode plyNode;
		
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
			plyNode.save();
			
			welcomeMessage(p);
			
		}else {
			
			plyNode = PlayerConfig.getInstance().getPlayerConfigNode(uuid);
			
			String lastIP = plyNode.getIP();
			String newIP = p.getConnection().getAddress().getAddress().getHostAddress();
			DateFormat date = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
			
			plyNode.setIP(newIP);
			
			List<String> list = new ArrayList<>(plyNode.getIPList());				    	
	    	list.add(lastIP + " /./ " + date.format(new Date()).toString());
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
					
					p.sendMessage(Text.of("test"));
					
					break;
				
				case "donateur":
				
					p.sendMessage(Text.of("test"));
					
					break;
				case "vip":
	
					p.sendMessage(Text.of("test"));
					
					break;
				case "vip+":
	
					p.sendMessage(Text.of("test"));
					
					break;
					
				case "none":
					
					p.sendMessage(Text.of("test"));
					
					break;
			}
		}
		
	}
	
	
}
