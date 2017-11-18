package fr.mrfern.spongeplugintest.chunk;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.living.humanoid.HandInteractEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.extent.Extent;

import fr.mrfern.spongeplugintest.config.ChunkConfig;
import fr.mrfern.spongeplugintest.config.ChunkNode;

public class ChunkInteractEvent{

	@Listener
	public void onInteractEvent(HandInteractEvent e,@First Player player) throws Exception {
		Location<Extent> location;
		double posX;//= player.getLocation().getChunkPosition().getX();
		double posZ; //= player.getLocation().getChunkPosition().getZ();
		
		if(e.getInteractionPoint().isPresent()) {
			location = new Location<Extent>(player.getWorld(),e.getInteractionPoint().get());
			posX = location.getChunkPosition().getX();
			posZ = location.getChunkPosition().getZ();
			
			String worldName = player.getWorld().getName();
			
			if(player != null) {
		    	
				ChunkNode chunkNode = ChunkConfig.getInstance().getChunkConfigNode(worldName,(int) posX, (int) posZ);
				
			    if(chunkNode != null) {
			    	
			    	if(!chunkNode.getClaimedBy().equals("none") & !chunkNode.getUserList().contains(player.getName())){
			    		
			    		Text textPosX = Text.builder("X:"+ posX ).color(TextColors.LIGHT_PURPLE).build();
				    	Text textSlasher = Text.builder("/").color(TextColors.GOLD).build();
				    	Text textPosZ = Text.builder("Z:"+ posZ ).color(TextColors.GREEN).build();
				    	Text textEnd = Text.builder(" ] ").color(TextColors.GOLD).build();
				    	
				    	Text textClaimed = Text.builder("Impossible d'executé : "+ e.getHandType().getName() +", Ce chunk appartient à ").color(TextColors.RED).append(Text.builder(chunkNode.getClaimedBy()).color(TextColors.YELLOW).build()).build();
				    	Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.DARK_BLUE).append(textPosX,textSlasher,textPosZ,textEnd,textClaimed).build();
				    	player.sendMessage(textEnTete);
			    		e.setCancelled(true);
			    	}else {
			    		
			    	}
			    	
			    	
			    }else {
			    	throw new Exception("Erreur null chunkNode return" + this.getClass().getName());
			    }
			    
		   }
			
	   }
	}	
}
