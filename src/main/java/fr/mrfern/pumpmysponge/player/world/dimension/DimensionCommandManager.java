package fr.mrfern.pumpmysponge.player.world.dimension;

import fr.mrfern.pumpmysponge.Main;

public class DimensionCommandManager {
	private static DimensionCommandManager instance = new DimensionCommandManager();
	private static Main mainManager;

	public static DimensionCommandManager commands(Main main) {
		mainManager = main;
		return instance;
	}
	
	public void setupCommands() {
		
		/*CommandSpec commandTempBanSpec = CommandSpec.builder()
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
			    .permission("pumpmystaff.player.unban")
			    .executor(new UnBanCommand())
			    .arguments(GenericArguments.flags().flag("all").buildWith(GenericArguments.none()),
	    				   GenericArguments.player(Text.of("player")))
			    .build();
		
		Sponge.getCommandManager().register(mainManager, commandUnBanSpec, "unban");*/
		
	}
}
