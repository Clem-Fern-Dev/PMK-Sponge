package fr.mrfern.spongeplugintest.chunk.commands;

import java.util.List;
import java.util.Optional;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import fr.mrfern.spongeplugintest.config.ChunkConfig;
import fr.mrfern.spongeplugintest.config.ChunkNode;

public class ChangeGroupAddChunk implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if(src instanceof Player) {
			
			Player player;
			if(args.<Player>getOne("player").isPresent()) {
				player = args.<Player>getOne("player").get();
			}else {
				src.sendMessage(Text.of("Le joueur entré n'est pas valide"));
			    return CommandResult.empty();
			}
			
            String groupName;
            if(args.<String>getOne("group-name").isPresent()) {
				groupName = args.<String>getOne("group-name").get();
			}else {
				src.sendMessage(Text.of("Mauvais argument"));
			    return CommandResult.empty();
			}
            
            Player ply = (Player) src;
			int posX = (int) ply.getLocation().getChunkPosition().getX(), posZ = (int) ply.getLocation().getChunkPosition().getZ();
		    String worldName = ply.getWorld().getName();		
				    
		    ChunkNode chunkNode = ChunkConfig.getInstance().getChunkConfigNode(worldName,posX,posZ);
				    
		    if(chunkNode != null) {
		    	
		    	if(chunkNode.getClaimedBy().equals(player.getName())){
		    		// vous ne pouvez pas vou ajouté vous meme
		    		return CommandResult.success();
		    	}
		    	
		    	// ajout
		    	if(chunkNode.getClaimedBy().equals(ply.getName())){
		    		// alors autorisé
		    		List<String> listGroupCoOwner = getNodeGroupList("co-owner", chunkNode);
				    List<String> listGroupUser = getNodeGroupList("user", chunkNode);
				    						    		
				    if(listGroupCoOwner.contains(player.getName()) & groupName.equals("co-owner")) {
				    		ply.sendMessage(Text.of("joueur déjà dans la liste co-owner"));
				    		// joueur déjà dans la liste
					    	return CommandResult.success();
				    }else if(listGroupUser.contains(player.getName()) & groupName.equals("user")) {
				    		ply.sendMessage(Text.of("joueur déjà dans la liste user"));
				    		// joueur déjà dans la liste
					    	return CommandResult.success();
				    }else if(listGroupCoOwner.contains(player.getName()) & groupName.equals("user")){
				    		ply.sendMessage(Text.of("suppression de la liste co-owner et ajout à list user"));
				    		// suppression de la liste co-owner et ajout à list user
					    	return CommandResult.success();
				    }else if(listGroupUser.contains(player.getName()) & groupName.equals("co-owner")) {
				    		ply.sendMessage(Text.of("suppression de la list user et ajout à la list co-owner"));
				    		// suppression de la list user et ajout à la list co-owner
					    	return CommandResult.success();
				    }else {
				    		ply.sendMessage(Text.of("ajout à la liste demandé simple"));
				    		// ajout à la liste demandé
					    	return CommandResult.success();
				    }

				    		
		    	}else {
				    // verification si autorisation
				    ply.sendMessage(Text.of("verification de l'autorisation"));
				}

		    }
		    return CommandResult.empty();
				    
		}else {
			// return erreur
			src.sendMessage(Text.of("Joueur inconnu"));
			return CommandResult.empty();
		}
		
	}
	
	private List<String> getNodeGroupList(String groupName, ChunkNode chunkNode) {
		switch (groupName) {
		case "co-owner":
			return chunkNode.getCoOwnerList();
			
		case "user":
			return chunkNode.getUserList();
		}
		return null;
	}
	
}
