package fr.mrfern.spongeplugintest.chunk.commands;

import java.util.Optional;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import fr.mrfern.spongeplugintest.config.ChunkConfig;
import fr.mrfern.spongeplugintest.config.ChunkNode;

public class ChangeGroupAddChunk implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if(src instanceof Player) {
			
			Optional<String> optionalOwner = args.<String>getOne("co-owner");
			Optional<String> optionnalUser = args.<String>getOne("user");
			String plyName,groupName;
			
			if (optionalOwner.isPresent()) {
			    plyName = optionalOwner.get();
			    groupName = "co-owner";
			}else if(optionnalUser.isPresent()){
				plyName = optionnalUser.get();
				groupName = "co-owner";
			}else {
				plyName = null;
				groupName = null;
			}
			
			if(plyName.isEmpty() | groupName.isEmpty()) {
				// return erreur
			    return CommandResult.empty();
			}else {
				// vérification joueur
				if(Sponge.getServer().getPlayer(plyName).isPresent()) {
					
					Player ply = (Player) src;
					int posX = (int) ply.getLocation().getChunkPosition().getX(), posZ = (int) ply.getLocation().getChunkPosition().getZ();
				    String worldName = ply.getWorld().getName();
				    
				    ChunkNode chunkNode = ChunkConfig.getInstance().getChunkConfigNode(worldName,posX,posZ);
				    
				    if(chunkNode != null) {
		    	 	 	// ajout
				    	
					    return CommandResult.success();
				    }
				    return CommandResult.empty();
				    
				}else {
					// return erreur
				    return CommandResult.empty();
				}
			}
		}
		return CommandResult.empty();
	}
}
