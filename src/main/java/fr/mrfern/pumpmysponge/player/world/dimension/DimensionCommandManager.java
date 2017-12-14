package fr.mrfern.pumpmysponge.player.world.dimension;


import java.util.HashMap;
import java.util.List;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import fr.mrfern.pumpmysponge.Main;
import fr.mrfern.pumpmysponge.player.world.dimension.commands.AddDimGroupCommand;
import fr.mrfern.pumpmysponge.player.world.dimension.commands.CreateDimGroupCommand;
import fr.mrfern.pumpmysponge.player.world.dimension.commands.DeleteDimGroupCommand;
import fr.mrfern.pumpmysponge.player.world.dimension.commands.QueueDimGroupCommand;

public class DimensionCommandManager {
	
	private static DimensionCommandManager instance = new DimensionCommandManager();
	private static Main mainManager;
	
	private static HashMap<List<Player>,QueueDimData> hashQueue = new HashMap<>();

	public static DimensionCommandManager commands(Main main) {
		mainManager = main;
		return instance;
	}
	
	public void setupCommands() {
		
		CommandSpec commandCreateDimGroupSpec = CommandSpec.builder()
			    .description(Text.of("Commande de bannissement avec durée"))
			    .permission("pumpmysponge.command.party.create")
			    .executor(new CreateDimGroupCommand())
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandCreateDimGroupSpec, "party-c", "party-create");
		
		CommandSpec commandDeleteDimGroupSpec = CommandSpec.builder()
			    .description(Text.of("Commande de bannissement avec durée"))
			    .permission("pumpmysponge.command.delete")
			    .executor(new DeleteDimGroupCommand())
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandDeleteDimGroupSpec, "party-d", "party-delete");
		
		CommandSpec commandAddDimGroupSpec = CommandSpec.builder()
			    .description(Text.of("Commande de bannissement avec durée"))
			    .permission("pumpmysponge.command.add")
			    .executor(new AddDimGroupCommand())
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandAddDimGroupSpec, "party-a", "party-add");
		
		CommandSpec commandQueueDimGroupSpec = CommandSpec.builder()
			    .description(Text.of("Commande de bannissement avec durée"))
			    .permission("pumpmysponge.command.inqueu")
			    .executor(new QueueDimGroupCommand())
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandQueueDimGroupSpec, "party-q", "party-queue");
		/*
		CommandSpec commandUnBanSpec = CommandSpec.builder()
			    .description(Text.of("Commande de bannissement avec durée"))
			    .permission("pumpmystaff.player.unban")
			    .executor(new UnBanCommand())
			    .arguments(GenericArguments.flags().flag("all").buildWith(GenericArguments.none()),
	    				   GenericArguments.player(Text.of("player")))
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandUnBanSpec, "unban");*/
		
	}

	public static HashMap<List<Player>,QueueDimData> getHashQueue() {
		return hashQueue;
	}

	public static void setHashQueue(HashMap<List<Player>,QueueDimData> hashQueue) {
		DimensionCommandManager.hashQueue = hashQueue;
	}
	
	public static boolean getPlayerDimData(Player ply) {
		return false;
	}
}
