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
		String worldName = e.getToTransform().getExtent().getName();

		if(!e.getFromTransform().getLocation().getChunkPosition().equals(e.getToTransform().getLocation().getChunkPosition())) {
			
			//player.sendMessage(Text.of("Changement de chunk / ancien : X: " + e.getFromTransform().getLocation().getChunkPosition().getX() + " / Z : " + 
			//e.getFromTransform().getLocation().getChunkPosition().getZ() + " nouveau : X: " + posX + " / Z: " + posZ));
				
			ChunkNode chunkNode = ChunkConfig.getInstance().getChunkConfigNode(worldName,(int) posX, (int) posZ);
			
			if(chunkNode != null) {
				
				int lastchunkX = e.getFromTransform().getLocation().getChunkPosition().getX();
				int lastchunkZ = e.getFromTransform().getLocation().getChunkPosition().getZ();
				ChunkNode lastChunkNode = ChunkConfig.getInstance().getChunkConfigNode(worldName,lastchunkX,lastchunkZ);
				
				String fromClaimedBy = lastChunkNode.getClaimedBy(), ToClaimedBy = chunkNode.getClaimedBy();
				//player.sendMessage(Text.of(fromClaimedBy + " to " + ToClaimedBy));
				
				if(chunkNode.getClaimedBy().equals(player.getName()) | chunkNode.getUserList().contains(player.getName())) {
					// le chunk dans lequel on rentre est à nous
					if(!lastChunkNode.getClaimedBy().equals(chunkNode.getClaimedBy())){
						player.sendMessage(Text.of("Bienvenue chez vous"));
					}
				}else if(lastChunkNode.getClaimedBy().equals(player.getName()) | lastChunkNode.getUserList().contains(player.getName())){
					if(!chunkNode.getClaimedBy().equals(lastChunkNode.getClaimedBy())){
						player.sendMessage(Text.of("Au revoir"));
					}
				}
				
								
				
			}else {
				
				ChunkConfig.getInstance().createChunkConfig(worldName, (int) posX, (int) posZ);
				chunkNode = ChunkConfig.getInstance().getChunkConfigNode(worldName,(int) posX, (int) posZ);			    
			    
				chunkNode.setNameDiscoverer(player.getName());
				chunkNode.save();
				
			}
			   
			    
			
			
		}
		
		/*if(e.getFromTransform().getLocation().getChunkPosition().getX() != e.getToTransform().getLocation().getChunkPosition().getX() &&
				e.getFromTransform().getLocation().getChunkPosition().getZ() != e.getToTransform().getLocation().getChunkPosition().getZ()) {
			
			
		}*/
		
	}
	
}
