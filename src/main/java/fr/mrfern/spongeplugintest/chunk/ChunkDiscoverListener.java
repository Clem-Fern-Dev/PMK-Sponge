package fr.mrfern.spongeplugintest.chunk;
	
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.text.Text;

import fr.mrfern.spongeplugintest.config.ChunkConfig;
import fr.mrfern.spongeplugintest.config.ChunkNode;

public class ChunkDiscoverListener {

	@Listener
	public void OnDiscoverChunk(MoveEntityEvent e,@First Player player) {		

		double posX = e.getToTransform().getLocation().getChunkPosition().getX();
		double posZ = e.getToTransform().getLocation().getChunkPosition().getZ();

		if(e.getFromTransform().getLocation().getChunkPosition().equals(e.getToTransform().getLocation().getChunkPosition())) {
			
		}else {
			
			player.sendMessage(Text.of("Changement de chunk / ancien : X: " + e.getFromTransform().getLocation().getChunkPosition().getX() + " / Z : " + 
			e.getFromTransform().getLocation().getChunkPosition().getZ() + " nouveau : X: " + posX + " / Z: " + posZ));
			
			if(player != null) {
								
			   String worldName = e.getToTransform().getExtent().getName();
			    
			    ChunkConfig.getInstance().createChunkConfig(worldName, (int) posX, (int) posZ);
			 
			    
			    ChunkNode chunkNode = ChunkConfig.getInstance().getChunkConfigNode(worldName,(int) posX, (int) posZ);
			    chunkNode.setNameDiscoverer(player.getName());
			    chunkNode.save();
			}
		}
		
		/*if(e.getFromTransform().getLocation().getChunkPosition().getX() != e.getToTransform().getLocation().getChunkPosition().getX() &&
				e.getFromTransform().getLocation().getChunkPosition().getZ() != e.getToTransform().getLocation().getChunkPosition().getZ()) {
			
			
		}*/
		
	}
	
}
