package fr.mrfern.spongeplugintest.chunk;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import fr.mrfern.spongeplugintest.Main;

public class ChunkCommandManager {
	
	public ChunkCommandManager(CommandManager manager) {
		cmdManager = manager;
	}
	
	protected static CommandManager cmdManager;
	private static ChunkCommandManager instance = new ChunkCommandManager(null);
	private static Main mainManager;

	public static ChunkCommandManager commands(Main main,CommandManager manager) {
		cmdManager = manager;
		mainManager = main;
		return instance;
	}
	
	public void setupCommands() {
		
		CommandSpec myCommandSpec = CommandSpec.builder()
			    .description(Text.of("Hello World Command"))
			    .permission("spongeplugintest.command.helloworld")
			    .executor(new ChunkCommand())
			    .build();
		
		Sponge.getCommandManager().register(mainManager, myCommandSpec, "chunk");
		
	}

	public CommandManager getCmdManager() {
		return cmdManager;
	}

	
}
