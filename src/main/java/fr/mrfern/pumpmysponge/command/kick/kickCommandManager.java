package fr.mrfern.pumpmysponge.command.kick;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import fr.mrfern.pumpmysponge.Main;

public class kickCommandManager {
	private static kickCommandManager instance = new kickCommandManager();
	private static Main mainManager;

	public static kickCommandManager commands(Main main) {
		mainManager = main;
		return instance;
	}
	
	public void setupCommands() {
		
		CommandSpec commandKickSpec = CommandSpec.builder()
			    .description(Text.of("Commande de kick"))
			    .permission("pumpmystaff.command.kick")
			    .executor(new KickCommand())
			    .arguments(GenericArguments.flags().flag("all").buildWith(GenericArguments.none()),
		    				GenericArguments.player(Text.of("player")),	    				
		    				GenericArguments.remainingJoinedStrings(Text.of("raison")))
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandKickSpec, "tban");
		
	}
}
