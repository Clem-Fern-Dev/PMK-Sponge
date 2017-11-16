package fr.mrfern.spongeplugintest.chunk.commands;

import java.util.Optional;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import fr.mrfern.spongeplugintest.config.ChunkConfig;
import fr.mrfern.spongeplugintest.config.ChunkNode;

public class ChangeGroupAddChunk implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if(src instanceof Player) {
			
			Optional<String> optionalOwner = args.<String>getOne("co-owner");
			Optional<String> optionnalUser = args.<String>getOne("user");
			String plyName;
			
			if (optionalOwner.isPresent()) {
			    plyName = optionalOwner.get();
			}else if(optionnalUser.isPresent()){
				plyName = optionnalUser.get();
			}else {
				plyName = null;
			}
			
			
			Player ply = (Player) src;
			int posX = (int) ply.getLocation().getChunkPosition().getX(), posZ = (int) ply.getLocation().getChunkPosition().getZ();
		    String worldName = ply.getWorld().getName();
		    
		    ChunkNode chunkNode = ChunkConfig.getInstance().getChunkConfigNode(worldName,posX,posZ);
	    	
		    if(chunkNode != null) {
		    	 	 	
		    }
					
		    return CommandResult.empty();
		}
		return CommandResult.empty();
	}
}
