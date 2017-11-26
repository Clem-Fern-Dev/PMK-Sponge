package fr.mrfern.spongeplugintest.player.commands;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import fr.mrfern.spongeplugintest.Main;

public class PlayerCommandManager {
	private static PlayerCommandManager instance = new PlayerCommandManager();
	private static Main mainManager;

	public static PlayerCommandManager commands(Main main) {
		mainManager = main;
		return instance;
	}
	
	public void setupCommands() {
		
		CommandSpec commandTempBanSpec = CommandSpec.builder()
			    .description(Text.of("Commande de bannissement avec durée"))
			    .executor(new TBanCommand())
			    .arguments(GenericArguments.flags().flag("all").buildWith(GenericArguments.none()))
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandTempBanSpec, "tban");
		
		CommandSpec commandUnBanSpec = CommandSpec.builder()
			    .description(Text.of("Commande de bannissement avec durée"))
			    .executor(new UnBanCommand())
			    .arguments(GenericArguments.flags().flag("all").buildWith(GenericArguments.none()))
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandUnBanSpec, "unban");
		
	}
}
