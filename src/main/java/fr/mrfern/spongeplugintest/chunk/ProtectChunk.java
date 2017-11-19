package fr.mrfern.spongeplugintest.chunk;

import java.util.Optional;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import fr.mrfern.spongeplugintest.config.ChunkConfig;
import fr.mrfern.spongeplugintest.config.ChunkNode;

public class ProtectChunk{

	//@Listener
	public void OnInteractBlockEvent(InteractBlockEvent.Primary e, @First Player player) throws Exception {
		
		player.sendMessage(Text.of("interact"));
		
		BlockSnapshot block = e.getTargetBlock();
		Optional<Location<World>> location = block.getLocation();
		
		if(location.isPresent()) {
			int posX = location.get().getChunkPosition().getX();
			int posZ = location.get().getChunkPosition().getZ();
			String worldName = player.getWorld().getName();
			
			ChunkNode chunkNode = ChunkConfig.getInstance().getChunkConfigNode(worldName,(int) posX, (int) posZ);
			if(chunkNode == null) {	
				ChunkConfig.getInstance().createChunkConfig(worldName, (int) posX, (int) posZ);				    
			    chunkNode = ChunkConfig.getInstance().getChunkConfigNode(worldName,(int) posX, (int) posZ);
			    if(chunkNode == null) {
			    	throw new Exception("return null après création");
			    }
			    chunkNode.setNameDiscoverer(player.getName());
			    chunkNode.save();
			}
			
			if(!chunkNode.getClaimedBy().equals("none") & !chunkNode.getUserList().contains(player.getName()) & !chunkNode.getClaimedBy().equals(player.getName())){
	    		
	    		Text textPosX = Text.builder("X:"+ posX ).color(TextColors.LIGHT_PURPLE).build();
		    	Text textSlasher = Text.builder("/").color(TextColors.GOLD).build();
		    	Text textPosZ = Text.builder("Z:"+ posZ ).color(TextColors.GREEN).build();
		    	Text textEnd = Text.builder(" ] ").color(TextColors.GOLD).build();

				//Optional<ItemStack> optionnalItemInHand = player.getItemInHand(HandTypes.MAIN_HAND);
				
				Text textClaimed = Text.builder("Impossible d'intéragir avec : " + block.toString() + ", Ce chunk appartient à ").color(TextColors.RED).append(Text.builder(chunkNode.getClaimedBy()).color(TextColors.YELLOW).build()).build();
		    	Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.DARK_BLUE).append(textPosX,textSlasher,textPosZ,textEnd,textClaimed).build();
		    	player.sendMessage(textEnTete);
	    		e.setCancelled(true);
	    	}		
			
		}
	}
	
	@Listener
	public void OnBrakBlock(ChangeBlockEvent.Break e,@First Player player, @First BlockSnapshot block) throws Exception {
		
		player.sendMessage(Text.of("change block break " + block.getLocation().get().getBlockType().getName()));
		
		
		/*Optional<Location<World>> location = block.get().getLocation();
		
		if(location.isPresent()) {
			int posX = location.get().getChunkPosition().getX();
			int posZ = location.get().getChunkPosition().getZ();
			String worldName = player.getWorld().getName();
			
			ChunkNode chunkNode = ChunkConfig.getInstance().getChunkConfigNode(worldName,(int) posX, (int) posZ);
			if(chunkNode == null) {	
				ChunkConfig.getInstance().createChunkConfig(worldName, (int) posX, (int) posZ);				    
			    chunkNode = ChunkConfig.getInstance().getChunkConfigNode(worldName,(int) posX, (int) posZ);
			    if(chunkNode == null) {
			    	throw new Exception("return null après création");
			    }
			    chunkNode.setNameDiscoverer(player.getName());
			    chunkNode.save();
			}
			
			if(!chunkNode.getClaimedBy().equals("none") & !chunkNode.getUserList().contains(player.getName()) & !chunkNode.getClaimedBy().equals(player.getName())){
	    		
	    		Text textPosX = Text.builder("X:"+ posX ).color(TextColors.LIGHT_PURPLE).build();
		    	Text textSlasher = Text.builder("/").color(TextColors.GOLD).build();
		    	Text textPosZ = Text.builder("Z:"+ posZ ).color(TextColors.GREEN).build();
		    	Text textEnd = Text.builder(" ] ").color(TextColors.GOLD).build();

				//Optional<ItemStack> optionnalItemInHand = player.getItemInHand(HandTypes.MAIN_HAND);
				
				Text textClaimed = Text.builder("Impossible d'intéragir avec : " + block.toString() + ", Ce chunk appartient à ").color(TextColors.RED).append(Text.builder(chunkNode.getClaimedBy()).color(TextColors.YELLOW).build()).build();
		    	Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.DARK_BLUE).append(textPosX,textSlasher,textPosZ,textEnd,textClaimed).build();
		    	player.sendMessage(textEnTete);
	    		e.setCancelled(true);
	    	}	
			
		}*/
		
	}
	
	@Listener
	public void OnBrakBlock(ChangeBlockEvent.Place e,@First Player player) {
		player.sendMessage(Text.of("change block place"));
	}
	
	/*@Listener
	public void OnBrakBlock(ChangeBlockEvent.Decay e,@First Player player) {
		player.sendMessage(Text.of("change block decay"));
	}
	
	@Listener
	public void OnBrakBlock(ChangeBlockEvent.Grow e,@First Player player) {
		player.sendMessage(Text.of("change block grow"));
	}
	
	@Listener
	public void OnBrakBlock(ChangeBlockEvent.Modify e,@First Player player) {
		player.sendMessage(Text.of("change block modify"));
	}*/
	
	/*@Listener
	public void OnBrakBlock(ChangeBlockEvent.Post e,@First Player player) {
		player.sendMessage(Text.of("change block post"));
	}
	
	@Listener
	public void OnBrakBlock(ChangeBlockEvent.Pre e,@First Player player) {
		player.sendMessage(Text.of("change block pre"));
	}*/
}
			
/*		
			

		    	
						    	
				    
				    
			   				
				else {
		    		
		    	}
			}			    
	}	
}
/*
	@Listener
	public void onInteractEvent( e,@First Player player) throws Exception {
		
		   }
			
	   }
	}	
}*/
