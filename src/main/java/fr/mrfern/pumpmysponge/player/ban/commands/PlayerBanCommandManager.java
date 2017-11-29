package fr.mrfern.pumpmysponge.player.ban.commands;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import fr.mrfern.pumpmysponge.Main;

public class PlayerBanCommandManager {
	private static PlayerBanCommandManager instance = new PlayerBanCommandManager();
	private static Main mainManager;

	public static PlayerBanCommandManager commands(Main main) {
		mainManager = main;
		return instance;
	}
	
	public void setupCommands() {
		
		CommandSpec commandTempBanSpec = CommandSpec.builder()
			    .description(Text.of("Commande de bannissement avec durée"))
			    .permission("pumpmystaff.player.ban")
			    .executor(new TBanCommand())
			    .arguments(GenericArguments.flags().flag("all").buildWith(GenericArguments.none()),
		    				GenericArguments.player(Text.of("player")),
		    				GenericArguments.integer(Text.of("day")),
		    				GenericArguments.integer(Text.of("hour")),
		    				GenericArguments.integer(Text.of("minute")),		    				
		    				GenericArguments.remainingJoinedStrings(Text.of("raison")))
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandTempBanSpec, "tban");
		
		CommandSpec commandUnBanSpec = CommandSpec.builder()
			    .description(Text.of("Commande de bannissement avec durée"))
			    .executor(new UnBanCommand())
			    .arguments(GenericArguments.flags().flag("all").buildWith(GenericArguments.none()),
	    				   GenericArguments.player(Text.of("player")))
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandUnBanSpec, "unban");
		
	}
}
