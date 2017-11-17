package fr.mrfern.spongeplugintest.chunk.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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

public class ChangeGroupDelChunk implements CommandExecutor {

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
		    
		    Text textPosX = Text.builder("X:"+ posX ).color(TextColors.LIGHT_PURPLE).build();
	    	Text textSlasher = Text.builder("/").color(TextColors.DARK_BLUE).build();
	    	Text textPosZ = Text.builder("Z:"+ posZ ).color(TextColors.GREEN).build();
	    	Text textEnd = Text.builder(" ] ").color(TextColors.DARK_BLUE).build();
	    	Text textPlayerNameCible = Text.builder(player.getName()).color(TextColors.GOLD).build();
	    	//Text textPlayerNameYou = Text.builder(ply.getName()).color(TextColors.GOLD).build();
	    	Text textGroupName = Text.builder(groupName).color(TextColors.GOLD).build();
				    
		    if(chunkNode != null) {
		    	
		    	if(chunkNode.getClaimedBy().equals(player.getName())){
		    		// vous ne pouvez pas vou ajouté vous meme
		    		Text textClaimed = Text.builder("Vous ne pouvez pas vous retirez vous même, ce claim vous appartient.").color(TextColors.RED).build();
			    	Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.DARK_BLUE).append(textPosX,textSlasher,textPosZ,textEnd,textClaimed).build();
		    		ply.sendMessage(textEnTete);
			    	return CommandResult.empty();
		    	}
		    	
		    	Function<Object,String> stringTransformer = new Function<Object,String>() {
				    @Override
				    public String apply(Object input) {
				        if (input instanceof String) {
				            return (String) input;
				        } else {
				            return null;
				        }
				    }
				};
		    	
		    	// ajout
		    	if(chunkNode.getClaimedBy().equals(ply.getName())){
		    		// alors autorisé
		    		List<String> listGroupCoOwner = new ArrayList<>(chunkNode.getCoOwnerList());
				    List<String> listGroupUser = new ArrayList<>(chunkNode.getUserList());
				    
				    						    		
				    if((!listGroupCoOwner.contains(player.getName()) & groupName.equals("co-owner")) || (!listGroupUser.contains(player.getName()) & groupName.equals("user"))) {
				    	
				    	Text textClaimed = Text.builder(" n'est pas dans la liste du groupe ").color(TextColors.RED).build();
				    	Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.DARK_BLUE).append(textPosX,textSlasher,textPosZ,textEnd,textPlayerNameCible,textClaimed,textGroupName).build();
			    		ply.sendMessage(textEnTete);
				    	// joueur déjà dans la liste
					    return CommandResult.success();
					    
				    }else if(listGroupCoOwner.contains(player.getName()) & groupName.equals("user")){
				    	
				    	Text textClaimed = Text.builder(" n'est pas dans ce group, il appartient au groupe ").color(TextColors.BLUE).build();
				    	Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.DARK_BLUE).append(textPosX,textSlasher,textPosZ,textEnd,textPlayerNameCible,textClaimed,textGroupName).build();
			    		ply.sendMessage(textEnTete);

					    return CommandResult.success();
					    
				    }else if(listGroupUser.contains(player.getName()) & groupName.equals("co-owner")) {
				    	
				    	Text textClaimed = Text.builder(" n'est pas dans ce group, il appartient au groupe ").color(TextColors.BLUE).build();
				    	Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.DARK_BLUE).append(textPosX,textSlasher,textPosZ,textEnd,textPlayerNameCible,textClaimed,textGroupName).build();
			    		ply.sendMessage(textEnTete);
				    	
					    return CommandResult.success();
					    
				    }else {
				    	
				    	// suppresion à la liste demandé				    	
				    	List<String> list = new ArrayList<>(chunkNode.getNode("chunk","chunk-player-perm",groupName).getList(stringTransformer));				    	
				    	list.remove(player.getName());
				    	chunkNode.getNode("chunk","chunk-player-perm",groupName).setValue(list);				    	
				    	chunkNode.save();
				    	
				    	Text textClaimed = Text.builder(" a été retiré de la liste ").color(TextColors.BLUE).build();
				    	Text textEnTete = Text.builder("[PumpMyChunk -- ").color(TextColors.DARK_BLUE).append(textPosX,textSlasher,textPosZ,textEnd,textPlayerNameCible,textClaimed,textGroupName).build();
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
