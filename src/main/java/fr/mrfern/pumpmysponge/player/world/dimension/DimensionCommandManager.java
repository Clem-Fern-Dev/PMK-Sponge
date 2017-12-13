package fr.mrfern.pumpmysponge.player.world.dimension;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import fr.mrfern.pumpmysponge.Main;
import fr.mrfern.pumpmysponge.player.world.dimension.commands.CreateDimGroupCommand;

public class DimensionCommandManager {
	private static DimensionCommandManager instance = new DimensionCommandManager();
	private static Main mainManager;

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
			    .executor(new CreateDimGroupCommand())
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandDeleteDimGroupSpec, "party-d", "party-delete");
		
		CommandSpec commandAddDimGroupSpec = CommandSpec.builder()
			    .description(Text.of("Commande de bannissement avec durée"))
			    .permission("pumpmysponge.command.add")
			    .executor(new CreateDimGroupCommand())
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandAddDimGroupSpec, "party-a", "party-add");
		
		CommandSpec commandQueueDimGroupSpec = CommandSpec.builder()
			    .description(Text.of("Commande de bannissement avec durée"))
			    .permission("pumpmysponge.command.inqueu")
			    .executor(new CreateDimGroupCommand())
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
}
