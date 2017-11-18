package fr.mrfern.spongeplugintest.chunk.commands;

import java.util.ArrayList;
import java.util.List;

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
			
			Player ply = (Player) src;
			int posX = (int) ply.getLocation().getChunkPosition().getX(), posZ = (int) ply.getLocation().getChunkPosition().getZ();
		    String worldName = ply.getWorld().getName();		
				    
		    ChunkNode chunkNode = ChunkConfig.getInstance().getChunkConfigNode(worldName,posX,posZ);
		    
		    Text textPosX = Text.builder("X:"+ posX ).color(TextColors.LIGHT_PURPLE).build();
	    	Text textSlasher = Text.builder("/").color(TextColors.GOLD).build();
	    	Text textPosZ = Text.builder("Z:"+ posZ ).color(TextColors.GREEN).build();
	    	Text textEnd = Text.builder(" ] ").color(TextColors.GOLD).build();
			
			Player player;
			if(args.<Player>getOne("player").isPresent()) {
				player = args.<Player>getOne("player").get();
			}else {
				Text textClaimed = Text.builder("Le joueur spécifié n'est pas valide ").color(TextColors.RED).build();
		    	Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.DARK_BLUE).append(textPosX,textSlasher,textPosZ,textEnd,textClaimed).build();
	    		ply.sendMessage(textEnTete);
			    return CommandResult.empty();
			}
			
			Text textPlayerNameCible = Text.builder(player.getName()).color(TextColors.YELLOW).build();
            
            
	    	//Text textPlayerNameYou = Text.builder(ply.getName()).color(TextColors.GOLD).build();
				    
		    if(chunkNode != null) {
		    	
		    	/*if(chunkNode.getClaimedBy().equals(player.getName())){
		    		// vous ne pouvez pas vou ajouté vous meme
		    		Text textClaimed = Text.builder("Vous ne pouvez pas vous ajoutez vous même, ce claim vous appartient.").color(TextColors.RED).build();
			    	Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.DARK_BLUE).append(textPosX,textSlasher,textPosZ,textEnd,textClaimed).build();
		    		ply.sendMessage(textEnTete);
			    	return CommandResult.empty();
		    	}*/
		    	
		    	// ajout
		    	if(chunkNode.getClaimedBy().equals(ply.getName())){
		    		// alors autorisé
				    List<String> listGroupUser = new ArrayList<>(chunkNode.getUserList());				    
				    						    		
				    if(listGroupUser.contains(player.getName())) {
				    	
				    	Text textClaimed = Text.builder(" possède déjà les autorisation sur ce chunk ").color(TextColors.RED).build();
				    	Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.GOLD).append(textPosX,textSlasher,textPosZ,textEnd,textPlayerNameCible,textClaimed).build();
			    		ply.sendMessage(textEnTete);
				    	// joueur déjà dans la liste
					    return CommandResult.success();
					    
				    }else {
				    	
				    	// ajout à la liste demandé				    	
				    	List<String> list = new ArrayList<>(chunkNode.getUserList());				    	
				    	list.add(player.getName());
				    	chunkNode.setUserList(list);				    	
				    	chunkNode.save();
				    	
				    	Text textClaimed = Text.builder("Les autorisations sur le chunk ont été donné à ").color(TextColors.BLUE).build();
				    	Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.GOLD).append(textPosX,textSlasher,textPosZ,textEnd,textClaimed,textPlayerNameCible).build();
			    		ply.sendMessage(textEnTete);
				    	
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
	
}
