package fr.mrfern.spongeplugintest.chunk.commands;

import java.awt.Color;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.CommandBlockSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import fr.mrfern.spongeplugintest.config.ChunkConfig;
import fr.mrfern.spongeplugintest.config.ChunkNode;

public class ChunkCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if(src instanceof Player) {
		    Player player = (Player) src;
		    ChunkNode chunkNode = ChunkConfig.getInstance().getChunkConfigNode(player.getWorld().getName(), 
					(int) player.getLocation().getX(),
					(int) player.getLocation().getZ());
		    
		    player.sendMessage(Text.of(Color.BLUE + "[ ChunkInfo X:" + chunkNode.getLocationX() + " / Z:" + chunkNode.getLocationZ() + " ] "));
		    
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