package fr.mrfern.pumpmysponge.player.ban.commands;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.CommandFlags.UnknownFlagBehavior;
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
			    .executor(new TBanCommand())
			    .arguments(GenericArguments.flags().flag("all").buildWith(GenericArguments.none()),
		    				GenericArguments.onlyOne(GenericArguments.player(Text.of("player"))),
		    				GenericArguments.onlyOne(GenericArguments.string(Text.of("time"))))
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandTempBanSpec, "tban");
		
		CommandSpec commandUnBanSpec = CommandSpec.builder()
			    .description(Text.of("Commande de bannissement avec durée"))
			    .executor(new UnBanCommand())
			    .arguments(GenericArguments.flags().flag("all").buildWith(GenericArguments.none()),
			    			GenericArguments.onlyOne(GenericArguments.player(Text.of("player"))),
			    		   GenericArguments.flags().setUnknownLongFlagBehavior(UnknownFlagBehavior.ACCEPT_VALUE).buildWith(GenericArguments.none()))
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandUnBanSpec, "unban");
		
	}
}
