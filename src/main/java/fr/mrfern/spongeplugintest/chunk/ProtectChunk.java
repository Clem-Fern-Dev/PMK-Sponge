package fr.mrfern.spongeplugintest.chunk;

import org.spongepowered.api.event.block.InteractBlockEvent;

public class ProtectChunk{

	public void OnInteractBlockEvent(InteractBlockEvent e) {
		
	}
	
	
	
	
}
/*
	@Listener
	public void onInteractEvent( e,@First Player player) throws Exception {
		Location<World> location;
		double posX;//= player.getLocation().getChunkPosition().getX();
		double posZ; //= player.getLocation().getChunkPosition().getZ();
		
		if(e.getInteractionPoint().isPresent()) { 
			location = new Location<World>(player.getWorld(),e.getInteractionPoint().get().getX(),e.getInteractionPoint().get().getY(),e.getInteractionPoint().get().getZ());
			posX = location.getChunkPosition().getX();
			posZ = location.getChunkPosition().getZ();
			
			Location<World> location1 = new Location<World>(player.getWorld(),e.getInteractionPoint().get().getFloorX(),e.getInteractionPoint().get().getFloorY(),e.getInteractionPoint().get().getFloorZ());
			int posX1 = location1.getChunkPosition().getX();
			int posZ1 = location1.getChunkPosition().getZ();
			player.sendMessage(Text.of("x : " + posX + " z : " + posZ + "  /   floor X  = " + posX1 + " z : " + posZ1));
			
			String worldName = player.getWorld().getName();
			
			if(player != null) {
		    	
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
					
					Text textClaimed = Text.builder("Impossible d'executé : "+ e.getHandType().getName() +", Ce chunk appartient à ").color(TextColors.RED).append(Text.builder(chunkNode.getClaimedBy()).color(TextColors.YELLOW).build()).build();
			    	Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.DARK_BLUE).append(textPosX,textSlasher,textPosZ,textEnd,textClaimed).build();
			    	player.sendMessage(textEnTete);
		    		e.setCancelled(true);
		    	}else {
		    		
		    	}
			    
		   }
			
	   }
	}	
}*/
