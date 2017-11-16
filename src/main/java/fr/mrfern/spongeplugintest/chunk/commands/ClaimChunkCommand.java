package fr.mrfern.spongeplugintest.chunk.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.CommandBlockSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import fr.mrfern.spongeplugintest.config.ChunkConfig;
import fr.mrfern.spongeplugintest.config.ChunkNode;

public class ClaimChunkCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		if(src instanceof Player) {
			
			Player ply = (Player) src;
			int posX = (int) ply.getLocation().getChunkPosition().getX(), posZ = (int) ply.getLocation().getChunkPosition().getZ();
		    String worldName = ply.getWorld().getName();
		    
		    ChunkNode chunkNode = ChunkConfig.getInstance().getChunkConfigNode(worldName,posX,posZ);
	    	
		    if(chunkNode != null) {
		    	
		    	Text textPosX = Text.builder("X:"+ posX ).color(TextColors.RED).build();
		    	Text textSlasher = Text.builder("/").color(TextColors.DARK_BLUE).build();
		    	Text textPosZ = Text.builder("Z:"+ posZ ).color(TextColors.GREEN).build();
		    	Text textEnd = Text.builder(" ] ").color(TextColors.DARK_BLUE).build();
			    
			    if(chunkNode.getClaimedBy().equals("none")) {
			    	
			    	chunkNode.setClaimedBy(ply.getName());
			    	chunkNode.save();
			    	
			    	Text textClaimed = Text.builder("Ce chunk vous appartient").color(TextColors.BLUE).build();
			    	Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.DARK_BLUE).append(textPosX,textSlasher,textPosZ,textEnd,textClaimed).build();
			    	ply.sendMessage(textEnTete);
			    	return CommandResult.success();
			    	
			    }else {
			    	if(chunkNode.getClaimedBy().equals(ply.getName())) {
			    		Text textClaimed = Text.builder("Vous avez déjà claim ce chunk").color(TextColors.RED).append(Text.builder(chunkNode.getClaimedBy()).color(TextColors.GOLD).build()).build();
				    	Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.DARK_BLUE).append(textPosX,textSlasher,textPosZ,textEnd,textClaimed).build();
				    	ply.sendMessage(textEnTete);
				    	return CommandResult.empty();
			    	}else {
			    		Text textClaimed = Text.builder("Vous ne pouvez pas claim ce chunk, il appartient à ").color(TextColors.RED).append(Text.builder(chunkNode.getClaimedBy()).color(TextColors.GOLD).build()).build();
				    	Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.DARK_BLUE).append(textPosX,textSlasher,textPosZ,textEnd,textClaimed).build();
				    	ply.sendMessage(textEnTete);
				    	return CommandResult.empty();
			    	}
			    	
			    	
			    }
		    }
		    
		    return CommandResult.empty();
		    		
		}
		else if(src instanceof ConsoleSource) {
		    src.sendMessage(Text.of("Impossible d'exécuter cette commands ici"));
		}
		else if(src instanceof CommandBlockSource) {
		    src.sendMessage(Text.of("Impossible d'exécuter cette commands ici"));
		}
		return CommandResult.empty();
	}

}
